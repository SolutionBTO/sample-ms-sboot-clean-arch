# Getting Started

### Reference Documentation

For further reference, please consider the following sections:

* [Official Gradle documentation](https://docs.gradle.org)
* [Spring Boot Gradle Plugin Reference Guide](https://docs.spring.io/spring-boot/4.0.6/gradle-plugin)
* [Create an OCI image](https://docs.spring.io/spring-boot/4.0.6/gradle-plugin/packaging-oci-image.html)
* [Spring Boot Testcontainers support](https://docs.spring.io/spring-boot/4.0.6/reference/testing/testcontainers.html#testing.testcontainers)
* [Testcontainers RabbitMQ Module Reference Guide](https://java.testcontainers.org/modules/rabbitmq/)
* [Testcontainers MongoDB Module Reference Guide](https://java.testcontainers.org/modules/databases/mongodb/)
* [Spring Boot Actuator](https://docs.spring.io/spring-boot/4.0.6/reference/actuator/index.html)
* [Spring for RabbitMQ](https://docs.spring.io/spring-boot/4.0.6/reference/messaging/amqp.html)
* [Spring Cache Abstraction](https://docs.spring.io/spring-boot/4.0.6/reference/io/caching.html)
* [Contract Stub Runner](https://docs.spring.io/spring-cloud-contract/reference/project-features-stubrunner.html)
* [OpenFeign](https://docs.spring.io/spring-cloud-openfeign/reference/)
* [Spring Data MongoDB](https://docs.spring.io/spring-boot/4.0.6/reference/data/nosql.html#data.nosql.mongodb)
* [Spring Data Redis (Access+Driver)](https://docs.spring.io/spring-boot/4.0.6/reference/data/nosql.html#data.nosql.redis)
* [Spring Boot DevTools](https://docs.spring.io/spring-boot/4.0.6/reference/using/devtools.html)
* [MongoDB](https://docs.spring.io/spring-boot/4.0.6/reference/data/nosql.html#data.nosql.mongodb)
* [Prometheus](https://docs.spring.io/spring-boot/4.0.6/reference/actuator/metrics.html#actuator.metrics.export.prometheus)
* [Spring Security](https://docs.spring.io/spring-boot/4.0.6/reference/web/spring-security.html)
* [SpringDoc OpenAPI](https://springdoc.org/)
* [Testcontainers](https://java.testcontainers.org/)
* [Validation](https://docs.spring.io/spring-boot/4.0.6/reference/io/validation.html)
* [Spring Web](https://docs.spring.io/spring-boot/4.0.6/reference/web/servlet.html)

### Guides

The following guides illustrate how to use some features concretely:

* [Building a RESTful Web Service with Spring Boot Actuator](https://spring.io/guides/gs/actuator-service/)
* [Messaging with RabbitMQ](https://spring.io/guides/gs/messaging-rabbitmq/)
* [Caching Data with Spring](https://spring.io/guides/gs/caching/)
* [Accessing Data with MongoDB](https://spring.io/guides/gs/accessing-data-mongodb/)
* [Messaging with Redis](https://spring.io/guides/gs/messaging-redis/)
* [Securing a Web Application](https://spring.io/guides/gs/securing-web/)
* [Spring Boot and OAuth2](https://spring.io/guides/tutorials/spring-boot-oauth2/)
* [Authenticating a User with LDAP](https://spring.io/guides/gs/authenticating-ldap/)
* [SpringDoc OpenAPI](https://github.com/springdoc/springdoc-openapi-demos/)
* [Validation](https://spring.io/guides/gs/validating-form-input/)
* [Building a RESTful Web Service](https://spring.io/guides/gs/rest-service/)
* [Serving Web Content with Spring MVC](https://spring.io/guides/gs/serving-web-content/)
* [Building REST services with Spring](https://spring.io/guides/tutorials/rest/)

### Additional Links

These additional references should also help you:

* [Gradle Build Scans – insights for your project's build](https://scans.gradle.com#gradle)
* [Declarative REST calls with Spring Cloud OpenFeign sample](https://github.com/spring-cloud-samples/feign-eureka)

### Testcontainers support

This project
uses [Testcontainers at development time](https://docs.spring.io/spring-boot/4.0.6/reference/features/dev-services.html#features.dev-services.testcontainers).

Testcontainers has been configured to use the following Docker images:

* [`mongo:latest`](https://hub.docker.com/_/mongo)
* [`rabbitmq:latest`](https://hub.docker.com/_/rabbitmq)
* [`redis:latest`](https://hub.docker.com/_/redis)

Please review the tags of the used images and set them to the same as you're running in production.

