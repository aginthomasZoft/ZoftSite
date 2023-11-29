package com.zoft.solutions.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class CompanyLogos {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int logoId;
	
	private String altLogoImage; 
	
    private String logoImageUrl; 
    
    private String logoImageName;
    
	public String getAltLogoImageUrl() {
		return altLogoImage;
	}
	public void setAltLogoImageUrl(String altLogoImageUrl) {
		this.altLogoImage = altLogoImageUrl;
	}
	public int getLogoId() {
		return logoId;
	}
	public void setLogoId(int logoId) {
		this.logoId = logoId;
	}
	public String getLogoImageUrl() {
		return logoImageUrl;
	}
	public void setLogoImageUrl(String logoImageUrl) {
		this.logoImageUrl = logoImageUrl;
	}
	public String getLogoImageName() {
		return logoImageName;
	}
	public void setLogoImageName(String logoImageName) {
		this.logoImageName = logoImageName;
	}

    
    
}
