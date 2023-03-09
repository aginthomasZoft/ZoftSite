package com.zoft.solutions.controller;


import com.zoft.solutions.entity.CaseStudyDetails;
import com.zoft.solutions.service.CaseStudyService;

import io.swagger.annotations.ApiOperation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/Case")
@CrossOrigin
public class CaseStudyController {

	 @Autowired
	    private CaseStudyService service;

	    @PostMapping
	    @ApiOperation(value="Store Case Study")
	    public CaseStudyDetails addCaseStudy(@RequestBody CaseStudyDetails caseId) {
	        return service.saveCaseStudy(caseId);
	    }

	    @GetMapping
	    @ApiOperation(value="Search Case Study")
	    public List<CaseStudyDetails> getCaseStudy() {
	        return service.getCaseStudy();
	    }

	    @GetMapping("/five")
	    @ApiOperation(value="Search Five Case Study")
	    public List<CaseStudyDetails> getFiveCaseStudy() {
	        return service.getFiveBlogs();
	    }
	    @GetMapping("/{caseId}")
	    @ApiOperation(value="Search By Id Case Study")
	    public CaseStudyDetails getCaseStudyById(@PathVariable int caseId) {
	        return service.getCaseStudyById(caseId);
	    }


	    @PutMapping("/{caseId}")
	    @ApiOperation(value="Update By Id Case Study")
	    public CaseStudyDetails updateCaseStudyById(@PathVariable int caseId, @RequestBody CaseStudyDetails caseStudy) {
	       return service.updateCaseStudy(caseId, caseStudy);
	    }

	    @PatchMapping("/{caseId}")
	    public CaseStudyDetails updateCaseStudyFields(@PathVariable int caseId,@RequestBody Map<String, Object> fields){
	        return service.updateCaseStudyByFields(caseId,fields);
	    }

	    @DeleteMapping("/{caseId}")
	    @ApiOperation(value="Delete Case Study")
	    public long deleteCaseStudyById(@PathVariable int caseId) {
	        return service.deleteCaseStudy(caseId);
	    }

}
