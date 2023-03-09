package com.zoft.solutions.service;




import com.zoft.solutions.entity.CaseStudyDetails;
import com.zoft.solutions.respository.CaseStudyRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class CaseStudyService {

	@Autowired
    private CaseStudyRepository repository;


    public CaseStudyDetails saveCaseStudy(CaseStudyDetails blog) {
        return repository.save(blog);
    }

    public List<CaseStudyDetails> getCaseStudy() {
        return repository.findAll();
    }

    public CaseStudyDetails getCaseStudyById(int caseId) {
        return repository.findById(caseId).get();
    }


    public CaseStudyDetails updateCaseStudy(int caseId, CaseStudyDetails blogRequest) {
        // get the product from DB by id
        // update with new value getting from request
    	CaseStudyDetails existingProduct = repository.findById(caseId).get(); // DB
        existingProduct.setCaseTitle(blogRequest.getCaseTitle());
        existingProduct.setCategory(blogRequest.getCategory());
        existingProduct.setClients(blogRequest.getClients());
        existingProduct.setCoverImg(blogRequest.getCoverImg());
        existingProduct.setContent(blogRequest.getContent());
        existingProduct.setPreview(blogRequest.getPreview());
        existingProduct.setSeo(blogRequest.getSeo());
        existingProduct.setStatus(blogRequest.getStatus());
        existingProduct.setTags(blogRequest.getTags());
        existingProduct.setCreatedDate(blogRequest.getCreatedDate());
        existingProduct.setUpdatedDate(blogRequest.getUpdatedDate());
        existingProduct.setPublishingDate(blogRequest.getPublishingDate());
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
    
 public List<CaseStudyDetails> getFiveBlogs() {
    	
        return repository.findFiveByDate();
    }
}
