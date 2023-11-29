package com.zoft.solutions.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.zoft.solutions.entity.Admin;
import com.zoft.solutions.service.AdminService;

import io.swagger.annotations.ApiOperation;

@Controller
@CrossOrigin
public class LoginController {

	@Autowired
    AdminService adminService;
	
	@PostMapping("/login")
	@ApiOperation(value = "Check login credentials")
	public ResponseEntity<String> login(@RequestBody Admin admin) {
	    try {
	        ResponseEntity<String> response = adminService.checkAdmin(admin);

	        if (response.getStatusCode() == HttpStatus.UNAUTHORIZED) {
	            // Unauthorized access, return a specific response
	            return new ResponseEntity<>("Wrong credentials", HttpStatus.UNAUTHORIZED);
	        } else {
	            // Successful login
	            return new ResponseEntity<>("Login successful", HttpStatus.OK);
	        }
	    } catch (IllegalArgumentException e) {
	        // Handle other exceptions
	        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
	    }
	}
}
