package com.zoft.solutions.service;

import com.zoft.solutions.entity.ContactUs;

import com.zoft.solutions.respository.ContactRepository;
import com.zoft.solutions.util.ValidationUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class ContactUsService {
	
	 @Autowired
	    private ContactRepository repository;


	    public ContactUs saveContactDetail(ContactUs contactDetails) {
	    	
	    	List<String> validationErrors = ValidationUtils.validateEntity(contactDetails);

	        if (!validationErrors.isEmpty()) {
	            throw new IllegalArgumentException(String.join(", ", validationErrors));
	        }
	        return repository.save(contactDetails);
	    }

	    public List<ContactUs> getContactDetails() {
	        return repository.findAll();
	    }

	    public ContactUs getContactDetailById(int contactId) {
	        return repository.findById(contactId).get();
	    }


	    public ContactUs updateContactDetail(int contactId, ContactUs updatedContactDetails) {
	    	
	    	List<String> validationErrors = ValidationUtils.validateEntity(updatedContactDetails);

	        if (!validationErrors.isEmpty()) {
	            throw new IllegalArgumentException(String.join(", ", validationErrors));
	        }
	        // updating with new value getting from request
	    	ContactUs existingContact = repository.findById(contactId).get(); // DB
	    	existingContact.setFullName(updatedContactDetails.getFullName());
	    	existingContact.setEmail(updatedContactDetails.getEmail());
	    	existingContact.setCompanyName(updatedContactDetails.getCompanyName());
	    	existingContact.setPhone(updatedContactDetails.getPhone());
	    	existingContact.setDesignation(updatedContactDetails.getDesignation());
	    	existingContact.setMessage(updatedContactDetails.getMessage());
	        return repository.save(existingContact);
	    }


	    public long deleteContactDetail(int contactId) {
	        repository.deleteById(contactId);
	        return repository.count();
	    }

	    public ContactUs updateContactDetailByFields(int contactId, Map<String, Object> fields) {
	        Optional<ContactUs> existingContact = repository.findById(contactId);

	        if (existingContact.isPresent()) {
	            fields.forEach((key, value) -> {
	                Field field = ReflectionUtils.findField(ContactUs.class, key);
	                field.setAccessible(true);
	                ReflectionUtils.setField(field, existingContact.get(), value);
	            });
	            return repository.save(existingContact.get());
	        }
	        return null;
	    }
	    
	   
}
