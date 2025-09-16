package com.example.Customermanagement;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class CustomermanagementApplication {

    private static final Logger logger = LoggerFactory.getLogger(CustomermanagementApplication.class);


	public static void main(String[] args) {

        logger.info("application started successfully");
        System.out.println("application started successfully");

        SpringApplication.run(CustomermanagementApplication.class, args);
	}


    @GetMapping("/")
    public String welcome(){
        return "server running on the port 8099";
    }







}
