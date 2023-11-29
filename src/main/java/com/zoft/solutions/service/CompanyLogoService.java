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

import com.zoft.solutions.entity.Banners;
import com.zoft.solutions.entity.CompanyLogos;
import com.zoft.solutions.respository.CompanyLogoRepository;
import com.zoft.solutions.util.MainImageUtil;

@Service
public class CompanyLogoService {
	
    @Autowired
    private CompanyLogoRepository companyLogoRepository;
    
    @Autowired
    private MainImageUtil imageUtil;

    public ResponseEntity<String> logoImageUpload(MultipartFile file,String altLogoImage) throws IOException {
    	
    	Map<String, Object> responseMap = imageUtil.uploadImage(file).getBody();
    	
    	companyLogoRepository.setImageDetails((String) responseMap.get("blobName"),(String) responseMap.get("blobUrl"),altLogoImage);
    	
    	return ResponseEntity.ok("Image uploaded successfully.");
    }
    
    public String getImageById(int logoId) {
        return companyLogoRepository.findImageUrlByLogoId(logoId);
    }
    
    public ResponseEntity<String> logoImageDelete(int logoId) {
        Optional<CompanyLogos> optionalImage = companyLogoRepository.findById(logoId);
        if (optionalImage.isPresent()) {
        	CompanyLogos image = optionalImage.get();
            String blobName = image.getLogoImageName();

            imageUtil.deleteImage(blobName);
            companyLogoRepository.deleteById(logoId);
            return ResponseEntity.ok("Image deleted successfully.");
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
    public List<CompanyLogos> allCompanyLogos() {
        return companyLogoRepository.findAll();
    }
    
public ResponseEntity<CompanyLogos> checkIfLogoIdExists(int logoId) {
        

        try {
        	CompanyLogos logo = companyLogoRepository.findById(logoId).get();;
            return new ResponseEntity<>(logo, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
