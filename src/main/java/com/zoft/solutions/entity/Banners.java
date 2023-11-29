package com.zoft.solutions.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

@Entity
public class Banners {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int bannerId;

	@NotBlank(message = "Banner Title is required")
    private String bannerTitle;
	
	@NotBlank(message = "Banner Description is required")
    private String bannerDescription;
	
	private String altBannerImageName;
	
    private String bannerImageName; 
	
    private String bannerImageUrl; 
    
    @NotBlank(message = "Banner link url is required")
	@Pattern(regexp = "^(https?|ftp):\\/\\/[\\w-]+(\\.[\\w-]+)+([\\w.,@?^=%&:/~+#-]*[\\w@?^=%&/~+#-])?$", message = "Invalid link format")
    private String bannerLinkUrl;
    
    
	public String getAltBannerImageName() {
		return altBannerImageName;
	}
	public void setAltBannerImageName(String altBannerImageName) {
		this.altBannerImageName = altBannerImageName;
	}
	public int getBannerId() {
		return bannerId;
	}
	public void setBannerId(int bannerId) {
		this.bannerId = bannerId;
	}

	public String getBannerTitle() {
		return bannerTitle;
	}
	public void setBannerTitle(String bannerTitle) {
		this.bannerTitle = bannerTitle;
	}
	public String getBannerDescription() {
		return bannerDescription;
	}
	public void setBannerDescription(String bannerDescription) {
		this.bannerDescription = bannerDescription;
	}
	public String getBannerImageName() {
		return bannerImageName;
	}
	public void setBannerImageName(String bannerImageName) {
		this.bannerImageName = bannerImageName;
	}
	public String getBannerImageUrl() {
		return bannerImageUrl;
	}
	public void setBannerImageUrl(String bannerImageUrl) {
		this.bannerImageUrl = bannerImageUrl;
	}
	public String getBannerLinkUrl() {
		return bannerLinkUrl;
	}
	public void setBannerLinkUrl(String bannerLinkUrl) {
		this.bannerLinkUrl = bannerLinkUrl;
	}


    
}
