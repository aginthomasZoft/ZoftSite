package com.zoft.solutions.respository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.zoft.solutions.entity.CompanyLogos;



public interface CompanyLogoRepository extends JpaRepository<CompanyLogos, Integer> {

	@Modifying
    @Transactional
    @Query(value = "INSERT INTO CompanyLogos (logoImageName, logoImageUrl,altLogoImage) VALUES (:logoImageName, :logoImageUrl, :altLogoImage)", nativeQuery = true)
    int setImageDetails(@Param("logoImageName") String logoImageName, @Param("logoImageUrl") String logoImageUrl,@Param("altLogoImage") String altLogoImage);

//	@Modifying
//    @Transactional
//    @Query(value = "INSERT INTO CompanyLogos (logoImageName, logoImageUrl,altLogoImageUrl) VALUES (:logoImageName, :logoImageUrl, :altLogoImageUrl)", nativeQuery = true)
//    int updateImageDetails(@Param("logoImageName") String logoImageName, @Param("logoImageUrl") String logoImageUrl,@Param("altLogoImageUrl") String altLogoImageUrl);


    @Query("SELECT b.logoImageUrl FROM CompanyLogos b WHERE b.logoId = :logoId")
    String findImageUrlByLogoId(@Param("logoId") int logoId);

}
