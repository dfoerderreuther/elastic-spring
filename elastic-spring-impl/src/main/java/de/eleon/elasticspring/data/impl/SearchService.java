package de.eleon.elasticspring.data.impl;

import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.client.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SearchService {

    @Autowired
    IndexService indexService;

    @Autowired
    Client client;

    public SearchRequestBuilder searchRequestBuilder() {
        return client.prepareSearch(indexService.getIndexName());
    }

}
