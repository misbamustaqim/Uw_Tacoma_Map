package uw.webservices;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

public class ServletInitializer extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		
		DatabaseInitializer.initializeDatabase();
		
		return application.sources(WebservicesProjectUwTacomaCampusMapApplication.class);
	}

}
