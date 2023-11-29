package com.zoft.solutions.respository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.zoft.solutions.entity.Banners;

public interface BannersRepository extends JpaRepository<Banners, Integer> {
	
    @Transactional
    @Modifying
    @Query("UPDATE Banners b SET b.bannerImageName = :bannerImageName, b.bannerImageUrl = :bannerImageUrl WHERE b.bannerId = :bannerId")
    int setBannerImageDetails(@Param("bannerId") int bannerId, @Param("bannerImageName") String bannerImageName, @Param("bannerImageUrl") String bannerImageUrl);
    
    @Transactional
    @Modifying
    @Query("UPDATE Banners b SET b.bannerImageName = NULL, b.bannerImageUrl = NULL WHERE b.bannerId = :bannerId")
    int clearBannerImageDetails(@Param("bannerId") int bannerId);
    
    @Query("SELECT b.bannerImageUrl FROM Banners b WHERE b.bannerId = :bannerId")
    String findBannerImageUrlByBannerId(@Param("bannerId") int bannerId);

}
