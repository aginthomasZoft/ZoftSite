package com.zoft.solutions.service;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.zoft.solutions.entity.AboutUs;
import com.zoft.solutions.entity.BlogDetails;
import com.zoft.solutions.respository.AboutUsRepository;
import com.zoft.solutions.util.MainImageUtil;
import com.zoft.solutions.util.ValidationUtils;

@Service
public class AboutUsService {

	@Autowired
    private AboutUsRepository aboutUsRepository;
    
    @Autowired
    private MainImageUtil imageUtil;

    public List<AboutUs> getAllAboutUs() {
        return aboutUsRepository.findAll();
    }

    public AboutUs getAboutUsById(int aboutUsId) {
        return aboutUsRepository.findById(aboutUsId).get();
    }

    public AboutUs createAboutUs(AboutUs aboutUsDetails) {
    	
    	List<String> validationErrors = ValidationUtils.validateEntity(aboutUsDetails);

        if (!validationErrors.isEmpty()) {
            throw new IllegalArgumentException(String.join(", ", validationErrors));
        }
        return aboutUsRepository.save(aboutUsDetails);
    }

    public AboutUs updateAboutUs(int aboutUsId, AboutUs updatedAboutUs) {
    	
    	List<String> validationErrors = ValidationUtils.validateEntity(updatedAboutUs);

        if (!validationErrors.isEmpty()) {
            throw new IllegalArgumentException(String.join(", ", validationErrors));
        }
        
    	AboutUs existingAboutUs = aboutUsRepository.findById(aboutUsId).orElse(null);
        if (existingAboutUs != null) {
        	existingAboutUs.setAboutUsTitle(updatedAboutUs.getAboutUsTitle());
        	existingAboutUs.setAboutUsDescription(updatedAboutUs.getAboutUsDescription());
        	existingAboutUs.setAboutUsLinkUrl(updatedAboutUs.getAboutUsLinkUrl());
        	existingAboutUs.setAltAboutUsImageName(updatedAboutUs.getAltAboutUsImageName());

            return aboutUsRepository.save(existingAboutUs);
        }
        return null;
    }

    public void deleteAboutUs(int aboutUsId) {
    	aboutUsImageDelete(aboutUsId);
    	aboutUsRepository.deleteById(aboutUsId);
    }
    
    public ResponseEntity<String> aboutUsImageUpload(MultipartFile file, int aboutUsId) throws IOException {
    	
    	Map<String, Object> responseMap = imageUtil.uploadImage(file).getBody();
    	
    	aboutUsRepository.setImageDetails(aboutUsId,(String) responseMap.get("blobName"),(String) responseMap.get("blobUrl"));
    	
    	return ResponseEntity.ok("Image uploaded successfully.");
    }
    
    public String getImageById(int aboutUsId) {
        return aboutUsRepository.findImageUrlByAboutUsId(aboutUsId);
    }
    
    public ResponseEntity<String> aboutUsImageDelete(int aboutUsId) {
        Optional<AboutUs> optionalImage = aboutUsRepository.findById(aboutUsId);
        if (optionalImage.isPresent()) {
        	AboutUs image = optionalImage.get();
            String blobName = image.getAboutUsImageName();

            imageUtil.deleteImage(blobName);
            aboutUsRepository.clearImageDetails(aboutUsId);
            return ResponseEntity.ok("Image deleted successfully.");
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
public ResponseEntity<AboutUs> checkIfAboutUsIdExists(int aboutUsId) {
        

        try {
        	AboutUs aboutUs = getAboutUsById(aboutUsId);
            return new ResponseEntity<>(aboutUs, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
