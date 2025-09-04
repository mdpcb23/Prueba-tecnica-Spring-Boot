package com.example.crudVuelos;

import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.OpenAPI;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CrudVuelosApplication {

	public static void main(String[] args) {
		SpringApplication.run(CrudVuelosApplication.class, args);
	}
	@Bean
	public OpenAPI  clienteAPI() {
		return new OpenAPI().info(new Info()
				.title("CRUD vuelos")
				.version("1.0")
				.description("API para filtrar, buscar, editar, agregar y eliminar vuelos"));
	}

}
