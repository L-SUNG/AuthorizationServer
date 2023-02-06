package com.example.authorizationserver.web;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PageControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void indexLoading() {
        String body = this.restTemplate.getForObject("/", String.class);

        // HTML의 body에서 취득한 문자열 확인
        assertThat(body).contains("AuthorizationServer");
    }

}
