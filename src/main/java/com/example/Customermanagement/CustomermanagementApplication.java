package com.example.Customermanagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.logging.Logger;

@SpringBootApplication
@RestController
public class CustomermanagementApplication {


	public static void main(String[] args) {

        SpringApplication.run(CustomermanagementApplication.class, args);
	}


    @GetMapping("/welcome")
    public String welcome(){
        return "server running on the port 8099";
    }



}
