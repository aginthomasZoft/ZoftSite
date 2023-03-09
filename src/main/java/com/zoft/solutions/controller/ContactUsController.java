package com.zoft.solutions.controller;


import com.zoft.solutions.entity.ContactUs;

import com.zoft.solutions.service.ContactUsService;

import io.swagger.annotations.ApiOperation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/ContactUs")
@CrossOrigin
public class ContactUsController {

	 @Autowired
	    private ContactUsService service;

	    @PostMapping
	    @ApiOperation(value="Store Contact details")
	    public ContactUs addContactDetail(@RequestBody ContactUs contact) {
	        return service.saveContactDetail(contact);
	    }

	    @GetMapping
	    @ApiOperation(value="Search Contact details")
	    public List<ContactUs> getAllContactDetails() {
	        return service.getContactDetails();
	    }

	    @GetMapping("/{contactId}")
	    @ApiOperation(value="Search By Id Contact details")
	    public ContactUs getContactDetailById(@PathVariable int contactId) {
	        return service.getContactDetailById(contactId);
	    }


	    @PutMapping("/{contactId}")
	    @ApiOperation(value="Update By Id Contact details")
	    public ContactUs updateContactDetailById(@PathVariable int contactId, @RequestBody ContactUs contact) {
	       return service.updateContactDetail(contactId, contact);
	    }

	    @PatchMapping("/{contactId}")
	    public ContactUs updateContactDetailFields(@PathVariable int contactId,@RequestBody Map<String, Object> fields){
	        return service.updateContactDetailByFields(contactId,fields);
	    }

	    @DeleteMapping("/{contactId}")
	    @ApiOperation(value="Delete Contact details")
	    public long deleteContactDetailById(@PathVariable int contactId) {
	        return service.deleteContactDetail(contactId);
	    }

}
