package com.zoft.solutions.service;

import com.zoft.solutions.entity.ContactUs;

import com.zoft.solutions.respository.ContactRepository;

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


	    public ContactUs saveContactDetail(ContactUs contact) {
	        return repository.save(contact);
	    }

	    public List<ContactUs> getContactDetails() {
	        return repository.findAll();
	    }

	    public ContactUs getContactDetailById(int contactId) {
	        return repository.findById(contactId).get();
	    }


	    public ContactUs updateContactDetail(int contactId, ContactUs contactRequest) {
	        // get the product from DB by id
	        // update with new value getting from request
	    	ContactUs existingContact = repository.findById(contactId).get(); // DB
	    	existingContact.setFullName(contactRequest.getFullName());
	    	existingContact.setEmail(contactRequest.getEmail());
	    	existingContact.setCompanyName(contactRequest.getCompanyName());
	    	existingContact.setPhone(contactRequest.getPhone());
	    	existingContact.setDesignation(contactRequest.getDesignation());
	    	existingContact.setMessage(contactRequest.getMessage());
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
