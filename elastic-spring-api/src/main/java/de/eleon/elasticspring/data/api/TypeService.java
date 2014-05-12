package de.eleon.elasticspring.data.api;

public interface TypeService {

    boolean typeExist(String type);

    boolean createMapping(String type, String json);

}
