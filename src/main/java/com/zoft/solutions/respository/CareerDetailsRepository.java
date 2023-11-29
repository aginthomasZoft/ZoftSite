package com.zoft.solutions.respository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.zoft.solutions.entity.CareersDetails;

@Repository
public interface CareerDetailsRepository extends JpaRepository<CareersDetails, Integer>, JpaSpecificationExecutor<CareersDetails> {

    @Transactional
    @Modifying
    @Query("UPDATE CareersDetails b SET b.active = :active WHERE b.careerId = :careerId")
    int setActiveForCareersId(int careerId, boolean active);
}
