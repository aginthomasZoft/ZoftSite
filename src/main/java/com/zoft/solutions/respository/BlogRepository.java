package com.zoft.solutions.respository;




import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.zoft.solutions.entity.BlogDetails;

public interface BlogRepository extends
        JpaRepository<BlogDetails, Integer>, JpaSpecificationExecutor<BlogDetails> {

    @Query(value = "select * From BlogDetails Order By publishingDate Desc LIMIT 5", nativeQuery=true)
    List<BlogDetails> findFiveByDate();
    
    
    List<BlogDetails> findAllByOrderByPublishingDateDesc();
    
    @Transactional
    @Modifying
    @Query("UPDATE BlogDetails b SET b.active = :active WHERE b.blogId = :blogId")
    int setActiveForBlogId(int blogId, boolean active);
    
    @Transactional
    @Modifying
    @Query("UPDATE BlogDetails b SET b.coverImageName = :coverImageName, b.coverImageUrl = :coverImageUrl WHERE b.blogId = :blogId")
    int setImageDetails(@Param("blogId") int blogId, @Param("coverImageName") String coverImageName, @Param("coverImageUrl") String coverImageUrl);
    
    @Transactional
    @Modifying
    @Query("UPDATE BlogDetails b SET b.coverImageName = NULL, b.coverImageUrl = NULL WHERE b.blogId = :blogId")
    int clearImageDetails(@Param("blogId") int blogId);
    
    @Query("SELECT b.coverImageUrl FROM BlogDetails b WHERE b.blogId = :blogId")
    String findCoverImageUrlByBlogId(@Param("blogId") int blogId);

    
}
