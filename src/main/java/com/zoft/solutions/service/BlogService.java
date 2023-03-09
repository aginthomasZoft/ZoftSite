package com.zoft.solutions.service;


import com.zoft.solutions.entity.Blogdetails;
import com.zoft.solutions.respository.BlogRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class BlogService {

    @Autowired
    private BlogRepository repository;


    public Blogdetails saveProduct(Blogdetails blog) {
        return repository.save(blog);
    }

    public List<Blogdetails> getBlogs() {
        return repository.findAll();
    }

    public Blogdetails getBlogById(int blogId) {
        return repository.findById(blogId).get();
    }


    public Blogdetails updateBlog(int blogId, Blogdetails blogRequest) {
        // get the product from DB by id
        // update with new value getting from request
    	Blogdetails existingProduct = repository.findById(blogId).get(); // DB
        existingProduct.setTitle(blogRequest.getTitle());
        existingProduct.setContent(blogRequest.getContent());
        existingProduct.setBlogUrl(blogRequest.getBlogUrl());
        existingProduct.setStatus(blogRequest.getStatus());
        existingProduct.setCoverImage(blogRequest.getCoverImage());
        existingProduct.setCategory(blogRequest.getCategory());
        existingProduct.setTags(blogRequest.getTags());
        existingProduct.setPreview(blogRequest.getPreview());
        existingProduct.setSeo(blogRequest.getSeo());
        existingProduct.setCreatedDate(blogRequest.getCreatedDate());
        existingProduct.setUpdatedDate(blogRequest.getUpdatedDate());
        existingProduct.setPublishingDate(blogRequest.getPublishingDate());
        existingProduct.setCreatedBy(blogRequest.getCreatedBy());
        return repository.save(existingProduct);
    }


    public long deleteBLog(int blogId) {
        repository.deleteById(blogId);
        return repository.count();
    }

    public Blogdetails updateBlogByFields(int blogId, Map<String, Object> fields) {
        Optional<Blogdetails> existingBlog = repository.findById(blogId);

        if (existingBlog.isPresent()) {
            fields.forEach((key, value) -> {
                Field field = ReflectionUtils.findField(Blogdetails.class, key);
                field.setAccessible(true);
                ReflectionUtils.setField(field, existingBlog.get(), value);
            });
            return repository.save(existingBlog.get());
        }
        return null;
    }
    
    public List<Blogdetails> getFiveBlogs() {
    	
        return repository.findFiveByDate();
    }

}
