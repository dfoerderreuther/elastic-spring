package de.eleon.elasticspring.data.impl;

import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import test.TestConfiguration;

import java.io.IOException;

import static org.elasticsearch.common.xcontent.XContentFactory.jsonBuilder;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestConfiguration.class )
public class TypeServiceImplTest extends TestCase {

    @Autowired
    TypeServiceImpl typeServiceImpl;

    @Test
    public void shouldNotHaveType() {
        assertFalse(typeServiceImpl.typeExist("unknown"));
    }

    @Test
    public void shouldCreateType() throws IOException {
        String type = "create";
        String json = jsonBuilder()
                .startObject()
                    .startObject(type)
                        .startObject("properties")
                            .startObject("name").field("type", "string").endObject()
                        .endObject()
                    .endObject()
                .endObject().string();
        typeServiceImpl.createMapping(type, json);

        assertTrue(typeServiceImpl.typeExist(type));
    }
}