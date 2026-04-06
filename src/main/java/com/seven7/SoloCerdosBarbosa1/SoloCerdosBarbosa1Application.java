package com.seven7.SoloCerdosBarbosa1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SoloCerdosBarbosa1Application {

	public static void main(String[] args) {
		SpringApplication.run(SoloCerdosBarbosa1Application.class, args);

		System.out.println("========================================");
		System.out.println("🐷 Solo Cerdos Barbosa API");
		System.out.println("========================================");
		System.out.println("✅ Aplicación iniciada correctamente!");
		System.out.println("📌 Página principal: http://localhost:8080/");
		System.out.println("📌 API Productos: http://localhost:8080/api/productos");
		System.out.println("📌 API Clientes: http://localhost:8080/api/clientes");
		System.out.println("📌 API Ventas: http://localhost:8080/api/ventas");
		System.out.println("📌 H2 Console: http://localhost:8080/h2-console");
		System.out.println("========================================");
	}
}
