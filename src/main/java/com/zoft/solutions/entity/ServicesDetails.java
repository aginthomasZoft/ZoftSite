package com.zoft.solutions.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data
@Entity
public class ServicesDetails {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int serviceId;
	
	private String title;
	private String img;
	
	@Column(length = 6000000)
	private String serviceDescription;
	
	@Column(length = 6000000)
	private String serviceBenefits;
	private String sideImage;
	
	
	@JsonIgnoreProperties("ServicesDetails")
	@OneToMany(fetch = FetchType.EAGER,mappedBy = "servicesDetails", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<SubServices> SubServices= new ArrayList<>();
	
	public ServicesDetails() {}

	public int getServiceId() {
		return serviceId;
	}

	public void setServiceId(int serviceId) {
		this.serviceId = serviceId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public String getServiceDescription() {
		return serviceDescription;
	}

	public void setServiceDescription(String serviceDescription) {
		this.serviceDescription = serviceDescription;
	}

	public String getServiceBenefits() {
		return serviceBenefits;
	}

	public void setServiceBenefits(String serviceBenefits) {
		this.serviceBenefits = serviceBenefits;
	}

	public String getSideImage() {
		return sideImage;
	}

	public List<SubServices> getSubServices() {
		return SubServices;
	}

	public void setSubServices(List<SubServices> subServices) {
		SubServices = subServices;
	}

	public void setSideImage(String sideImage) {
		this.sideImage = sideImage;
	}

	



}
