package com.practice.recruitmentservice.integration;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
@ContextConfiguration(initializers = BaseIntegrationTest.Initializer.class)
@ActiveProfiles("test")
@Testcontainers
public class BaseIntegrationTest {

  @Container
  private static final MySQLContainer container = new MySQLContainer<>(DockerImageName.parse("mysql:5.7"));

  static {
    container.withDatabaseName("recruitment_test_db")
        .withUsername("test")
        .withPassword("pwd")
        .withInitScript("init-test.sql");
    container.start();
  }

  static class Initializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {

    @Override
    public void initialize( ConfigurableApplicationContext applicationContext) {
      System.out.println(container.getJdbcUrl());
      TestPropertyValues.of("spring.datasource.url=" + container.getJdbcUrl(),
          "spring.datasource.username=" + container.getUsername(),
          "spring.datasource.port" + container.getFirstMappedPort(),
          "spring.datasource.password=" + container.getPassword()).applyTo(applicationContext);
    }

  }

  @AfterAll
  static void afterAll() {
    container.close();
  }
}
