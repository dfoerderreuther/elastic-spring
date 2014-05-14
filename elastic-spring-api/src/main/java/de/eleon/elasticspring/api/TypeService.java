package de.eleon.elasticspring.api;

/**
 * Manage Types in index
 */
public interface TypeService {

    /**
     * Request if type already exis
     *
     * @param type String with typename
     * @return true when type exist
     */
    boolean typeExist(String type);

    /**
     * Create mapping for type
     * @param type String with typename
     * @param json JSON-String with mapping information
     * @return success
     */
    boolean createMapping(String type, String json);

}
