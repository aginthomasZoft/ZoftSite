package com.zoft.solutions.service;


import com.zoft.solutions.entity.UserAccess;
import com.zoft.solutions.respository.AccessRepository;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class AccessService {

	@Autowired
    private AccessRepository repository;


    public UserAccess saveAccessDetail(UserAccess access) {
        return repository.save(access);
    }

    public List<UserAccess> getAccessDetails() {
        return repository.findAll();
    }

    public UserAccess getAccessDetailById(int accessId) {
        return repository.findById(accessId).get();
    }


    public UserAccess updateAccessDetail(int accessId, UserAccess userRequest) {
        // get the product from DB by id
        // update with new value getting from request
    	UserAccess existingAccess = repository.findById(accessId).get(); // DB
    	existingAccess.setBlog(userRequest.getBlog());
    	existingAccess.setCaseStudy(userRequest.getCaseStudy());
    	existingAccess.setPaymaster(userRequest.getPaymaster());
    	existingAccess.setStatus(userRequest.getStatus());
    	existingAccess.setCreatedDate(userRequest.getCreatedDate());
//    	existingAccess.setUserId(userRequest.getUserId());
        return repository.save(existingAccess);
    }


    public long deleteAccessDetail(int accessId) {
        repository.deleteById(accessId);
        return repository.count();
    }

    public UserAccess updateAccessDetailByFields(int accessId, Map<String, Object> fields) {
        Optional<UserAccess> existingUser = repository.findById(accessId);

        if (existingUser.isPresent()) {
            fields.forEach((key, value) -> {
                Field field = ReflectionUtils.findField(UserAccess.class, key);
                field.setAccessible(true);
                ReflectionUtils.setField(field, existingUser.get(), value);
            });
            return repository.save(existingUser.get());
        }
        return null;
    }
}
