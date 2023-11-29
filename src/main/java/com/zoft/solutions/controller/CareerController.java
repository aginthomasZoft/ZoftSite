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

import com.zoft.solutions.entity.CareersDetails;
import com.zoft.solutions.model.SearchRequestModel;
import com.zoft.solutions.service.CareerService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/careers")
@CrossOrigin
public class CareerController {

	 @Autowired
	    private CareerService careerService;

	    @GetMapping
	    @ApiOperation(value="Get all Career Details")
	    public List<CareersDetails> getAllCareerDetails() {
	        return careerService.getAllCareerDetails();
	    }

	    @GetMapping("/{careerId}")
	    @ApiOperation(value="Get By Id Career Details")
	    public CareersDetails getCareerDetailsById(@PathVariable int careerId) {
	        return careerService.getCareerDetailsById(careerId);
	    }

	    @PostMapping
	    @ApiOperation(value="Store Career Details")
	    public ResponseEntity<Object> saveCareerDetails(@RequestBody CareersDetails roleDetails) {
	        try {
	        	CareersDetails responce= careerService.saveCareerDetails(roleDetails);
	        	return new ResponseEntity<>(responce, HttpStatus.CREATED);
    	    } catch (IllegalArgumentException e) {
    	        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    	    }
	    }

	    @DeleteMapping("/{careerId}")
	    @ApiOperation(value="Delete Career Details by id")
	    public void deleteCareerDetails(@PathVariable int careerId) {
	    	careerService.deleteCareerDetails(careerId);
	    }
	    
	    @PutMapping("/{careerId}")
	    @ApiOperation(value="Update career details By Id")
	    public ResponseEntity<Object> updateCareerDetails(@PathVariable int careerId, @RequestBody CareersDetails careersDetails) {
	    	try {
	    		CareersDetails responce=careerService.updateCareerDetails(careerId, careersDetails);
	    	return new ResponseEntity<>(responce, HttpStatus.CREATED);
	    } catch (IllegalArgumentException e) {
	        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
	    }
	    }
	    
	    @PostMapping("/search")
	    @ApiOperation(value = "Search Career")
	    public List<CareersDetails> searchByRoleCareers(@RequestBody SearchRequestModel searchRequest) {
	        return careerService.searchCareers(searchRequest.getSearchKey(), searchRequest.getActive(), searchRequest.getStatus());
	    }
	    
	    @PostMapping("/delete/{careerId}")
	    @ApiOperation(value="Change active status")
	    public void setActiveForCareer(@PathVariable int careerId) {
	    	careerService.setActiveForCareer(careerId,false);
	    }
}
