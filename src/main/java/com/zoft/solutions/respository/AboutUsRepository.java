package com.zoft.solutions.respository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.zoft.solutions.entity.AboutUs;

public interface AboutUsRepository extends JpaRepository<AboutUs, Integer> {

    @Transactional
    @Modifying
    @Query("UPDATE AboutUs b SET b.aboutUsImageName = :aboutUsImageName, b.aboutUsImageUrl = :aboutUsImageUrl WHERE b.aboutUsId = :aboutUsId")
    int setImageDetails(@Param("aboutUsId") int aboutUsId, @Param("aboutUsImageName") String aboutUsImageName, @Param("aboutUsImageUrl") String aboutUsImageUrl);
    
    @Transactional
    @Modifying
    @Query("UPDATE AboutUs b SET b.aboutUsImageName = NULL, b.aboutUsImageUrl = NULL WHERE b.aboutUsId = :aboutUsId")
    int clearImageDetails(@Param("aboutUsId") int aboutUsId);
    
    @Query("SELECT b.aboutUsImageUrl FROM AboutUs b WHERE b.aboutUsId = :aboutUsId")
    String findImageUrlByAboutUsId(@Param("aboutUsId") int aboutUsId);

}
