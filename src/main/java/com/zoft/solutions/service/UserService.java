package com.zoft.solutions.service;

import com.zoft.solutions.entity.UserDetails;
import com.zoft.solutions.respository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class UserService {

	@Autowired
    private UserRepository repository;


    public UserDetails saveUser(UserDetails user) {
        return repository.save(user);
    }

    public List<UserDetails> getUsers() {
        return repository.findAll();
    }

    public UserDetails getUserById(int userId) {
        return repository.findById(userId).get();
    }


    public UserDetails updateUser(int userId, UserDetails userRequest) {
        // get the product from DB by id
        // update with new value getting from request
    	UserDetails existingProduct = repository.findById(userId).get(); // DB
        existingProduct.setName(userRequest.getName());
        existingProduct.setPassword(userRequest.getPassword());
        existingProduct.setEmail(userRequest.getEmail());
        existingProduct.setStatus(userRequest.getStatus());
        existingProduct.setLastLogin(userRequest.getLastLogin());
        existingProduct.setUserAccessId(userRequest.getUserAccessId());
        return repository.save(existingProduct);
    }


    public long deleteUser(int userId) {
        repository.deleteById(userId);
        return repository.count();
    }

    public UserDetails updateUserByFields(int userId, Map<String, Object> fields) {
        Optional<UserDetails> existingUser = repository.findById(userId);

        if (existingUser.isPresent()) {
            fields.forEach((key, value) -> {
                Field field = ReflectionUtils.findField(UserDetails.class, key);
                field.setAccessible(true);
                ReflectionUtils.setField(field, existingUser.get(), value);
            });
            return repository.save(existingUser.get());
        }
        return null;
    }
}
