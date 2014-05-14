package de.eleon.elasticspring.api;

import org.elasticsearch.client.Client;

/**
 * ClientService opens a connection to an elasticsearch server or starts a new instance of an embedded elasticsearch node.
 *
 * The following values can be configured:
 *
 * The port of the elasticsearch server
 * # elastic.host=12345
 *
 * The hostname of the elasticsearch server
 * # elastic.port=localhost
 *
 * The index name to use
 * # elastic.indexname=geosearchtest
 *
 * Delete index on startup
 * # elastic.deleteOnStartup=true
 *
 * If host or port is null, an embedded instance will be used.
 *
 */
public interface ClientService {

    /**
     * Returns the server type
     *
     * @return true when the elasticsearch server is running embedded
     */
    boolean isEmbedded();

    /**
     * Returns a elasticsearch client
     *
     * @return Client
     */
    Client getClient();

}
