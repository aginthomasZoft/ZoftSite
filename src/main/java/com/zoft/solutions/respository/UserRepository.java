package com.zoft.solutions.respository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.zoft.solutions.entity.UserDetails;
public interface UserRepository  extends
JpaRepository<UserDetails, Integer>{

}
