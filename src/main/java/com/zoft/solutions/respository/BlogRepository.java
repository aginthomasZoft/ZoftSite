package com.zoft.solutions.respository;




import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.zoft.solutions.entity.Blogdetails;

public interface BlogRepository extends
        JpaRepository<Blogdetails, Integer> {

    @Query(value = "select * From blogdetails Order By createdDate Desc LIMIT 5", nativeQuery=true)
    List<Blogdetails> findFiveByDate();

}
