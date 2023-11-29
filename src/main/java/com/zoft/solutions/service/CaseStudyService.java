package com.zoft.solutions.service;




import java.io.IOException;
import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.multipart.MultipartFile;

import com.zoft.solutions.entity.BlogDetails;
import com.zoft.solutions.entity.CaseStudyDetails;
import com.zoft.solutions.respository.CaseStudyRepository;
import com.zoft.solutions.util.CaseStudySpecificationUtil;
import com.zoft.solutions.util.MainImageUtil;
import com.zoft.solutions.util.ValidationUtils;

@Service
public class CaseStudyService {

	@Autowired
    private CaseStudyRepository repository;

    @Autowired
    private MainImageUtil imageUtil;

    public CaseStudyDetails saveCaseStudy(CaseStudyDetails caseStudyDetails) {
    	
   	 List<String> validationErrors = ValidationUtils.validateEntity(caseStudyDetails);

     if (!validationErrors.isEmpty()) {
         throw new IllegalArgumentException(String.join(", ", validationErrors));
     }
     
        return repository.save(caseStudyDetails);
    }

    public List<CaseStudyDetails> getCaseStudy() {
        return repository.findAll();
    }

    public CaseStudyDetails getCaseStudyById(int caseId) {
        return repository.findById(caseId).get();
    }


    public CaseStudyDetails updateCaseStudy(int caseId, CaseStudyDetails updatedCaseStudy) {
        
    	List<String> validationErrors = ValidationUtils.validateEntity(updatedCaseStudy);

        if (!validationErrors.isEmpty()) {
            throw new IllegalArgumentException(String.join(", ", validationErrors));
        }
        
    	// get the product from DB by id
        // update with new value getting from request
    	CaseStudyDetails existingProduct = repository.findById(caseId).get(); // DB
        existingProduct.setCaseTitle(updatedCaseStudy.getCaseTitle());
        existingProduct.setCategory(updatedCaseStudy.getCategory());
        existingProduct.setClients(updatedCaseStudy.getClients());
        existingProduct.setCoverImg(updatedCaseStudy.getCoverImg());
        existingProduct.setContent(updatedCaseStudy.getContent());
        existingProduct.setPreview(updatedCaseStudy.getPreview());
        existingProduct.setStatus(updatedCaseStudy.getStatus());
        existingProduct.setTags(updatedCaseStudy.getTags());
        existingProduct.setCreatedDate(updatedCaseStudy.getCreatedDate());
        existingProduct.setUpdatedDate(updatedCaseStudy.getUpdatedDate());
        existingProduct.setPublishingDate(updatedCaseStudy.getPublishingDate());
        existingProduct.setMetaTitle(updatedCaseStudy.getMetaTitle());
        existingProduct.setMetaDescription(updatedCaseStudy.getMetaDescription());
        existingProduct.setPublished(updatedCaseStudy.isPublished());
        return repository.save(existingProduct);
    }


    public long deleteCaseStudy(int caseId) {
        repository.deleteById(caseId);
        return repository.count();
    }

    public CaseStudyDetails updateCaseStudyByFields(int caseId, Map<String, Object> fields) {
        Optional<CaseStudyDetails> existingBlog = repository.findById(caseId);

        if (existingBlog.isPresent()) {
            fields.forEach((key, value) -> {
                Field field = ReflectionUtils.findField(CaseStudyDetails.class, key);
                field.setAccessible(true);
                ReflectionUtils.setField(field, existingBlog.get(), value);
            });
            return repository.save(existingBlog.get());
        }
        return null;
    }
    
 public List<CaseStudyDetails> getFiveCaseStudy() {
    	
        return repository.findFiveByDate();
    }
 
 public List<CaseStudyDetails> searchCaseStudy(String caseTitle, Boolean active,String status){
 	
 	// Create a specification using the static method from BlogSpecifications
     Specification<CaseStudyDetails> spec = CaseStudySpecificationUtil.findByTitleAndStatus(caseTitle, status);

     if (active != null) {
         // If 'active' is provided, add an additional condition to the specification
         spec = spec.and((root, query, criteriaBuilder) ->
                 criteriaBuilder.equal(root.get("active"), active));
     }

     // Use the specification and apply sorting
     return repository.findAll(spec, Sort.by(Sort.Direction.DESC, "publishingDate"));
	
 }
 
 public void setActiveForCaseStudy(int caseId, boolean active) {
 	repository.setActiveForCaseId(caseId, active);
 }
 
// public ResponseEntity<String> caseStudyImageUpload(MultipartFile file, int caseId) throws IOException {
// 	
// 	Map<String, Object> responseMap = imageUtil.uploadImage(file).getBody();
// 	
// 	repository.setImageDetails(caseId,(String) responseMap.get("blobName"),(String) responseMap.get("blobUrl"));
// 	
// 	return ResponseEntity.ok("Image uploaded successfully.");
// }
// 
// public String getImageById(int caseId) {
//     return repository.findCoverImageUrlByBlogId(caseId);
// }
// 
// public ResponseEntity<String> caseStudyImageDelete(int caseId) {
//     Optional<CaseStudyDetails> optionalImage = repository.findById(caseId);
//     if (optionalImage.isPresent()) {
//    	 CaseStudyDetails image = optionalImage.get();
//         String blobName = image.getCoverImageName();
//
//         imageUtil.deleteImage(blobName);
//         repository.clearImageDetails(caseId);
//         return ResponseEntity.ok("Image deleted successfully.");
//     } else {
//         return ResponseEntity.notFound().build();
//     }
// }
 
}
