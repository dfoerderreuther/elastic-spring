package de.eleon.elasticspring.data.api;

import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.index.query.QueryBuilder;

public interface ElementService {

    boolean write(String type, String id, String json);

    GetResponse get(String type, String id);

    boolean delete(String type, String id);

    void deleteAll(String type);

    void deleteAll(String type, QueryBuilder queryBuilder);

    void updateElement(String type, String id, String field, String value);

    void updateElement(String type, String id, String field, Number value);

    long count(String type);

    boolean exists(String type, String id);

}
