package com.zoft.solutions.respository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.zoft.solutions.entity.SubServices;

public interface SubServicesRepository extends JpaRepository<SubServices, Integer>{



	 @Modifying
	    @Transactional
	    @Query("UPDATE SubServices s SET s.subtitle = :subtitle, s.subimg = :subimg, s.subserviceDescription = :subserviceDescription WHERE s.subServiceId = :subServiceId AND s.servicesDetails.serviceId = :serviceId")
	    void updateSubServicesByServiceIdAndSubServiceId(@Param("subtitle") String subtitle, @Param("subimg") String subimg, @Param("subserviceDescription") String subserviceDescription, @Param("serviceId") int serviceId, @Param("subServiceId") int subServiceId);

	 @Modifying
	    @Transactional
	    @Query("DELETE FROM SubServices s WHERE s.subServiceId = :subServiceId AND s.servicesDetails.serviceId = :serviceId")
	    void deleteSubServicesByServiceIdAndSubServiceId(@Param("subServiceId") int subServiceId, @Param("serviceId") int serviceId);
	    

	 @Modifying
	    @Transactional
	    @Query("DELETE FROM SubServices s WHERE s.servicesDetails.serviceId = :serviceId")
	    void deleteAllByServiceId(@Param("serviceId") int serviceId);
	 
	 @Modifying
	    @Transactional
	    @Query(value = "INSERT INTO subservices (subtitle, subimg, subserviceDescription, service_id ) VALUES (:subtitle, :subimg, :subserviceDescription, :serviceId)", nativeQuery = true)
	    void insertSubServices(String subtitle, String subimg, String subserviceDescription, int serviceId);

}
