package com.zoft.solutions.entity;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
@Entity
public class SubServices {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int subServiceId;
	
	private String subtitle;
	private String subimg;
	
	@Column(length = 6000000)
	private String subserviceDescription;
	
	
	@JsonIgnore
	@ManyToOne
    @JoinColumn(name = "service_id",referencedColumnName = "serviceId")
    private ServicesDetails servicesDetails;





	public SubServices(int subServiceId, String subtitle, String subimg, String subserviceDescription,
			ServicesDetails servicesDetails) {
		super();
		this.subServiceId = subServiceId;
		this.subtitle = subtitle;
		this.subimg = subimg;
		this.subserviceDescription = subserviceDescription;
		this.servicesDetails = servicesDetails;
	}


	public SubServices() {}


	public int getSubServiceId() {
		return subServiceId;
	}


	public void setSubServiceId(int subServiceId) {
		this.subServiceId = subServiceId;
	}


	public String getSubtitle() {
		return subtitle;
	}


	public void setSubtitle(String subtitle) {
		this.subtitle = subtitle;
	}


	public String getSubimg() {
		return subimg;
	}


	public void setSubimg(String subimg) {
		this.subimg = subimg;
	}


	public String getSubserviceDescription() {
		return subserviceDescription;
	}


	public void setSubserviceDescription(String subserviceDescription) {
		this.subserviceDescription = subserviceDescription;
	}


	public ServicesDetails getServicesDetails() {
		return servicesDetails;
	}



	public void setServicesDetails(ServicesDetails servicesDetails) {
		this.servicesDetails = servicesDetails;
	}




}
