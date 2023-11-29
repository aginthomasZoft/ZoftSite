package com.zoft.solutions.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.zoft.solutions.entity.BlogDetails;
import com.zoft.solutions.model.SearchRequestModel;
import com.zoft.solutions.service.BlogService;

import io.swagger.annotations.ApiOperation;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/blogs")
@CrossOrigin
public class BlogController {

    @Autowired
    private BlogService blogService;

    @PostMapping
    @ApiOperation(value="Store blog")
    public ResponseEntity<Object> addBlog(@RequestBody @Valid BlogDetails blog, BindingResult bindingResult) {
        ResponseEntity<BlogDetails> response = blogService.checkIfBlogIdExists(blog.getBlogId());

        try {
            if (response.getStatusCode() == HttpStatus.NOT_FOUND) {
                BlogDetails savedBlog = blogService.saveBlog(blog);
                return new ResponseEntity<>(savedBlog, HttpStatus.CREATED);
            } else {
                return new ResponseEntity<>("Blog with id " + blog.getBlogId() + " already exists", HttpStatus.CONFLICT);
            }
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping
    @ApiOperation(value="Get all blog details")
    public List<BlogDetails> getAllBlogs() {
        return blogService.getBlogs();
    }

    @GetMapping("/{blogId}")
    @ApiOperation(value = "Get by id blog")
    public ResponseEntity<Object> getBlogById(@PathVariable int blogId) {
        ResponseEntity<BlogDetails> response = blogService.checkIfBlogIdExists(blogId);

        if (response.getStatusCode() == HttpStatus.OK) {
            BlogDetails getBlog = response.getBody();
            return new ResponseEntity<>(getBlog, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Blog with id " + blogId + " is not present", HttpStatus.NOT_FOUND);
        }
    }


    @PostMapping("/delete/{blogId}")
    @ApiOperation(value="Change active status")
    public ResponseEntity<Object> setActiveForBlog(@PathVariable int blogId) {
    	
    	 ResponseEntity<BlogDetails> response = blogService.checkIfBlogIdExists(blogId);

    	 if (response.getStatusCode() == HttpStatus.OK) {
             blogService.setActiveForBlog(blogId,false);
             return new ResponseEntity<>("Status changed sucesssfully", HttpStatus.OK);
         } else {
             return new ResponseEntity<>("Blog with id " + blogId + " is not present", HttpStatus.NOT_FOUND);
         }
    }
    @GetMapping("/five")
    @ApiOperation(value="Search five blogs")
    public List<BlogDetails> getFiveBlogs() {
        return blogService.getFiveBlogs();
    }
    
    @PutMapping("/{blogId}")
    @ApiOperation(value="Update by id blog")
    public ResponseEntity<Object> updateBlogById(@PathVariable int blogId, @RequestBody BlogDetails blog) {
        try {
        	ResponseEntity<BlogDetails> response = blogService.checkIfBlogIdExists(blogId);

            if (response.getStatusCode() == HttpStatus.OK) {
                // Update the existing blog
                BlogDetails updatedBlog = blogService.updateBlog(blogId, blog);
                return new ResponseEntity<>(updatedBlog, HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Blog not found for id: " + blogId, HttpStatus.NOT_FOUND);
            }
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }


    @DeleteMapping("/{blogId}")
    @ApiOperation(value="Delete blog")
    public ResponseEntity<Object> deleteBlogById(@PathVariable int blogId) {
    	ResponseEntity<BlogDetails> response = blogService.checkIfBlogIdExists(blogId);

        if (response.getStatusCode() == HttpStatus.OK) {
            blogService.deleteBLog(blogId);
            return new ResponseEntity<>("Blog deleted successfully", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Blog not found for id: " + blogId, HttpStatus.NOT_FOUND);
        }
    }


    @PostMapping("/search")
    @ApiOperation(value = "Search blog")
    public List<BlogDetails> searchByTitleBlogs(@RequestBody SearchRequestModel searchRequest) {
        return blogService.searchBlogs(searchRequest.getSearchKey(), searchRequest.getActive(), searchRequest.getStatus());
    }


 // Handle image upload
    @PostMapping("/image/upload")
    @ApiOperation(value="Upload cover image")
    public ResponseEntity<String> uploadImage(@RequestParam("File") MultipartFile file, @RequestParam("Blog Id") int blogId) throws IOException {
    	ResponseEntity<BlogDetails> response = blogService.checkIfBlogIdExists(blogId);

        if (response.getStatusCode() == HttpStatus.OK) {
            return blogService.blogImageUpload(file, blogId);
        } else {
            return new ResponseEntity<>("Blog not found for id: " + blogId, HttpStatus.NOT_FOUND);
        }
    }

    // Get image by ID
    @GetMapping("/image/{blogId}")
    @ApiOperation(value="Get cover image by id")
    public ResponseEntity<String> getImages(@PathVariable int blogId) {
    	ResponseEntity<BlogDetails> response = blogService.checkIfBlogIdExists(blogId);

        if (response.getStatusCode() == HttpStatus.OK) {
            return ResponseEntity.ok(blogService.getImageById(blogId));
        } else {
            return new ResponseEntity<>("Blog not found for id: " + blogId, HttpStatus.NOT_FOUND);
        }
    }

    // Delete an image by ID
    @DeleteMapping("/image/delete/{blogId}")
    @ApiOperation(value="Delete cover image by id")
    public ResponseEntity<String> deleteImage(@PathVariable int blogId) {
    	ResponseEntity<BlogDetails> response = blogService.checkIfBlogIdExists(blogId);

        if (response.getStatusCode() == HttpStatus.OK) {
            return blogService.blogImageDelete(blogId);
        } else {
            return new ResponseEntity<>("Blog not found for id: " + blogId, HttpStatus.NOT_FOUND);
        }
    }



}
