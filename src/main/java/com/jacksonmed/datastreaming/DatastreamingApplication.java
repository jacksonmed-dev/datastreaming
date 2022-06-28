package com.jacksonmed.datastreaming;

import com.datastax.oss.driver.api.core.CqlSession;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@SpringBootApplication
public class DatastreamingApplication {

	public static void main(String[] args) {
		SpringApplication.run(DatastreamingApplication.class, args);
	}

}
