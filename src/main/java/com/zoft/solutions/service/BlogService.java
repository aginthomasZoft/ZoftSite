package com.zoft.solutions.service;


import java.io.IOException;
import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.multipart.MultipartFile;

import com.zoft.solutions.entity.BlogDetails;
import com.zoft.solutions.respository.BlogRepository;
import com.zoft.solutions.util.BlogSpecificationsUtil;
import com.zoft.solutions.util.MainImageUtil;
import com.zoft.solutions.util.ValidationUtils;

@Service
public class BlogService {

    @Autowired
    private BlogRepository repository;
    
    @Autowired
    private MainImageUtil imageUtil;
    

    public BlogDetails saveBlog(BlogDetails blogDetails) {
    	 List<String> validationErrors = ValidationUtils.validateEntity(blogDetails);

         if (!validationErrors.isEmpty()) {
             throw new IllegalArgumentException(String.join(", ", validationErrors));
         }

         // Save the blog if validation is successful
         return repository.save(blogDetails);
    }

    public List<BlogDetails> getBlogs() {
        return repository.findAllByOrderByPublishingDateDesc();
    }

    public BlogDetails getBlogById(int blogId) {
        return repository.findById(blogId).get();
    }


    public BlogDetails updateBlog(int blogId, BlogDetails updatedRequest) {
   
   	 List<String> validationErrors = ValidationUtils.validateEntity(updatedRequest);

         if (!validationErrors.isEmpty()) {
             throw new IllegalArgumentException(String.join(", ", validationErrors));
         }
        // updating with new value getting from request
    	BlogDetails existingProduct = repository.findById(blogId).get(); // DB
        existingProduct.setTitle(updatedRequest.getTitle());
        existingProduct.setContent(updatedRequest.getContent());
        existingProduct.setBlogUrl(updatedRequest.getBlogUrl());
        existingProduct.setStatus(updatedRequest.getStatus());
        existingProduct.setCoverImage(updatedRequest.getCoverImage());
        existingProduct.setTags(updatedRequest.getTags());
        existingProduct.setPreview(updatedRequest.getPreview());
        existingProduct.setCreatedDate(updatedRequest.getCreatedDate());
        existingProduct.setUpdatedDate(updatedRequest.getUpdatedDate());
        existingProduct.setPublishingDate(updatedRequest.getPublishingDate());
        existingProduct.setCreatedBy(updatedRequest.getCreatedBy());
        existingProduct.setMetaTitle(updatedRequest.getMetaTitle());
        existingProduct.setMetaDescription(updatedRequest.getMetaDescription());
        existingProduct.setPublished(updatedRequest.isPublished());
        existingProduct.setActive(updatedRequest.isActive());
        existingProduct.setCompany(updatedRequest.getCompany());
        existingProduct.setAlternateImageName(updatedRequest.getAlternateImageName());
        return repository.save(existingProduct);
    }


    public long deleteBLog(int blogId) {
    	blogImageDelete(blogId);
        repository.deleteById(blogId);
        return repository.count();
    }

    public BlogDetails updateBlogByFields(int blogId, Map<String, Object> fields) {
        Optional<BlogDetails> existingBlog = repository.findById(blogId);

        if (existingBlog.isPresent()) {
            fields.forEach((key, value) -> {
                Field field = ReflectionUtils.findField(BlogDetails.class, key);
                field.setAccessible(true);
                ReflectionUtils.setField(field, existingBlog.get(), value);
            });
            return repository.save(existingBlog.get());
        }
        return null;
    }
    public void setActiveForBlog(int blogId, boolean active) {
    	repository.setActiveForBlogId(blogId, active);
    }
    public List<BlogDetails> getFiveBlogs() {
    	
        return repository.findFiveByDate();
    }

    public List<BlogDetails> searchBlogs(String title, Boolean active,String status){
    	
    	// Create a specification using the static method from BlogSpecifications
        Specification<BlogDetails> spec = BlogSpecificationsUtil.findByTitleAndStatus(title, status);

        if (active != null) {
            // If 'active' is provided, add an additional condition to the specification
            spec = spec.and((root, query, criteriaBuilder) ->
                    criteriaBuilder.equal(root.get("active"), active));
        }

        // Using the specification and apply sorting
        return repository.findAll(spec, Sort.by(Sort.Direction.DESC, "publishingDate"));
  	
    }
    
 public ResponseEntity<String> blogImageUpload(MultipartFile file, int blogId) throws IOException {
    	
    	Map<String, Object> responseMap = imageUtil.uploadImage(file).getBody();
    	
    	repository.setImageDetails(blogId,(String) responseMap.get("blobName"),(String) responseMap.get("blobUrl"));
    	
    	return ResponseEntity.ok("Image uploaded successfully.");
    }
    
    public String getImageById(int blogId) {
        return repository.findCoverImageUrlByBlogId(blogId);
    }
    
    public ResponseEntity<String> blogImageDelete(int blogId) {
        Optional<BlogDetails> optionalImage = repository.findById(blogId);
        if (optionalImage.isPresent()) {
        	BlogDetails image = optionalImage.get();
            String blobName = image.getCoverImageName();

            imageUtil.deleteImage(blobName);
            repository.clearImageDetails(blogId);
            return ResponseEntity.ok("Image deleted successfully.");
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
    public ResponseEntity<BlogDetails> checkIfBlogIdExists(int blogId) {
        

        try {
        	BlogDetails blog = getBlogById(blogId);
            return new ResponseEntity<>(blog, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    
}
