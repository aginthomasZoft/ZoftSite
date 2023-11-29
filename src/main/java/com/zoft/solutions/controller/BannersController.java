package com.zoft.solutions.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.zoft.solutions.entity.Banners;
import com.zoft.solutions.entity.BlogDetails;
import com.zoft.solutions.service.BannersService;

import io.swagger.annotations.ApiOperation;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/banners")
@CrossOrigin
public class BannersController {

    @Autowired
    private BannersService bannersService;

    @GetMapping
    @ApiOperation(value="Get all banners details")
    public List<Banners> getAllBanners() {
        return bannersService.getAllBanners();
    }

    @GetMapping("/{bannerId}")
    @ApiOperation(value="Get banner by id details")
    public ResponseEntity<Object> getBannerById(@PathVariable int bannerId) {
        
    	ResponseEntity<Banners> response = bannersService.checkIfBannerIdExists(bannerId);

        if (response.getStatusCode() == HttpStatus.OK) {
        	Banners getBanner = response.getBody();
            return new ResponseEntity<>(getBanner, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Banner with id " + bannerId + " is not present", HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    @ApiOperation(value="Store Banner")
    public ResponseEntity<Object> createBanner(@RequestBody Banners banners) {
    	
        ResponseEntity<Banners> response = bannersService.checkIfBannerIdExists(banners.getBannerId());

        try {
        	if (response.getStatusCode() == HttpStatus.NOT_FOUND) {
        	Banners savedBanner =  bannersService.createBanner(banners);
        	return new ResponseEntity<>(savedBanner, HttpStatus.CREATED);
	        }else {
            return new ResponseEntity<>("Banner with id " + banners.getBannerId() + " already exists", HttpStatus.CONFLICT);
             }
        }catch (IllegalArgumentException e) {
	        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
	    }
    }

    @PutMapping("/{bannerId}")
    @ApiOperation(value="Update banner By Id")
    public ResponseEntity<Object> updateBanner(@PathVariable int bannerId, @RequestBody Banners banners) {
      try {
        	ResponseEntity<Banners> response = bannersService.checkIfBannerIdExists(bannerId);

        	if (response.getStatusCode() == HttpStatus.OK) {
        	  Banners updatedBanner = bannersService.updateBanner(bannerId, banners);
              return new ResponseEntity<>(updatedBanner, HttpStatus.CREATED);
        	} else {
                return new ResponseEntity<>("Banner not found for id: " + bannerId, HttpStatus.NOT_FOUND);
            }
         } catch (IllegalArgumentException e) {
             return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
         }
    }

    @DeleteMapping("/{bannerId}")
    @ApiOperation(value="Delete banner by id")
    public ResponseEntity<Object> deleteBannerById(@PathVariable int bannerId) {
    	ResponseEntity<Banners> response = bannersService.checkIfBannerIdExists(bannerId);

    	if (response.getStatusCode() == HttpStatus.OK) {
    	 bannersService.deleteBanner(bannerId);
    	 return new ResponseEntity<>("Banner deleted successfully", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Banner not found for id: " + bannerId, HttpStatus.NOT_FOUND);
        }
    }
    
    // Handle image upload
    @PostMapping("/image/upload")
    @ApiOperation(value="Upload banner image")
    public ResponseEntity<String> uploadImage(@RequestParam("file") MultipartFile file, @RequestParam("banner Id") int bannerId) throws IOException {
    	ResponseEntity<Banners> response = bannersService.checkIfBannerIdExists(bannerId);

        if (response.getStatusCode() == HttpStatus.OK) {
    	 return bannersService.bannerImageUpload(file,bannerId);
        } else {
            return new ResponseEntity<>("Banner not found for id: " + bannerId, HttpStatus.NOT_FOUND);
        }
    }

    // Get image by ID
    @GetMapping("/image/{bannerId}")
    @ApiOperation(value="Get banner image by id")
    public ResponseEntity<String> getImages(@PathVariable int bannerId) {
       	ResponseEntity<Banners> response = bannersService.checkIfBannerIdExists(bannerId);

        if (response.getStatusCode() == HttpStatus.OK) {
    	 return ResponseEntity.ok(bannersService.getImageById(bannerId));
        } else {
            return new ResponseEntity<>("Banner not found for id: " + bannerId, HttpStatus.NOT_FOUND);
        }
    }

    // Delete an image by ID
    @DeleteMapping("/image/delete/{bannerId}")
    @ApiOperation(value="Delete banner image by id")
    public ResponseEntity<String> deleteImage(@PathVariable int bannerId) {
    	ResponseEntity<Banners> response = bannersService.checkIfBannerIdExists(bannerId);

        if (response.getStatusCode() == HttpStatus.OK) {
        return bannersService.bannerImageDelete(bannerId);
        } else {
            return new ResponseEntity<>("Banner not found for id: " + bannerId, HttpStatus.NOT_FOUND);
        }
    }
}

