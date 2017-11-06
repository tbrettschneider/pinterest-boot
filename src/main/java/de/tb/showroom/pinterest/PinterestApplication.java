package de.tb.showroom.pinterest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableAsync
@SpringBootApplication
public class PinterestApplication {

	public static void main(String[] args) {
		SpringApplication.run(PinterestApplication.class, args);
	}
}
