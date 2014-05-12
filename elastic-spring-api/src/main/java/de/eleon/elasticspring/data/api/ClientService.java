package de.eleon.elasticspring.data.api;

import org.elasticsearch.client.Client;

public interface ClientService {

    boolean isEmbedded();

    Client getClient();

}
