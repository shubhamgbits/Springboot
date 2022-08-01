package com.project.urlshortener;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication //Tells spring boot that this is the starting point of our application. 
@ComponentScan("com.project") //Scan components from all packages mentioned. 
public class UrlshortenerApplication {

	public static void main(String[] args) {
		SpringApplication.run(UrlshortenerApplication.class, args);
		
		/* Spring boot has a static method which starts the spring application. 
		 * It creates a servlet container & run it. 
		 */
	}

}
