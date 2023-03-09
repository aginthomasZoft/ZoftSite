package com.zoft.solutions.controller;

import com.zoft.solutions.entity.UserAccess;
import com.zoft.solutions.service.AccessService;

import io.swagger.annotations.ApiOperation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/Access")
@CrossOrigin
public class AccessController {

	@Autowired
    private AccessService service;

    @PostMapping
    @ApiOperation(value="Store AccessDetail Details")
    public UserAccess addAccessDetails(@RequestBody UserAccess access) {
        return service.saveAccessDetail(access);
    }

    @GetMapping
    @ApiOperation(value="Search AccessDetail")
    public List<UserAccess> getAllAccessDetails() {
        return service.getAccessDetails();
    }

    @GetMapping("/{accessId}")
    @ApiOperation(value="Search By Id AccessDetail")
    public UserAccess getAccessDetailById(@PathVariable int userId) {
        return service.getAccessDetailById(userId);
    }


    @PutMapping("/{accessId}")
    @ApiOperation(value="Update By Id AccessDetail")
    public UserAccess updateAccessDetailById(@PathVariable int userId, @RequestBody UserAccess access) {
       return service.updateAccessDetail(userId, access);
    }

    @PatchMapping("/{accessId}")
    public UserAccess updateAccessDetailFields(@PathVariable int userId,@RequestBody Map<String, Object> fields){
        return service.updateAccessDetailByFields(userId,fields);
    }

    @DeleteMapping("/{accessId}")
    @ApiOperation(value="Delete AccessDetail details")
    public long deleteAccessDetailById(@PathVariable int userId) {
        return service.deleteAccessDetail(userId);
    }
}
