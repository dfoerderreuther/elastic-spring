package de.eleon.elasticspring.data.impl;

import de.eleon.elasticspring.data.api.SearchService;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.client.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SearchServiceImpl implements SearchService {

    @Autowired
    IndexServiceImpl indexServiceImpl;

    @Autowired
    Client client;

    @Override
    public SearchRequestBuilder searchRequestBuilder() {
        return client.prepareSearch(indexServiceImpl.getIndexName());
    }

}
