package com.jetbrains.testcontainersdemo;

import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

@Testcontainers
@Sql(scripts = "classpath:schema.sql")
public class AbstractTest {
    @Container
    private static MySQLContainer container = new MySQLContainer("mysql:8.0.26");


    @DynamicPropertySource
    public static void overrideProps(org.springframework.test.context.DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", container::getJdbcUrl);
        registry.add("spring.datasource.username", container::getUsername);
        registry.add("spring.datasource.password", container::getPassword);
    }

}
