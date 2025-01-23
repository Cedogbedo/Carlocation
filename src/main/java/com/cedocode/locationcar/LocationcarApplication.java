package com.cedocode.locationcar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;

import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.cedocode.locationcar.servicesss.UserServiceImpl;

import jakarta.annotation.PostConstruct;




@SpringBootApplication
public class LocationcarApplication {
	
	@Autowired
    private UserServiceImpl userService;

	public static void main(String[] args) {
		SpringApplication.run(LocationcarApplication.class, args);
	}

	
	
	@PostConstruct
    public void init() {
        userService.createAdminUserIfNotExists();
    }
}
