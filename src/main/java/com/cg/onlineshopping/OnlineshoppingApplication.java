package com.cg.onlineshopping;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableTransactionManagement
@SpringBootApplication
public class OnlineshoppingApplication {

	public static void main(String[] args) {
		SpringApplication.run(OnlineshoppingApplication.class, args);
	}

}
