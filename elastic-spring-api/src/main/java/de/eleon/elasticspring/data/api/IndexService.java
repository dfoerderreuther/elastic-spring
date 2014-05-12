package de.eleon.elasticspring.data.api;

/**
 * IndexService is managing indexes of an elasticsearch server
 */
public interface IndexService {

    /**
     * The current index name from "elastic.indexname="
     *
     * @return String of indexname
     */
    String getIndexName();

    /**
     * Request if index exist
     *
     * @return true if index exist
     */
    boolean indexExist();

    /**
     * Request deletion of index
     *
     * @return success
     */
    boolean deleteIndex();

    /**
     * Request creation of index
     *
     * @return success
     */
    boolean createIndex();

    /**
     * Request flushing of index. Maybe usefull if you require to access an element directly after creation
     */
    void flushIndex();

}
