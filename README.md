# Takealot Better Reviews

### Reference Documentation
* [Official Gradle documentation](https://docs.gradle.org)
* [Spring Boot Gradle Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/2.5.0/gradle-plugin/reference/html/)
* [Spring Web](https://docs.spring.io/spring-boot/docs/2.5.0/reference/htmlsingle/#boot-features-developing-web-applications)
* [Spring Boot DevTools](https://docs.spring.io/spring-boot/docs/2.5.0/reference/htmlsingle/#using-boot-devtools)

### Guides

The following guides illustrate how to use some features concretely:

* [Building a RESTful Web Service](https://spring.io/guides/gs/rest-service/)
* [Serving Web Content with Spring MVC](https://spring.io/guides/gs/serving-web-content/)
* [Building REST services with Spring](https://spring.io/guides/tutorials/bookmarks/)


### Setting up
Navigate to the root of the folder and execute using cmd

`gradlew clean build`

This would generate a `build` folder.

Next run

`java -jar build\libs\<jarfile_name>.jar`

This will kick off your server.

### Alternate
Import project into IDE of choice

Run `MainApplication.java` as a java appication.

### To test locally
`http://localhost:8081/tbr/swagger-ui.html`

