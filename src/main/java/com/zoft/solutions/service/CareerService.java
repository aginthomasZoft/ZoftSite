package com.zoft.solutions.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.zoft.solutions.entity.CareersDetails;
import com.zoft.solutions.respository.CareerDetailsRepository;
import com.zoft.solutions.util.CareerSpecificationsUtil;
import com.zoft.solutions.util.ValidationUtils;

@Service
public class CareerService {

	@Autowired
    private CareerDetailsRepository careerDetailsRepository;

    public List<CareersDetails> getAllCareerDetails() {
        return careerDetailsRepository.findAll();
    }

    public CareersDetails getCareerDetailsById(int careerId) {
        return careerDetailsRepository.findById(careerId).orElse(null);
    }

    public CareersDetails saveCareerDetails(CareersDetails careerDetails) {
    	
    	List<String> validationErrors = ValidationUtils.validateEntity(careerDetails);

        if (!validationErrors.isEmpty()) {
            throw new IllegalArgumentException(String.join(", ", validationErrors));
        }
        return careerDetailsRepository.save(careerDetails);
    }

    public void deleteCareerDetails(int careerId) {
    	careerDetailsRepository.deleteById(careerId);
    }
    
    public CareersDetails updateCareerDetails(int careerId, CareersDetails updatedCareerDetails) {
    	
    	List<String> validationErrors = ValidationUtils.validateEntity(updatedCareerDetails);

        if (!validationErrors.isEmpty()) {
            throw new IllegalArgumentException(String.join(", ", validationErrors));
        }
    	CareersDetails existingRoleDetails = careerDetailsRepository.findById(careerId).orElse(null);

        if (existingRoleDetails != null) {
            // Update fields
            existingRoleDetails.setRole(updatedCareerDetails.getRole());
            existingRoleDetails.setJobCode(updatedCareerDetails.getJobCode());
            existingRoleDetails.setWorkMode(updatedCareerDetails.getWorkMode());
            existingRoleDetails.setWorkLocation(updatedCareerDetails.getWorkLocation());
            existingRoleDetails.setExperience(updatedCareerDetails.getExperience());
            existingRoleDetails.setExpectedDateToJoin(updatedCareerDetails.getExpectedDateToJoin());
            existingRoleDetails.setContent(updatedCareerDetails.getContent());
            existingRoleDetails.setActive(updatedCareerDetails.isActive());
            existingRoleDetails.setUpdatedDate(updatedCareerDetails.getUpdatedDate());
            existingRoleDetails.setCreatedDate(updatedCareerDetails.getCreatedDate());
            existingRoleDetails.setCreatedBy(updatedCareerDetails.getCreatedBy());
            existingRoleDetails.setPublishingDate(updatedCareerDetails.getPublishingDate());
            existingRoleDetails.setStatus(updatedCareerDetails.getStatus());
            existingRoleDetails.setPublished(updatedCareerDetails.isPublished());
            existingRoleDetails.setCategory(updatedCareerDetails.getCategory());
            existingRoleDetails.setMetaTitle(updatedCareerDetails.getMetaTitle());
            existingRoleDetails.setMetaDescription(updatedCareerDetails.getMetaDescription());
            existingRoleDetails.setPreview(updatedCareerDetails.getPreview());
            existingRoleDetails.setCompany(updatedCareerDetails.getCompany());

            return careerDetailsRepository.save(existingRoleDetails);
        } else {

            return null;
        }
    }
    
    public List<CareersDetails> searchCareers(String role, Boolean active,String status){
    	
    	// Create a specification using the static method from BlogSpecifications
        Specification<CareersDetails> spec = CareerSpecificationsUtil.findByRoleAndStatus(role, status);

        if (active != null) {
            // If 'active' is provided, add an additional condition to the specification
            spec = spec.and((root, query, criteriaBuilder) ->
                    criteriaBuilder.equal(root.get("active"), active));
        }

        // Using the specification and apply sorting
        return careerDetailsRepository.findAll(spec, Sort.by(Sort.Direction.DESC, "publishingDate"));
  	
    }
    
    public void setActiveForCareer(int careerId, boolean active) {
    	careerDetailsRepository.setActiveForCareersId(careerId, active);
    }
}
