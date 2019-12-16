package com.example.geo;

import com.example.geo.business.GeoLocator;
import com.example.geo.utils.mapper.XMLConsumer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;
import java.io.InputStream;

@SpringBootApplication
public class GeoApplication {

	public static void main(String[] args) {
		SpringApplication.run(GeoApplication.class, args);

		ApplicationContext applicationContext =
				new ClassPathXmlApplicationContext("ApplicationContext.xml");

/*
		// TODO : get xsd from url
		//GeoLocator geoLocatorSingleton = (GeoLocator)applicationContext.getBean("geoLocatorSingleton");
		//geoLocatorSingleton.setIsXSD();

		String url = "https://geocoder.ls.hereapi.com/6.2/xsd/LBSP-Search-Search.xsd?apiKey=zRKCNulsmituY-IuLoyca4d50QYtpjW4C4H5Do6d9MQ";

		try {
			InputStream is = XMLConsumer.getResponseFromURL(url, null);

			GeoLocator geoLocatorSingleton = (GeoLocator)applicationContext.getBean("geoLocatorSingleton");
			geoLocatorSingleton.setIsXSD(is);
		} catch (IOException e) {
			e.printStackTrace();
		};

 */
	}

}
