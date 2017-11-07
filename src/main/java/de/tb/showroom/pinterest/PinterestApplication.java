package de.tb.showroom.pinterest;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import de.tb.showroom.pinterest.model.Pin;
import de.tb.showroom.pinterest.repositories.PinRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;

import java.io.IOException;
import java.io.InputStream;
import java.util.Set;

@EnableAsync
@SpringBootApplication
public class PinterestApplication {

	public static void main(String[] args) {
		SpringApplication.run(PinterestApplication.class, args);
	}

	@Bean
	CommandLineRunner runner(PinRepository pinRepository) {
		return args -> {
			// read json and write to db
			ObjectMapper mapper = new ObjectMapper();
			TypeReference<Set<Pin>> typeReference = new TypeReference<Set<Pin>>(){};
			InputStream inputStream = TypeReference.class.getResourceAsStream("/testdata/pins.json");
			try {
				Set<Pin> pins = mapper.readValue(inputStream, typeReference);
				pinRepository.save(pins);
				System.out.println("Pins Saved!");
			} catch (IOException e){
				System.out.println("Unable to save pins: " + e.getMessage());
			}
		};
	}
}
