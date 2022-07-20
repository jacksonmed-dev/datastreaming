package com.jacksonmed.datastreaming;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
// @SpringBootApplication
public class DatastreamingApplication {

	public static void main(String[] args) {
		SpringApplication.run(DatastreamingApplication.class, args);
	}

}
