package com.amex.sms.school;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.PostConstruct;


@SpringBootApplication
//@PropertySource("C:/apps/secrets/app.secrets")
public class SchoolApplication {

	@Autowired
	Environment environment;



	Logger logger = LoggerFactory.getLogger(SchoolApplication.class);


	public static void main(String[] args) {
		SpringApplication.run(SchoolApplication.class, args);
	}


	@PostConstruct
	public void init(){
		logger.info("Our school name is {0}", environment.getProperty("school-name"));
	}
}
