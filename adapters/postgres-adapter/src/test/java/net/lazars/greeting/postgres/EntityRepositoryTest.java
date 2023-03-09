package net.lazars.greeting.postgres;

import org.junit.jupiter.api.BeforeAll;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Testcontainers
@EntityScan("net.lazars.greeting.postgres.entity")
@EnableJpaRepositories("net.lazars.greeting.postgres.repository")
public abstract class EntityRepositoryTest {

  @Container
  private static final PostgreSQLContainer<?> CONTAINER =
      new PostgreSQLContainer<>("postgres:14.5");

  @DynamicPropertySource
  static void setProperties(final DynamicPropertyRegistry registry) {
    registry.add("spring.datasource.url", CONTAINER::getJdbcUrl);
    registry.add("spring.datasource.username", CONTAINER::getUsername);
    registry.add("spring.datasource.password", CONTAINER::getPassword);
    registry.add("spring.datasource.driver-class-name", () -> "org.postgresql.Driver");

    registry.add("spring.jpa.database-platform", () -> "org.hibernate.dialect.PostgreSQLDialect");
    registry.add("spring.jpa.generate-ddl", () -> "true");

    registry.add("greeting.database", () -> "POSTGRES");
  }

  @BeforeAll
  public static void beforeAll() {
    CONTAINER.withReuse(true).start();
  }
}
