package net.lazars.greeting.mongo;

import org.junit.jupiter.api.BeforeAll;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

@DataMongoTest
@Testcontainers
@EnableMongoRepositories("net.lazars.greeting.mongo.repository")
public abstract class DocumentRepositoryTest {

  @Container private static final MongoDBContainer CONTAINER = new MongoDBContainer("mongo");

  @DynamicPropertySource
  static void setProperties(final DynamicPropertyRegistry registry) {
    registry.add("spring.data.mongodb.uri", CONTAINER::getReplicaSetUrl);
    registry.add("spring.data.mongodb.database", () -> "dbName");
    registry.add("greeting.database", () -> "MONGO");
  }

  @BeforeAll
  public static void beforeAll() {
    CONTAINER.withReuse(true).start();
  }
}
