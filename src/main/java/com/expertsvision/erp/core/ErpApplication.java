package com.expertsvision.erp.core;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.UserDetailsServiceAutoConfiguration;


@SpringBootApplication
@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class,
								  HibernateJpaAutoConfiguration.class,
								  UserDetailsServiceAutoConfiguration.class})
public class ErpApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(ErpApplication.class, args);
	}

}
