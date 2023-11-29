package com.zoft.solutions.util;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.zoft.solutions.entity.AboutUs;
import com.zoft.solutions.entity.Banners;
import com.zoft.solutions.entity.BlogDetails;

public class ValidationUtils {

	public static <T> List<String> validateEntity(T entity) {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();

        Set<ConstraintViolation<T>> violations = validator.validate(entity);

        List<String> validationErrors = new ArrayList<>();

        for (ConstraintViolation<T> violation : violations) {
            validationErrors.add(violation.getMessage());
        }

        if (entity instanceof BlogDetails) {
            BlogDetails blogDetails = (BlogDetails) entity;
            
            if (blogDetails.getCoverImageName() != null && !blogDetails.getCoverImageName().isEmpty()) {
                validationErrors.add("CoverImageName should not be given");
            }
            if (blogDetails.getCoverImageUrl() != null && !blogDetails.getCoverImageUrl().isEmpty()) {
           	 validationErrors.add("CoverImageUrl should not be given");
			}
            
            if (blogDetails.getTags() == null || blogDetails.getTags().length == 0) {
                validationErrors.add("Tags array must not be null or empty");
            }else {
            	int flag = 0;
            	
                for (int i = 0; i < blogDetails.getTags().length; i++) {
                    String tag = blogDetails.getTags()[i];
                    
                    if (tag == null || tag.isEmpty() || tag.trim().isEmpty()) {
                    	flag=1;
                    }
                }
                if(flag == 1) {
                validationErrors.add("Tag is not correct");
                }
            }
        }else if (entity instanceof Banners) {
        	Banners banners = (Banners) entity;
        	
        	if (banners.getBannerImageName() != null && !banners.getBannerImageName().isEmpty()) {
                validationErrors.add("BannerImageName should not be given");
            }
            if (banners.getBannerImageUrl() != null && !banners.getBannerImageUrl().isEmpty()) {
           	 validationErrors.add("BannerImageUrl should not be given");
			}
        } else if (entity instanceof AboutUs) {
        	AboutUs aboutUs = (AboutUs) entity;
        	
        	if (aboutUs.getAboutUsImageName() != null && !aboutUs.getAboutUsImageName().isEmpty()) {
                validationErrors.add("AboutUsImageName should not be given");
            }
            if (aboutUs.getAboutUsImageUrl() != null && !aboutUs.getAboutUsImageUrl().isEmpty()) {
           	 validationErrors.add("AboutUsImageUrl should not be given");
			}
        } 	
        return validationErrors;
    }
}
