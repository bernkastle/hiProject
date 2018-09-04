package com.hiProject;

import com.hiProject.model.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient
public class ApplicationTests {

    @Autowired
    private WebTestClient webTestClient;

    @Test
    public void userServiceTest(){
        this.webTestClient.get().uri("/greeting")
                .exchange()
                .expectBody(String.class).isEqualTo("greeting");
    }
}
