package de.eleon.elasticspring.data.impl;

import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.index.query.QueryBuilders;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import test.TestConfiguration;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestConfiguration.class )
public class SearchServiceTest {

    @Autowired
    SearchService searchService;

    @Autowired
    ElementServiceImpl elementServiceImpl;

    @Autowired
    IndexService indexService;

    String type = "test2";
    String json = "{\"name\":\"findme\"}";
    String json2 = "{\"name\":\"other\"}";

    @Test
    public void testSearchRequestBuilder() {
        elementServiceImpl.write(type, "id1", json);
        elementServiceImpl.write(type, "id2", json);
        elementServiceImpl.write(type, "id3", json2);
        indexService.flushIndex();

        SearchResponse searchResponse = searchService.searchRequestBuilder()
                .setTypes(type)
                .setQuery(QueryBuilders.matchQuery("name", "findme"))
                .execute()
                .actionGet();

        assertThat(searchResponse.getHits().getTotalHits(), is(2l));

    }


}