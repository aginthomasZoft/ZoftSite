package com.zoft.solutions.respository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.zoft.solutions.entity.Admin;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Integer>{

	Admin findByUsername(String username);
}
