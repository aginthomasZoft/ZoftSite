package com.zoft.solutions.respository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.zoft.solutions.entity.NewsLetterDetails;

public interface NewsLetterRepository extends JpaRepository<NewsLetterDetails, Integer>{

}
