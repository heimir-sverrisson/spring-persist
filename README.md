# Persist

A test project for spring-boot persistance.

## Installation

Pull down this repo and write a application properties file:

```bash
cat > src/main/resources/application.properties
spring.datasource.url = jdbc:postgresql://localhost:5432/__NAME_OF_DATABASE__
spring.datasource.username = __DATABASE_USER__
spring.datasource.password = __DATABASE_PASSWORD__

# Silence an exception
spring.jpa.properties.hibernate.jdbc.lob.non_contextual_creation=true
# Update the schema - if needed
spring.jpa.hibernate.ddl-auto=update
```

Install everything:

```bash
mvn clean; mvn install;
```

Finally run the application:

```bash
mvn spring-boot:run
```

Test it out by navigating to: http://localhost:8080/swagger-ui.html
