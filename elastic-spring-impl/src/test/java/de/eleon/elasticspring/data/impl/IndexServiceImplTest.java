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
public class IndexServiceImplTest {

    @Autowired
    IndexServiceImpl indexServiceImpl;

    @Test
    public void shouldGetIndexName() {
        assertThat(indexServiceImpl.getIndexName(), is("geosearchtest"));
    }

    @Test
    public void shouldHaveIndex() {
        assertTrue(indexServiceImpl.indexExist());
    }

    @Test
    public void shouldRecreateIndex() {
        assertTrue("should have index on startup", indexServiceImpl.indexExist());
        assertTrue("should delete index", indexServiceImpl.deleteIndex());
        assertFalse("index should not exist after delete", indexServiceImpl.indexExist());
        assertTrue("should create index", indexServiceImpl.createIndex());
        assertTrue("index should exist after create", indexServiceImpl.indexExist());
    }

}