package com.jetbrains.testcontainersdemo;

import com.mysql.cj.jdbc.integration.c3p0.MysqlConnectionTester;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class CustomerIntegrationTests extends AbstractTest {

    @Autowired
    private CustomerDao customerDao;

    @Test
    void when_using_a_clean_db_this_should_be_empty() {
        List<Customer> customers = customerDao.findAll();
        assertThat(customers).hasSize(2);
    }


    @Test
    void add() throws Exception{
        int add = customerDao.add(new Customer(3L, "John", "Doe"));
        System.out.println("add = " + add);
        List<Customer> customers = customerDao.findAll();
        assertThat(customers).hasSize(3);
    }
}
