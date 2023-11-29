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

import com.zoft.solutions.entity.CaseStudyDetails;
import com.zoft.solutions.model.SearchRequestModel;
import com.zoft.solutions.service.CaseStudyService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/caseStudies")
@CrossOrigin
public class CaseStudyController {

	 @Autowired
	    private CaseStudyService service;

	    @PostMapping
	    @ApiOperation(value="Store case study")
	    public ResponseEntity<Object> addCaseStudy(@RequestBody CaseStudyDetails caseId) {
	        try {
	        	CaseStudyDetails responce =  service.saveCaseStudy(caseId);
	        	return new ResponseEntity<>(responce, HttpStatus.CREATED);
    	    } catch (IllegalArgumentException e) {
    	        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    	    }
	    }

	    @GetMapping
	    @ApiOperation(value="Get all case study")
	    public List<CaseStudyDetails> getCaseStudy() {
	        return service.getCaseStudy();
	    }

	    @GetMapping("/five")
	    @ApiOperation(value="Get Five case study")
	    public List<CaseStudyDetails> getFiveCaseStudy() {
	        return service.getFiveCaseStudy();
	    }
	    @GetMapping("/{caseId}")
	    @ApiOperation(value="Get By Id case study")
	    public CaseStudyDetails getCaseStudyById(@PathVariable int caseId) {
	        return service.getCaseStudyById(caseId);
	    }


	    @PutMapping("/{caseId}")
	    @ApiOperation(value="Update case study By Id")
	    public ResponseEntity<Object> updateCaseStudyById(@PathVariable int caseId, @RequestBody CaseStudyDetails caseStudy) {
	       try {
	    	   CaseStudyDetails responce =   service.updateCaseStudy(caseId, caseStudy);
	       return new ResponseEntity<>(responce, HttpStatus.CREATED);
	    } catch (IllegalArgumentException e) {
	        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
	    }
	    }

	    @DeleteMapping("/{caseId}")
	    @ApiOperation(value="Delete Case Study by id")
	    public long deleteCaseStudyById(@PathVariable int caseId) {
	        return service.deleteCaseStudy(caseId);
	    }
	    
	    @PostMapping("/search")
	    @ApiOperation(value = "Search case study")
	    public List<CaseStudyDetails> searchByTitleBlogs(@RequestBody SearchRequestModel searchRequest) {
	        return service.searchCaseStudy(searchRequest.getSearchKey(), searchRequest.getActive(), searchRequest.getStatus());
	    }
	    
	    @PostMapping("/delete/{caseId}")
	    @ApiOperation(value="Change active status for case study")
	    public void setActiveForBlog(@PathVariable int caseId) {
	         service.setActiveForCaseStudy(caseId,false);
	    }

}
