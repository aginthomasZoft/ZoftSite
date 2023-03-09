package com.zoft.solutions.controller;

import com.zoft.solutions.entity.Blogdetails;
import com.zoft.solutions.service.BlogService;

import io.swagger.annotations.ApiOperation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/Blog")
@CrossOrigin
public class BlogController {

    @Autowired
    private BlogService service;

    @PostMapping
    @ApiOperation(value="Store Blog")
    public Blogdetails addBlog(@RequestBody Blogdetails blog) {
        return service.saveProduct(blog);
    }

    @GetMapping
    @ApiOperation(value="Search Blog")
    public List<Blogdetails> getAllBlogs() {
        return service.getBlogs();
    }

    @GetMapping("/{blogId}")
    @ApiOperation(value="Search By Id Blog")
    public Blogdetails getBlogById(@PathVariable int blogId) {
        return service.getBlogById(blogId);
    }

    @GetMapping("/five")
    @ApiOperation(value="Search Five Blogs")
    public List<Blogdetails> getFiveBlogs() {
        return service.getFiveBlogs();
    }
    
    @PutMapping("/{blogId}")
    @ApiOperation(value="Update By Id Blog")
    public Blogdetails updateBlogById(@PathVariable int blogId, @RequestBody Blogdetails blog) {
       return service.updateBlog(blogId, blog);
    }

    @PatchMapping("/{blogId}")
    public Blogdetails updateBlogFields(@PathVariable int blogId,@RequestBody Map<String, Object> fields){
        return service.updateBlogByFields(blogId,fields);
    }

    @DeleteMapping("/{blogId}")
    @ApiOperation(value="Delete Blog")
    public long deleteBlogById(@PathVariable int blogId) {
        return service.deleteBLog(blogId);
    }






}
