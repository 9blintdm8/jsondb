package com.usersData;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.usersData.domain.UserClass;
import com.usersData.service.UserService;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

@SpringBootApplication
public class JsondbApplication {

	public static void main(String[] args) {
  		//
		SpringApplication.run(JsondbApplication.class, args);

//		Logger logger = Logger.getLogger("MyLog");
//		FileHandler fh;
//
//		try {
//
//			// This block configure the logger with handler and formatter
//			fh = new FileHandler("C:/Projektek/jsondb/MyLogFile.log");
//			logger.addHandler(fh);
//			SimpleFormatter formatter = new SimpleFormatter();
//			fh.setFormatter(formatter);
//
//			// the following statement is used to log any messages
//			logger.info("My first log");
//
//			} catch (SecurityException e) {
//				e.printStackTrace();
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
//
//			logger.info("Hi How r u?");

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