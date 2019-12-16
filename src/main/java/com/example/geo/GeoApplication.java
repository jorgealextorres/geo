package com.example.geo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

@SpringBootApplication
public class GeoApplication {

	public static void main(String[] args) {
		SpringApplication.run(GeoApplication.class, args);

		ApplicationContext applicationContext =
				new ClassPathXmlApplicationContext("ApplicationContext.xml");
	}

}
