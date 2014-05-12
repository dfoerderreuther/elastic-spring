package de.eleon.elasticspring.data.impl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import test.TestConfiguration;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestConfiguration.class )
public class IndexServiceTest {

    @Autowired
    IndexService indexService;

    @Test
    public void shouldGetIndexName() {
        assertThat(indexService.getIndexName(), is("geosearchtest"));
    }

    @Test
    public void shouldHaveIndex() {
        assertTrue(indexService.indexExist());
    }

    @Test
    public void shouldRecreateIndex() {
        assertTrue("should have index on startup", indexService.indexExist());
        assertTrue("should delete index", indexService.deleteIndex());
        assertFalse("index should not exist after delete", indexService.indexExist());
        assertTrue("should create index", indexService.createIndex());
        assertTrue("index should exist after create", indexService.indexExist());
    }

}