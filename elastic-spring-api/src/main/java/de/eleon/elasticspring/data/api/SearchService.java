package de.eleon.elasticspring.data.api;

import org.elasticsearch.action.search.SearchRequestBuilder;

public interface SearchService {

    SearchRequestBuilder searchRequestBuilder();

}
