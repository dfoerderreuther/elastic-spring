package de.eleon.elasticspring.data.impl;

import org.apache.log4j.Logger;
import org.elasticsearch.action.admin.indices.create.CreateIndexRequest;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.admin.indices.exists.indices.IndicesExistsRequest;
import org.elasticsearch.action.admin.indices.flush.FlushRequest;
import org.elasticsearch.client.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class IndexService {

    private static Logger log = Logger.getLogger(IndexService.class);

    @Value("${elastic.indexname}")
    protected String indexName;

    @Value("${elastic.deleteOnStartup}")
    private boolean deleteOnStartup;

    public String getIndexName() {
        return indexName;
    }

    @Autowired
    Client client;

    @PostConstruct
    private void init() {
        boolean exist = indexExist();
        if (deleteOnStartup && exist) {
            deleteIndex();
            createIndex();

        } else if(!exist) {
            createIndex();

        }
    }

    public boolean indexExist() {
        return client.admin().indices()
                .exists(new IndicesExistsRequest(indexName))
                .actionGet()
                .isExists();
    }

    public boolean deleteIndex() {
        return client.admin().indices()
                .delete(new DeleteIndexRequest(indexName))
                .actionGet()
                .isAcknowledged();
    }

    public boolean createIndex() {
        return client.admin().indices()
                .create(new CreateIndexRequest(indexName))
                .actionGet()
                .isAcknowledged();
    }

    public void flushIndex() {
        client.admin().indices()
                .flush(new FlushRequest(indexName))
                .actionGet();
    }

}
