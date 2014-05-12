package de.eleon.elasticspring.data.impl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import test.TestConfiguration;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestConfiguration.class )
public class ElementServiceImplTest {

    @Autowired
    ElementServiceImpl elementServiceImpl;

    @Autowired
    IndexService indexService;

    String type = "test";
    String json = "{\"name\":\"test\"}";

    @Test
    public void shouldWriteElement() {
        String id = "1a";
        assertTrue("should write", elementServiceImpl.write(type, id, json));
        assertTrue(elementServiceImpl.exists(type, id));
    }

    @Test
    public void shouldGet() {
        String id = "1d";
        elementServiceImpl.write(type, id, json);
        assertThat(elementServiceImpl.get(type, id).getSourceAsString(), is(json));
    }

    @Test
    public void shouldDeleteElement() {
        String id = "1b";
        elementServiceImpl.write(type, id, json);
        assertTrue("element should exist after create", elementServiceImpl.exists(type, id));
        assertTrue("should delete", elementServiceImpl.delete(type, id));
        assertFalse("element should not exist after delete", elementServiceImpl.exists(type, id));
    }

    @Test
    public void shouldCount() {
        elementServiceImpl.deleteAll(type);
        elementServiceImpl.write(type, "1c1", json);
        elementServiceImpl.write(type, "1c2", json);
        elementServiceImpl.write(type, "1c3", json);
        indexService.flushIndex();
        assertThat(elementServiceImpl.count(type), is(3l));
    }

    @Test
    public void shouldDeleteAll() {
        elementServiceImpl.write(type, "1e1", json);
        elementServiceImpl.write(type, "1e2", json);
        elementServiceImpl.write(type, "1e3", json);
        indexService.flushIndex();
        assertThat(elementServiceImpl.count(type), greaterThan(2l));
        elementServiceImpl.deleteAll(type);
        assertThat(elementServiceImpl.count(type), is(0l));
    }

    @Test
    public void shouldUpdateStringValue() {
        String id = "1f";
        elementServiceImpl.write(type, id, json);
        elementServiceImpl.updateElement(type, id, "name", "lala");
        assertThat(elementServiceImpl.get(type, id).getSourceAsString(), is("{\"name\":\"lala\"}"));
    }

    @Test
    public void shouldUpdateIntValue() {
        String id = "1f";
        elementServiceImpl.write(type, id, "{\"name\":1}");
        elementServiceImpl.updateElement(type, id, "name", 2);
        assertThat(elementServiceImpl.get(type, id).getSourceAsString(), is("{\"name\":2}"));
    }

}