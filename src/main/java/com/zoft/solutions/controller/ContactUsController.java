package com.zoft.solutions.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zoft.solutions.entity.ContactUs;
import com.zoft.solutions.service.ContactUsService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/contactUs")
@CrossOrigin
public class ContactUsController {

	 @Autowired
	    private ContactUsService service;

	    @PostMapping
	    @ApiOperation(value="Store Contact details")
	    public ResponseEntity<Object> addContactDetail(@RequestBody ContactUs contact) {
	        try {
	        	ContactUs responce =  service.saveContactDetail(contact);
	        	return new ResponseEntity<>(responce, HttpStatus.CREATED);
    	    } catch (IllegalArgumentException e) {
    	        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    	    }
	    }

	    @GetMapping
	    @ApiOperation(value="Get all contact details")
	    public List<ContactUs> getAllContactDetails() {
	        return service.getContactDetails();
	    }

	    @GetMapping("/{contactId}")
	    @ApiOperation(value="Get contact details by id")
	    public ContactUs getContactDetailById(@PathVariable int contactId) {
	        return service.getContactDetailById(contactId);
	    }


	    @PutMapping("/{contactId}")
	    @ApiOperation(value="Update contact details by id")
	    public ResponseEntity<Object> updateContactDetailById(@PathVariable int contactId, @RequestBody ContactUs contact) {
	       try {
	    	   ContactUs responce =  service.updateContactDetail(contactId, contact);
	       return new ResponseEntity<>(responce, HttpStatus.CREATED);
	    } catch (IllegalArgumentException e) {
	        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
	    }
	    }


	    @DeleteMapping("/{contactId}")
	    @ApiOperation(value="Delete contact details by id")
	    public long deleteContactDetailById(@PathVariable int contactId) {
	        return service.deleteContactDetail(contactId);
	    }

}
