package com.zoft.solutions.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.zoft.solutions.entity.Banners;
import com.zoft.solutions.entity.CompanyLogos;
import com.zoft.solutions.service.CompanyLogoService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/companyLogos")
@CrossOrigin
public class CompanyLogoController {
	
    @Autowired
    private CompanyLogoService companyLogoService;
    

	 // Handle image upload
    @PostMapping("/upload")
    @ApiOperation(value="Upload logo image")
    public ResponseEntity<String> uploadLogo(@RequestParam("file") MultipartFile file, @RequestParam("Image Name") String imageName) throws IOException {
        return companyLogoService.logoImageUpload(file,imageName);
    }
    
    //get all images
    @GetMapping
    @ApiOperation(value="Get all company logos")
    public List<CompanyLogos> getAllCompanyLogos() {
        return companyLogoService.allCompanyLogos();
    }

    // get image by id
    @GetMapping("/{logoId}")
    @ApiOperation(value="Get company logos by id")
    public ResponseEntity<String> getLogoById(@PathVariable int logoId) {
    	ResponseEntity<CompanyLogos> response = companyLogoService.checkIfLogoIdExists(logoId);

        if (response.getStatusCode() == HttpStatus.OK) {
    	  return ResponseEntity.ok(companyLogoService.getImageById(logoId));
        } else {
            return new ResponseEntity<>("Logo not found for id: " + logoId, HttpStatus.NOT_FOUND);
        }
    }

    // Delete an image by ID
    @DeleteMapping("/delete/{logoId}")
    @ApiOperation(value="Delete company logos by id")
    public ResponseEntity<String> deleteLogoById(@PathVariable int logoId) {
    	ResponseEntity<CompanyLogos> response = companyLogoService.checkIfLogoIdExists(logoId);

        if (response.getStatusCode() == HttpStatus.OK) {

    	  return companyLogoService.logoImageDelete(logoId);
        } else {
            return new ResponseEntity<>("Logo not found for id: " + logoId, HttpStatus.NOT_FOUND);
        }
    }
}
