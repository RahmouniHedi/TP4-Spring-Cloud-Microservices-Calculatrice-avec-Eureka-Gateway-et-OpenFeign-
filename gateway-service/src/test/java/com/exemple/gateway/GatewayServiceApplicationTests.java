package com.exemple.gateway;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.context.ApplicationContext;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.util.Arrays;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
    properties = {
        "management.endpoint.gateway.access=unrestricted",
        "management.endpoints.web.exposure.include=gateway,health,info"
    })
class GatewayServiceApplicationTests {

    @LocalServerPort
    private int port;

    @Autowired
    private ApplicationContext applicationContext;

    @Test
    void testActuatorRoutes() {
        try {
            Class<?> endpoint = Class.forName("org.springframework.cloud.gateway.actuate.GatewayControllerEndpoint");
            System.out.println("Is GatewayControllerEndpoint bean present: " + applicationContext.getBeanNamesForType(endpoint).length);
        } catch (ClassNotFoundException e) {
            System.out.println("GatewayControllerEndpoint NOT found: " + e.getMessage());
        }

        WebTestClient client = WebTestClient.bindToServer()
                .baseUrl("http://localhost:" + port)
                .build();

        client.get()
                .uri("/actuator")
                .exchange()
                .expectBody(String.class)
                .consumeWith(response -> {
                    System.out.println("Actuator /actuator response: " + response.getResponseBody());
                });

        client.get()
                .uri("/actuator/gateway/routes")
                .exchange()
                .expectStatus().isOk()
                .expectBody(String.class)
                .consumeWith(response -> {
                    System.out.println("Actuator /actuator/gateway/routes response status: " + response.getStatus());
                    System.out.println("Actuator /actuator/gateway/routes response body: " + response.getResponseBody());
                });
    }
}
