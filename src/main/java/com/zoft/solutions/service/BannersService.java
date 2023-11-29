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
import com.zoft.solutions.entity.BlogDetails;
import com.zoft.solutions.respository.BannersRepository;
import com.zoft.solutions.util.MainImageUtil;
import com.zoft.solutions.util.ValidationUtils;

@Service
public class BannersService {

    @Autowired
    private BannersRepository bannersRepository;
    
    @Autowired
    private MainImageUtil imageUtil;

    public List<Banners> getAllBanners() {
        return bannersRepository.findAll();
    }

    public Banners getBannerById(int bannerId) {
        return bannersRepository.findById(bannerId).get();
    }

    public Banners createBanner(Banners bannerDetails) {
    	
    	List<String> validationErrors = ValidationUtils.validateEntity(bannerDetails);

        if (!validationErrors.isEmpty()) {
            throw new IllegalArgumentException(String.join(", ", validationErrors));
        }
        return bannersRepository.save(bannerDetails);
    }

    public Banners updateBanner(int bannerId, Banners updatedBanner) {
    	
    	List<String> validationErrors = ValidationUtils.validateEntity(updatedBanner);

        if (!validationErrors.isEmpty()) {
            throw new IllegalArgumentException(String.join(", ", validationErrors));
        }
        Banners existingBanner = bannersRepository.findById(bannerId).orElse(null);
        if (existingBanner != null) {
            existingBanner.setBannerTitle(updatedBanner.getBannerTitle());
            existingBanner.setBannerDescription(updatedBanner.getBannerDescription());
            existingBanner.setBannerLinkUrl(updatedBanner.getBannerLinkUrl());
            existingBanner.setAltBannerImageName(updatedBanner.getAltBannerImageName());
            return bannersRepository.save(existingBanner);
        }
        return null;
    }

    public void deleteBanner(int bannerId) {
    	bannerImageDelete(bannerId);
        bannersRepository.deleteById(bannerId);
    }
    
    public ResponseEntity<String> bannerImageUpload(MultipartFile file, int bannerId) throws IOException {
    	
    	Map<String, Object> responseMap = imageUtil.uploadImage(file).getBody();
    	
    	bannersRepository.setBannerImageDetails(bannerId,(String) responseMap.get("blobName"),(String) responseMap.get("blobUrl"));
    	
    	return ResponseEntity.ok("Image uploaded successfully.");
    }
    
    public String getImageById(int bannerId) {
        return bannersRepository.findBannerImageUrlByBannerId(bannerId);
    }
    
    public ResponseEntity<String> bannerImageDelete(int bannerId) {
        Optional<Banners> optionalImage = bannersRepository.findById(bannerId);
        if (optionalImage.isPresent()) {
        	Banners image = optionalImage.get();
            String blobName = image.getBannerImageName();

            imageUtil.deleteImage(blobName);
            bannersRepository.clearBannerImageDetails(bannerId);
            return ResponseEntity.ok("Image deleted successfully.");
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
    public ResponseEntity<Banners> checkIfBannerIdExists(int bannerId) {
        

        try {
        	Banners banner = getBannerById(bannerId);
            return new ResponseEntity<>(banner, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}

