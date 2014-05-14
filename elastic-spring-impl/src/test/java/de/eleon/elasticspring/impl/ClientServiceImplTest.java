package de.eleon.elasticspring.impl;

import org.elasticsearch.action.admin.indices.create.CreateIndexRequest;
import org.elasticsearch.action.admin.indices.create.CreateIndexResponse;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexResponse;
import org.elasticsearch.action.admin.indices.exists.indices.IndicesExistsRequest;
import org.elasticsearch.action.admin.indices.exists.indices.IndicesExistsResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.IndicesAdminClient;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import test.TestConfiguration;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestConfiguration.class )
public class ClientServiceImplTest {

    @Autowired
    ClientServiceImpl clientServiceImpl;

    @Test
    public void shouldNotBeNull() {
        assertThat(clientServiceImpl, notNullValue());
    }

    @Test
    public void shouldGetClient() {
        assertThat(clientServiceImpl.getClient(), notNullValue());
    }

    @Test
    public void shouldBeEmbedded() {
        assertTrue(clientServiceImpl.isEmbedded());
    }

    @Test
    public void shouldManageIndex() {

        Client client = clientServiceImpl.getClient();
        String indexName = "manageindextest";
        IndicesAdminClient indices = client.admin().indices();

        CreateIndexResponse createIndexResponse = indices.create(new CreateIndexRequest(indexName)).actionGet();
        assertTrue(createIndexResponse.isAcknowledged());

        IndicesExistsResponse indicesExistsResponse = indices.exists(new IndicesExistsRequest(indexName)).actionGet();
        assertTrue(indicesExistsResponse.isExists());

        DeleteIndexResponse deleteIndexResponse = indices.delete(new DeleteIndexRequest(indexName)).actionGet();
        assertTrue(deleteIndexResponse.isAcknowledged());

    }

}