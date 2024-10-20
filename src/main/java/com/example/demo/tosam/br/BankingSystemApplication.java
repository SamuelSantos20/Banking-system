package com.example.demo.tosam.br;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(basePackages = {"service", "dao","serviceImpl" , "dto" , "daoImpl" , "com.example.demo.tosam.br" ,  "controller", "model", "config" ,  "security" , "util"})
@EnableJpaRepositories(basePackages = "daoImpl")
@EntityScan(basePackages = "model")
public class BankingSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(BankingSystemApplication.class, args);
	}

}
