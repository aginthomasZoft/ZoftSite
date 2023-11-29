package com.zoft.solutions.service;



import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zoft.solutions.entity.ServicesDetails;
import com.zoft.solutions.entity.SubServices;
import com.zoft.solutions.respository.ServicesRepository;
import com.zoft.solutions.respository.SubServicesRepository;

@Service
public class ServicesService {

	 @Autowired
	    private ServicesRepository servicesDetailsRepository;

	    @Autowired
	    private SubServicesRepository subServicesRepository;

	    public List<ServicesDetails> getAllServices() {
	        return servicesDetailsRepository.findAll();
	    }

	    public ServicesDetails getServiceById(int id) {
	        return servicesDetailsRepository.findById(id)
	                .orElseThrow(() -> new EntityNotFoundException("Service not found with id: " + id));
	    }

	    public ServicesDetails createService(ServicesDetails servicesDetails) {

	    	List<SubServices> subServices=servicesDetails.getSubServices();
	    	servicesDetailsRepository.insertServicesDetails(servicesDetails.getTitle(),servicesDetails.getImg(),servicesDetails.getServiceDescription(),servicesDetails.getServiceBenefits(),servicesDetails.getSideImage());
	    	int serviceId=servicesDetailsRepository.getLastInsertedServiceId();

	    	for (SubServices subService : subServices) {
	    		subServicesRepository.insertSubServices(subService.getSubtitle(), subService.getSubimg(), subService.getSubserviceDescription(), serviceId);
	    	}

			return getServiceById(serviceId);
	    }

	    public void updateService(int serviceId, ServicesDetails servicesDetails) {
	    	
	    	List<SubServices> subServices=servicesDetails.getSubServices();
	    	ServicesDetails existingService = servicesDetailsRepository.findById(serviceId).get(); // DB
	    	existingService.setImg(servicesDetails.getImg());
	    	existingService.setServiceBenefits(servicesDetails.getServiceBenefits());
	    	existingService.setServiceDescription(servicesDetails.getServiceDescription());
	    	existingService.setSideImage(servicesDetails.getSideImage());
	    	existingService.setTitle(servicesDetails.getTitle());
	    	
	    
	    	for (SubServices subService : subServices) {
            updateSubService(serviceId, subService.getSubServiceId(), subService);
	    	}
	    
	        
	    }

	    public void deleteService(int id) {
	        ServicesDetails servicesDetails = getServiceById(id);
	        servicesDetailsRepository.delete(servicesDetails);
	    }



	    public List<SubServices> getSubServiceByServiceId(int Id) {
	        return servicesDetailsRepository.findSubServicesByServiceId(Id);

	    }

	    public SubServices createSubService(int id, SubServices subService) {
	
	    	ServicesDetails servicesDetails = getServiceById(id);
	        subService.setServicesDetails(servicesDetails);
	        return subServicesRepository.save(subService);
	    }

	    public Optional<SubServices> updateSubService(int id,int subId, SubServices subService) {
	    	 subServicesRepository.updateSubServicesByServiceIdAndSubServiceId(subService.getSubtitle(), subService.getSubimg(), subService.getSubserviceDescription(), id, subId);
			return subServicesRepository.findById(subId);
	    }

	    public void deleteSubService(int subId,int Id) {

	    	subServicesRepository.deleteSubServicesByServiceIdAndSubServiceId(subId, Id);
	    }

		public void deleteAllSubServiceByServiceId(int id) {
			subServicesRepository.deleteAllByServiceId(id);
			
		}


}
