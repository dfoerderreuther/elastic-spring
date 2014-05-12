package de.eleon.elasticspring.data.impl;

import org.elasticsearch.action.admin.indices.exists.types.TypesExistsRequest;
import org.elasticsearch.action.admin.indices.mapping.put.PutMappingRequest;
import org.elasticsearch.client.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TypeService {

    @Autowired
    Client client;

    @Autowired
    IndexService indexService;

    public boolean typeExist(String type) {
        return client.admin().indices()
                .typesExists(new TypesExistsRequest(new String[]{indexService.getIndexName()}, type))
                .actionGet()
                .isExists();
    }

    public boolean createMapping(String type, String json) {
        PutMappingRequest request = new PutMappingRequest(indexService.getIndexName());
        request.type(type).source(json);
        return client.admin().indices()
                .putMapping(request)
                .actionGet()
                .isAcknowledged();
    }
}
