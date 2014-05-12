package de.eleon.elasticspring.data.impl;

import de.eleon.elasticspring.data.api.TypeService;
import org.elasticsearch.action.admin.indices.exists.types.TypesExistsRequest;
import org.elasticsearch.action.admin.indices.mapping.put.PutMappingRequest;
import org.elasticsearch.client.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TypeServiceImpl implements TypeService {

    @Autowired
    Client client;

    @Autowired
    IndexServiceImpl indexServiceImpl;

    @Override
    public boolean typeExist(String type) {
        return client.admin().indices()
                .typesExists(new TypesExistsRequest(new String[]{indexServiceImpl.getIndexName()}, type))
                .actionGet()
                .isExists();
    }

    @Override
    public boolean createMapping(String type, String json) {
        PutMappingRequest request = new PutMappingRequest(indexServiceImpl.getIndexName());
        request.type(type).source(json);
        return client.admin().indices()
                .putMapping(request)
                .actionGet()
                .isAcknowledged();
    }
}
