package de.eleon.elasticspring.data.impl;

import de.eleon.elasticspring.data.api.ClientService;
import org.apache.log4j.Logger;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.ImmutableSettings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.node.Node;
import org.elasticsearch.node.NodeBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;

import javax.annotation.PreDestroy;

@Service
@Configuration
public class ClientServiceImpl implements ClientService {


    private static Logger log = Logger.getLogger(ClientServiceImpl.class);

    private Node node;
    private Client client;

    private boolean embedded;

    @Value("${elastic.host}")
    private String host;

    @Value("${elastic.port}")
    private Integer port;


    public ClientServiceImpl() {
        log.info("Initializing ElasticSearch client with embedded node");
        if (host != null && port != null) {
            init(host, port);
        } else {
            initEmbedded();
        }
    }

    private void initEmbedded() {
        ImmutableSettings.Builder elasticsearchSettings = ImmutableSettings.settingsBuilder()
                .put("http.enabled", "false")
                .put("path.data", "target/elasticsearch-data");

        this.node = NodeBuilder.nodeBuilder().client(false)
                .local(true)
                .settings(elasticsearchSettings.build())
                .node();

        this.client = node.client();
        this.embedded = true;
    }


    private void init(String host, int port) {
        log.info(String.format("Initializing ElasticSearch client on Host: [%s], Port: [%s]", host, port));
        this.client = new TransportClient().addTransportAddress(new InetSocketTransportAddress(host, port));
        this.embedded = false;
    }

    @PreDestroy
    public void close() {
        log.info("Close ElasticSearch client");
        //this.client.close();
        if (this.node != null) {
            this.node.close();
        }
    }

    @Override
    public boolean isEmbedded() {
        return embedded;
    }

    @Override
    @Bean
    public Client getClient() {
        return client;
    }

}

