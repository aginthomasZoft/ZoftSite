package com.zoft.solutions.respository;

import org.springframework.data.jpa.repository.JpaRepository;


import com.zoft.solutions.entity.ContactUs;
public interface ContactRepository extends JpaRepository<ContactUs, Integer>{

}
