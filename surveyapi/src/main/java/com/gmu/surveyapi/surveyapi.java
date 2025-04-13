/*
Author: Qasim Shahid
SWE 645 Assignment / HW 3

Entry point for the Spring Boot application.
This class launches the embedded Tomcat server, connects to the MySQL database, and hosts the REST API.
*/

package com.gmu.surveyapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class surveyapi {

	public static void main(String[] args) {
		SpringApplication.run(surveyapi.class, args);
	}

}
