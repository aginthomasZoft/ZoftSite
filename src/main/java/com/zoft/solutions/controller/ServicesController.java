package com.zoft.solutions.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zoft.solutions.entity.ServicesDetails;
import com.zoft.solutions.entity.SubServices;
import com.zoft.solutions.service.ServicesService;

@RestController
@RequestMapping("/Services")
@CrossOrigin
public class ServicesController {

	@Autowired
    private ServicesService servicesService;

    // Get all services
    @GetMapping("/")
    public List<ServicesDetails> getAllServices() {
        return servicesService.getAllServices();
    }

    // Get a service by id
    @GetMapping("/{id}")
    public ServicesDetails getServiceById(@PathVariable int id) {
        return servicesService.getServiceById(id);
    }

    // Create a new service
    @PostMapping("/")
    public ServicesDetails createService(@RequestBody ServicesDetails servicesDetails) {
        return servicesService.createService(servicesDetails);
    }

    // Update an existing service
    @PutMapping("/{id}")
    public void updateService(@PathVariable int id, @RequestBody ServicesDetails servicesDetails) {
       servicesService.updateService(id, servicesDetails);
    }

    // Delete a service by id
    @DeleteMapping("/{id}")
    public void deleteService(@PathVariable int id) {
        servicesService.deleteService(id);
    }

    // Get sub-service by service id
    @GetMapping("/sub-services/{id}")
    public List<SubServices> getSubService(@PathVariable int id) {
        return servicesService.getSubServiceByServiceId(id);
    }



//    // Create a new sub-service for a service
//    @PostMapping("/{id}/sub-services")
//    public SubServices createSubService(@PathVariable int id, @RequestBody SubServices subService) {
//        return servicesService.createSubService(id, subService);
//    }
//
//    // Update an existing sub-service of a service
//    @PutMapping("/{id}/sub-services/{subId}")
//    public Optional<SubServices> updateSubService(@PathVariable int id, @PathVariable int subId, @RequestBody SubServices subService) {
//        return servicesService.updateSubService(id,subId, subService);
//    }

    // Delete a sub-service of a service by id
    @DeleteMapping("/{id}/sub-services/{subId}")
    public void deleteSubService(@PathVariable int id, @PathVariable int subId) {
        servicesService.deleteSubService(subId,id);
    }
    
    // Delete all sub-service of a service by id
    @DeleteMapping("/sub-services/{id}")
    public void deleteAllSubServiceByServiceId(@PathVariable int id) {
        servicesService.deleteAllSubServiceByServiceId(id);
    }
}
