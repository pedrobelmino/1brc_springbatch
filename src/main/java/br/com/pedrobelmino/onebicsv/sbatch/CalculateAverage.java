package br.com.pedrobelmino.onebicsv.sbatch;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;


@SpringBootApplication
public class CalculateAverage {

	public static void main(String[] args) {

		SpringApplication.exit((SpringApplication.run(CalculateAverage.class, args)));
	}

}
