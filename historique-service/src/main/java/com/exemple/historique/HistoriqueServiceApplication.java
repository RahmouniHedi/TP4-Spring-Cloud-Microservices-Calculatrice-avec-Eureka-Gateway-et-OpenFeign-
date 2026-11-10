package com.exemple.historique;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableFeignClients
@OpenAPIDefinition(servers = @Server(url = "/historique-service"))
public class HistoriqueServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(HistoriqueServiceApplication.class, args);
	}

}
