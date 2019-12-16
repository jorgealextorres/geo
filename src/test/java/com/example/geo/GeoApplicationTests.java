package com.example.geo;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.PropertySource;
import org.springframework.test.context.ContextConfiguration;

@SpringBootTest
@PropertySource("classpath*:test_application.properties")
@ContextConfiguration({"classpath*:test_ApplicationContext.xml"})
class GeoApplicationTests {

	@Test
	void contextLoads() {
	}

}
