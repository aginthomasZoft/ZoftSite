package com.zoft.solutions.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.zoft.solutions.entity.Admin;
import com.zoft.solutions.respository.AdminRepository;
import com.zoft.solutions.util.JwtTokenUtil;
import com.zoft.solutions.util.ValidationUtils;

@Service
public class AdminService {

	@Autowired
	AdminRepository adminRepository;
	
	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	public ResponseEntity<String> checkAdmin(Admin adminDetails) {
		
		List<String> validationErrors = ValidationUtils.validateEntity(adminDetails);

        if (!validationErrors.isEmpty()) {
            throw new IllegalArgumentException(String.join(", ", validationErrors));
        }
	    Admin adminContains = adminRepository.findByUsername(adminDetails.getUsername());

	    if (adminContains != null) {
	        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
	        boolean isPasswordMatch = passwordEncoder.matches(adminDetails.getPassword(), adminContains.getPassword());

	        if (isPasswordMatch) {
	            // Authentication successful, return response with a token
	            String token = jwtTokenUtil.generateToken(adminContains.getUsername(), adminContains.getRole());
	            return new ResponseEntity<>(token, HttpStatus.OK);
	        }
	    }

	    // Authentication failed, return error response
	    return new ResponseEntity<>("Login failed", HttpStatus.UNAUTHORIZED);
	}

}
