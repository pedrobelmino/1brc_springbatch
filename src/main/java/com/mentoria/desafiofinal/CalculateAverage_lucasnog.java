package com.mentoria.desafiofinal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;


@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class CalculateAverage_lucasnog {

	public static void main(String[] args) {
		SpringApplication.run(CalculateAverage_lucasnog.class, args);
	}

}
