package com.zoft.solutions.controller;

import com.zoft.solutions.entity.UserDetails;
import com.zoft.solutions.service.UserService;

import io.swagger.annotations.ApiOperation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/User")
@CrossOrigin
public class UserController {

	@Autowired
    private UserService service;

    @PostMapping
    @ApiOperation(value="Store User Details")
    public UserDetails addUser(@RequestBody UserDetails user) {
        return service.saveUser(user);
    }

    @GetMapping
    @ApiOperation(value="Search User")
    public List<UserDetails> getAllUsers() {
        return service.getUsers();
    }

    @GetMapping("/{userId}")
    @ApiOperation(value="Search By Id User")
    public UserDetails getUserById(@PathVariable int userId) {
        return service.getUserById(userId);
    }


    @PutMapping("/{userId}")
    @ApiOperation(value="Update By Id User")
    public UserDetails updateUserById(@PathVariable int userId, @RequestBody UserDetails user) {
       return service.updateUser(userId, user);
    }

    @PatchMapping("/{userId}")
    public UserDetails updateUserFields(@PathVariable int userId,@RequestBody Map<String, Object> fields){
        return service.updateUserByFields(userId,fields);
    }

    @DeleteMapping("/{userId}")
    @ApiOperation(value="Delete User details")
    public long deleteUserById(@PathVariable int userId) {
        return service.deleteUser(userId);
    }
}
