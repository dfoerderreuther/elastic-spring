Spring based Elasticsearch quickstart project
==========================

# Build 

mvn install


# Usage 

## Dependencies: 

    <dependency>
        <groupId>de.eleon</groupId>
        <artifactId>elastic-spring-api</artifactId>
        <version>1.0-SNAPSHOT</version>
    </dependency>

    <dependency>
        <groupId>de.eleon.elasticspring.impl</groupId>
        <artifactId>elastic-spring-impl</artifactId>
        <version>1.0-SNAPSHOT</version>
        <scope>runtime</scope>
    </dependency>


## Configuration: 

### Component scan

Add the package "de.eleon.elasticspring.data.impl" to your component scan.

### Properties

Add the following properties to your application.properties:

elastic.host=
elastic.port=
elastic.indexname=myindex
elastic.deleteOnStartup=false

If host or port is null, an embedded elasticsearch instance will be used.


 

