package com.zoft.solutions.respository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.zoft.solutions.entity.ServicesDetails;
import com.zoft.solutions.entity.SubServices;

public interface ServicesRepository extends JpaRepository<ServicesDetails, Integer>{

	
	    @Query("SELECT s FROM SubServices s WHERE s.servicesDetails.serviceId = :serviceId")
	    List<SubServices> findSubServicesByServiceId(@Param("serviceId") int serviceId);

	    @Modifying
	    @Transactional
	    @Query(value = "INSERT INTO servicesdetails (title, img, serviceDescription, serviceBenefits, sideImage) VALUES (:title, :img, :serviceDescription, :serviceBenefits, :sideImage)", nativeQuery = true)
	    void insertServicesDetails(String title, String img, String serviceDescription, String serviceBenefits, String sideImage);

	    @Query("SELECT MAX(s.serviceId) FROM ServicesDetails s")
	    Integer getLastInsertedServiceId();

}
