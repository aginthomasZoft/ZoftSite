package com.zoft.solutions.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;


import com.zoft.solutions.entity.NewsLetterDetails;
import com.zoft.solutions.respository.NewsLetterRepository;
import com.zoft.solutions.util.ValidationUtils;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class NewsLetterService {

	@Autowired
	
    private NewsLetterRepository repository;


    public NewsLetterDetails saveNewsLetter(NewsLetterDetails newsLetterDetails) {
    	
    	List<String> validationErrors = ValidationUtils.validateEntity(newsLetterDetails);

        if (!validationErrors.isEmpty()) {
            throw new IllegalArgumentException(String.join(", ", validationErrors));
        }
        return repository.save(newsLetterDetails);
    }

    public List<NewsLetterDetails> getNewsLetter() {
        return repository.findAll();
    }

    public NewsLetterDetails getNewsLetterById(int newsletterId) {
        return repository.findById(newsletterId).get();
    }


    public NewsLetterDetails updateNewsLetter(int newsletterId, NewsLetterDetails newsRequest) {
    	
    	List<String> validationErrors = ValidationUtils.validateEntity(newsRequest);

        if (!validationErrors.isEmpty()) {
            throw new IllegalArgumentException(String.join(", ", validationErrors));
        }
        // get the product from DB by id
        // update with new value getting from request
    	NewsLetterDetails existingProduct = repository.findById(newsletterId).get(); // DB
        existingProduct.setEmail(newsRequest.getEmail());
        return repository.save(existingProduct);
    }


    public long deleteNewsLetter(int newsletterId) {
        repository.deleteById(newsletterId);
        return repository.count();
    }

    public NewsLetterDetails updateNewsLetterByFields(int newsletterId, Map<String, Object> fields) {
        Optional<NewsLetterDetails> existingBlog = repository.findById(newsletterId);

        if (existingBlog.isPresent()) {
            fields.forEach((key, value) -> {
                Field field = ReflectionUtils.findField(NewsLetterDetails.class, key);
                field.setAccessible(true);
                ReflectionUtils.setField(field, existingBlog.get(), value);
            });
            return repository.save(existingBlog.get());
        }
        return null;
    }
   
}
