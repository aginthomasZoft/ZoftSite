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

import com.zoft.solutions.entity.NewsLetterDetails;
import com.zoft.solutions.service.NewsLetterService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/newsLetter")
@CrossOrigin
public class NewsLetterController {

	@Autowired
    private NewsLetterService newsLetterService;

    @PostMapping
    @ApiOperation(value="Store newsletter")
    public ResponseEntity<Object> addNewsLetter(@RequestBody NewsLetterDetails newsletterId) {
    	
        try {
        	NewsLetterDetails responce = newsLetterService.saveNewsLetter(newsletterId);
        return new ResponseEntity<>(responce, HttpStatus.CREATED);
    } catch (IllegalArgumentException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }
    }

    @GetMapping
    @ApiOperation(value="get all newsletter details")
    public List<NewsLetterDetails> getNewsLetter() {
        return newsLetterService.getNewsLetter();
    }

    @GetMapping("/{newsletterId}")
    @ApiOperation(value="Search newsletter by id")
    public NewsLetterDetails getNewsLetterById(@PathVariable int newsletterId) {
        return newsLetterService.getNewsLetterById(newsletterId);
    }


    @PutMapping("/{newsletterId}")
    @ApiOperation(value="Update newsletter by id")
    public ResponseEntity<Object> updateNewsLetterById(@PathVariable int newsletterId, @RequestBody NewsLetterDetails NewsLetter) {
       try {
    	   NewsLetterDetails responce = newsLetterService.updateNewsLetter(newsletterId, NewsLetter);
       return new ResponseEntity<>(responce, HttpStatus.CREATED);
    } catch (IllegalArgumentException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }
    }


    @DeleteMapping("/{newsletterId}")
    @ApiOperation(value="Delete newsletter by id")
    public long deleteNewsLetterById(@PathVariable int newsletterId) {
        return newsLetterService.deleteNewsLetter(newsletterId);
    }
}
