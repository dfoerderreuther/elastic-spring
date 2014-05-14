package de.eleon.elasticspring.api;

import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.index.query.QueryBuilder;

/**
 * Managing Elements in elasticsearch index
 */
public interface ElementService {

    /**
     * Write element to index
     *
     * @param type String with typename
     * @param id String with id of element
     * @param json JSON-String of element
     * @return success
     */
    boolean write(String type, String id, String json);

    /**
     * Get element from index
     *
     * @param type String with typename
     * @param id String with id of element
     * @return {link GetResponse}
     */
    GetResponse get(String type, String id);

    /**
     * Delete element from index
     * *
     * @param type String with typename
     * @param id String with id of element
     * @return success
     */
    boolean delete(String type, String id);

    /**
     * Delete all elements of specific type from index
     *
     * @param type String with typename
     */
    void deleteAll(String type);

    /**
     * Delete all elements of specific type from index, which are matching to query
     *
     * @param type String with typename
     * @param queryBuilder {link QueryBuilder} to search for
     */
    void deleteAll(String type, QueryBuilder queryBuilder);

    /**
     * Update single value of element
     *
     * @param type String with typename
     * @param id String with id of element
     * @param field String with fieldname
     * @param value String with new value
     */
    void updateElement(String type, String id, String field, String value);

    /**
     * Update single value of element
     *
     * @param type String with typename
     * @param id String with id of element
     * @param field String with fieldname
     * @param value Number with new value
     */
    void updateElement(String type, String id, String field, Number value);

    /**
     * Count elements of specific type
     *
     * @param type String with typename
     * @return count value as long
     */
    long count(String type);

    /**
     * Check if element exist
     *
     * @param type String with typename
     * @param id String with id of element
     * @return true if element exist
     */
    boolean exists(String type, String id);

}
