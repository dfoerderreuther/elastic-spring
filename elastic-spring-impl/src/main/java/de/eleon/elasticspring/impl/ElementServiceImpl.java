package de.eleon.elasticspring.impl;

import de.eleon.elasticspring.api.ElementService;
import org.elasticsearch.action.count.CountRequest;
import org.elasticsearch.action.deletebyquery.DeleteByQueryRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.support.QuerySourceBuilder;
import org.elasticsearch.client.Client;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ElementServiceImpl implements ElementService {

    @Autowired
    Client client;

    @Autowired
    IndexServiceImpl indexServiceImpl;

    @Override
    public boolean write(String type, String id, String json) {
        return client.prepareIndex(indexServiceImpl.getIndexName(), type, id)
                .setSource(json)
                .execute()
                .actionGet()
                .isCreated();
    }

    @Override
    public GetResponse get(String type, String id) {
        return client.prepareGet(indexServiceImpl.getIndexName(), type, id)
                .execute()
                .actionGet();
    }

    @Override
    public boolean delete(String type, String id) {
        return client.prepareDelete(indexServiceImpl.getIndexName(), type, id)
                .execute()
                .actionGet()
                .isFound();
    }

    @Override
    public void deleteAll(String type) {
        deleteAll(type, QueryBuilders.matchAllQuery());
    }

    @Override
    public void deleteAll(String type, QueryBuilder queryBuilder) {
        QuerySourceBuilder qsb = new QuerySourceBuilder()
                .setQuery(queryBuilder);
        DeleteByQueryRequest deleteByQueryRequest = new DeleteByQueryRequest(indexServiceImpl.getIndexName())
                .types(type)
                .source(qsb);
        client.deleteByQuery(deleteByQueryRequest)
                .actionGet();
    }

    @Override
    public void updateElement(String type, String id, String field, String value) {
        String script = String.format("ctx._source.%s=\"%s\"", field, value);
        client.prepareUpdate(indexServiceImpl.getIndexName(), type, id)
                .setScript(script)
                .execute()
                .actionGet()
                .isCreated();
    }

    @Override
    public void updateElement(String type, String id, String field, Number value) {
        String script = String.format("ctx._source.%s=%s", field, value);
        client.prepareUpdate(indexServiceImpl.getIndexName(), type, id)
                .setScript(script)
                .execute()
                .actionGet()
                .isCreated();
    }

    @Override
    public long count(String type) {
        return client.count(new CountRequest(indexServiceImpl.getIndexName()).types(type))
                .actionGet()
                .getCount();
    }

    @Override
    public boolean exists(String type, String id) {
        return client.prepareGet(indexServiceImpl.getIndexName(), type, id)
                .execute()
                .actionGet()
                .isExists();
    }
}
