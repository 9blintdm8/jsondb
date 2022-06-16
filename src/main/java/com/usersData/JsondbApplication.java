package com.users_data;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.users_data.domain.UserClass;
import com.users_data.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@SpringBootApplication
public class JsondbApplication {

	public static void main(String[] args) {
  		//
		SpringApplication.run(JsondbApplication.class, args);
	}

	@Bean
	CommandLineRunner runner(UserService userService){
		return args -> {
			// read JSON and load json
			ObjectMapper mapper = new ObjectMapper();
			TypeReference<List<UserClass>> typeReference = new TypeReference<List<UserClass>>(){};
			InputStream inputStream = TypeReference.class.getResourceAsStream("/json/users.json");
			try {
				List<UserClass> userClasses = mapper.readValue(inputStream,typeReference);
				userService.save(userClasses);
				System.out.println("Users Saved!");
			} catch (IOException e){
				System.out.println("Unable to save users: " + e.getMessage());
			}
		};
	}
}