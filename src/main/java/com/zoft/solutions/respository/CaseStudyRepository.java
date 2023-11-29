package com.zoft.solutions.respository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.zoft.solutions.entity.CaseStudyDetails;

public interface CaseStudyRepository extends
JpaRepository<CaseStudyDetails, Integer>, JpaSpecificationExecutor<CaseStudyDetails> {

	@Query(value = "select * From casestudydetails Order By createdDate Desc LIMIT 5", nativeQuery=true)
    List<CaseStudyDetails> findFiveByDate();
	
    @Transactional
    @Modifying
    @Query("UPDATE CaseStudyDetails b SET b.active = :active WHERE b.caseId = :caseId")
    int setActiveForCaseId(int caseId, boolean active);
    
//    @Transactional
//    @Modifying
//    @Query("UPDATE CaseStudyDetails b SET b.coverImageName = :coverImageName, b.coverImageUrl = :coverImageUrl WHERE b.blogId = :blogId")
//    int setImageDetails(@Param("blogId") int blogId, @Param("coverImageName") String coverImageName, @Param("coverImageUrl") String coverImageUrl);
//    
//    @Transactional
//    @Modifying
//    @Query("UPDATE CaseStudyDetails b SET b.coverImageName = NULL, b.coverImageUrl = NULL WHERE b.blogId = :blogId")
//    int clearImageDetails(@Param("blogId") int blogId);
//    
//    @Query("SELECT b.coverImageUrl FROM BlogDetails b WHERE b.blogId = :blogId")
//    String findCoverImageUrlByBlogId(@Param("blogId") int blogId);

}
