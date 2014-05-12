package de.eleon.elasticspring.data.api;

import org.elasticsearch.action.search.SearchRequestBuilder;

/**
 * SearchService for searching in elasticsearch index
 */
public interface SearchService {

    /**
     * Create a {link SearchRequestBuilder} in current index
     *
     * @return a new SearchRequestBuilder
     */
    SearchRequestBuilder searchRequestBuilder();

}
