package com.example.SpringLaptop.Controller;

import com.example.SpringLaptop.Entities.Laptop;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class LaptopControllerTest {


    private TestRestTemplate testRestTemplate;
    @Autowired
    private RestTemplateBuilder restTemplateBuilder;

    @LocalServerPort
    int port;


    @BeforeEach
    void setUp() {
    restTemplateBuilder = restTemplateBuilder.rootUri("http://localhost:" + port);
    testRestTemplate = new TestRestTemplate(restTemplateBuilder);
    }

    @Test
    void create() {
        ResponseEntity<Laptop[]> response =
            testRestTemplate.getForEntity("/laptop", Laptop[].class);

        assertEquals(HttpStatus.OK,response.getStatusCode());
    }

    @Test
    void findAll() {
        ResponseEntity<Laptop[]> response =
        testRestTemplate.getForEntity("/laptop", Laptop[].class);

        assertEquals(200, response.getStatusCodeValue());
        List<Laptop> laptops = Arrays.asList(response.getBody());
        System.out.println(laptops.size());
    }

    @Test
    void findById() {
        ResponseEntity<Laptop> response =
            testRestTemplate.getForEntity("/laptop/1",Laptop.class);

        assertEquals(404, response.getStatusCodeValue());


    }

    @Test
    void update() {
        ResponseEntity<Laptop[]> response =
        testRestTemplate.getForEntity("/laptop",Laptop[].class);

        assertEquals(200,response.getStatusCodeValue());
    }

    @Test
    void delete() {
        ResponseEntity<Laptop> response =
                testRestTemplate.getForEntity("/laptop/2", Laptop.class);

        assertEquals(HttpStatus.NOT_FOUND,response.getStatusCode());
    }

    @Test
    void deleteAll() {
        ResponseEntity<Laptop[]> response =
                testRestTemplate.getForEntity("/laptop", Laptop[].class);

        assertEquals(HttpStatus.OK,response.getStatusCode());
    }
}