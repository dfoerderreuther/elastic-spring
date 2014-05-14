package test;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@EnableAutoConfiguration
@ComponentScan(value = "de.eleon.elasticspring.impl")
@PropertySource("classpath:elastic.properties")
public class TestConfiguration {

}
