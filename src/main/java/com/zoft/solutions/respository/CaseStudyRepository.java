package com.zoft.solutions.respository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


import com.zoft.solutions.entity.CaseStudyDetails;

public interface CaseStudyRepository extends
JpaRepository<CaseStudyDetails, Integer>{

	@Query(value = "select * From casestudydetails Order By createdDate Desc LIMIT 5", nativeQuery=true)
    List<CaseStudyDetails> findFiveByDate();
}
