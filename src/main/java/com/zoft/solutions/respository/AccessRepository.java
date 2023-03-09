package com.zoft.solutions.respository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.zoft.solutions.entity.UserAccess;

public interface AccessRepository extends
JpaRepository<UserAccess, Integer>{

}
