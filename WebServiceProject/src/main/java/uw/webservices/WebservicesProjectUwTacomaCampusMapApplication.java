package uw.webservices;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class WebservicesProjectUwTacomaCampusMapApplication {

	public static void main(String[] args) {

		DatabaseInitializer.initializeDatabase();
		
		SpringApplication.run(WebservicesProjectUwTacomaCampusMapApplication.class, args);
	}
}
