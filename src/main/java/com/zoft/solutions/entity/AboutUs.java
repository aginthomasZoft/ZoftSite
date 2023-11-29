package com.zoft.solutions.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

@Entity
public class AboutUs {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int aboutUsId;

	@NotBlank(message = "About us Title is required")
    private String aboutUsTitle;
	
	@NotBlank(message = "About us Description is required")
	
	private String altAboutUsImageName;
	
    private String aboutUsDescription;
	
    private String aboutUsImageName; 
    
    private String aboutUsImageUrl;
    
    @NotBlank(message = "About us link url is required")
	@Pattern(regexp = "^(https?|ftp):\\/\\/[\\w-]+(\\.[\\w-]+)+([\\w.,@?^=%&:/~+#-]*[\\w@?^=%&/~+#-])?$", message = "Invalid link format")
    private String aboutUsLinkUrl;
    
	public String getAltAboutUsImageName() {
		return altAboutUsImageName;
	}
	public void setAltAboutUsImageName(String altAboutUsImageName) {
		this.altAboutUsImageName = altAboutUsImageName;
	}
	public String getAboutUsLinkUrl() {
		return aboutUsLinkUrl;
	}
	public void setAboutUsLinkUrl(String aboutUsLinkUrl) {
		this.aboutUsLinkUrl = aboutUsLinkUrl;
	}
	public int getAboutUsId() {
		return aboutUsId;
	}
	public void setAboutUsId(int aboutUsId) {
		this.aboutUsId = aboutUsId;
	}
	public String getAboutUsTitle() {
		return aboutUsTitle;
	}
	public void setAboutUsTitle(String aboutUsTitle) {
		this.aboutUsTitle = aboutUsTitle;
	}
	public String getAboutUsDescription() {
		return aboutUsDescription;
	}
	public void setAboutUsDescription(String aboutUsDescription) {
		this.aboutUsDescription = aboutUsDescription;
	}
	public String getAboutUsImageName() {
		return aboutUsImageName;
	}
	public void setAboutUsImageName(String aboutUsImageName) {
		this.aboutUsImageName = aboutUsImageName;
	}
	public String getAboutUsImageUrl() {
		return aboutUsImageUrl;
	}
	public void setAboutUsImageUrl(String aboutUsImageUrl) {
		this.aboutUsImageUrl = aboutUsImageUrl;
	} 
    
    
}
