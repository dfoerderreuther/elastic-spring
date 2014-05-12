package de.eleon.elasticspring.data.api;

public interface IndexService {

    String getIndexName();

    boolean indexExist();

    boolean deleteIndex();

    boolean createIndex();

    void flushIndex();

}
