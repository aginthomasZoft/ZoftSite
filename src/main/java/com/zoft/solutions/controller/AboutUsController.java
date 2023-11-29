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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.zoft.solutions.entity.AboutUs;
import com.zoft.solutions.entity.BlogDetails;
import com.zoft.solutions.service.AboutUsService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/aboutUs")
@CrossOrigin
public class AboutUsController {

	@Autowired
    private AboutUsService aboutUsService;

    @GetMapping
    @ApiOperation(value="Get all about us details")
    public List<AboutUs> getAllAboutUs() {
        return aboutUsService.getAllAboutUs();
    }

    @GetMapping("/{aboutUsId}")
    @ApiOperation(value="Get about us By Id ")
    public ResponseEntity<Object> getAboutUsById(@PathVariable int aboutUsId) {
    	ResponseEntity<AboutUs> response = aboutUsService.checkIfAboutUsIdExists(aboutUsId);

        if (response.getStatusCode() == HttpStatus.OK) {
        	
        	AboutUs getAboutUs=aboutUsService.getAboutUsById(aboutUsId);
    	  return new ResponseEntity<>(getAboutUs, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("AboutUs with id " + aboutUsId + " is not present", HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    @ApiOperation(value="Store about us")
    public ResponseEntity<Object> createaboutUs(@RequestBody AboutUs aboutUs) {
        ResponseEntity<AboutUs> response = aboutUsService.checkIfAboutUsIdExists(aboutUs.getAboutUsId());
       
    	try {
    		if (response.getStatusCode() == HttpStatus.NOT_FOUND) {
    		AboutUs savedAboutUs= aboutUsService.createAboutUs(aboutUs);
    		return new ResponseEntity<>(savedAboutUs, HttpStatus.CREATED);
    		} else {
                return new ResponseEntity<>("AboutUs with id " + aboutUs.getAboutUsId() + " already exists", HttpStatus.CONFLICT);
            }
	    } catch (IllegalArgumentException e) {
	        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
	    }
    }

    @PutMapping("/{aboutUsId}")
    @ApiOperation(value="Update about us By Id")
    public ResponseEntity<Object> updateAboutUs(@PathVariable int aboutUsId, @RequestBody AboutUs aboutUs) {
        try {
        	ResponseEntity<AboutUs> response = aboutUsService.checkIfAboutUsIdExists(aboutUsId);

            if (response.getStatusCode() == HttpStatus.OK) {
        	 AboutUs updatedAboutUs= aboutUsService.updateAboutUs(aboutUsId,aboutUs);
             return new ResponseEntity<>(updatedAboutUs, HttpStatus.CREATED);
            } else {
                return new ResponseEntity<>("AboutUs not found for id: " + aboutUsId, HttpStatus.NOT_FOUND);
            }
    } catch (IllegalArgumentException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }
    }

    @DeleteMapping("/{aboutUsId}")
    @ApiOperation(value="Delete about us by id")
    public ResponseEntity<Object> deleteAboutUs(@PathVariable int aboutUsId) {
    	ResponseEntity<AboutUs> response = aboutUsService.checkIfAboutUsIdExists(aboutUsId);

        if (response.getStatusCode() == HttpStatus.OK) {
    	   aboutUsService.deleteAboutUs(aboutUsId);
    	   return new ResponseEntity<>("AboutUs deleted successfully", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("AboutUs not found for id: " + aboutUsId, HttpStatus.NOT_FOUND);
        }
    }
    
    // Handle image upload
    @PostMapping("/image/upload")
    @ApiOperation(value="Upload image")
    public ResponseEntity<String> uploadImage(@RequestParam("file") MultipartFile file, @RequestParam("aboutUs Id") int aboutUsId) throws IOException {
    	ResponseEntity<AboutUs> response = aboutUsService.checkIfAboutUsIdExists(aboutUsId);

        if (response.getStatusCode() == HttpStatus.OK) {
    	  return aboutUsService.aboutUsImageUpload(file,aboutUsId);
        } else {
            return new ResponseEntity<>("AboutUs not found for id: " + aboutUsId, HttpStatus.NOT_FOUND);
        }
    }

    // Get image by ID
    @GetMapping("/image/{aboutUsId}")
    @ApiOperation(value="Get image by id")
    public ResponseEntity<String> getImages(@PathVariable int aboutUsId) {
    	ResponseEntity<AboutUs> response = aboutUsService.checkIfAboutUsIdExists(aboutUsId);

        if (response.getStatusCode() == HttpStatus.OK) {
    	 return ResponseEntity.ok(aboutUsService.getImageById(aboutUsId));
        } else {
            return new ResponseEntity<>("AboutUs not found for id: " + aboutUsId, HttpStatus.NOT_FOUND);
        }
    }

    // Delete an image by ID
    @DeleteMapping("/image/delete/{aboutUsId}")
    @ApiOperation(value="Delete image by id")
    public ResponseEntity<String> deleteImage(@PathVariable int aboutUsId) {
    	ResponseEntity<AboutUs> response = aboutUsService.checkIfAboutUsIdExists(aboutUsId);

        if (response.getStatusCode() == HttpStatus.OK) {
    	 return aboutUsService.aboutUsImageDelete(aboutUsId);
        } else {
            return new ResponseEntity<>("AboutUs not found for id: " + aboutUsId, HttpStatus.NOT_FOUND);
        }
    }
}
