package com.zoft.solutions.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
public class CareersDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int careerId;

    @NotBlank(message = "Role is required")
    private String role;
    
    private String jobCode;
    
    @NotBlank(message = "Work mode is required")
    private String workMode;
    
    @NotBlank(message = "Work Location is required")
    private String workLocation;
    
    @NotBlank(message = "Experience is required")
    private String experience;
    
    private Date expectedDateToJoin;
    
    @Column(length = 600000)
    @NotBlank(message = "Content is required")
    private String content;
    
    @NotNull(message = "Active must be explicitly set")
    private boolean active;
    
	@NotNull(message = "Updated date is required")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date updatedDate;
	
	@NotNull(message = "Created date is required")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date createdDate;
	
	@NotBlank(message = "Created by is required")
    private String createdBy;
    
	@NotNull(message = "Publishing date is required")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date publishingDate;
	
	@NotBlank(message = "Status is required")
    private String status;
    
    @NotNull(message = "Published must be explicitly set")
    private boolean published;
    
	@NotBlank(message = "Category is required")
    private String category;
	
	@NotBlank(message = "Meta title is required")
	private String metaTitle;
	
	@NotBlank(message = "Meta description is required")
	private String metaDescription;
	
	@NotBlank(message = "Preview is required")
	private String preview;
	
	@NotBlank(message = "Company is required")
	private String company;
	
	
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public String getJobCode() {
		return jobCode;
	}
	public void setJobCode(String jobCode) {
		this.jobCode = jobCode;
	}

	public String getMetaTitle() {
		return metaTitle;
	}
	public void setMetaTitle(String metaTitle) {
		this.metaTitle = metaTitle;
	}
	public String getMetaDescription() {
		return metaDescription;
	}
	public void setMetaDescription(String metaDescription) {
		this.metaDescription = metaDescription;
	}
	public String getPreview() {
		return preview;
	}
	public void setPreview(String preview) {
		this.preview = preview;
	}
	public int getId() {
		return careerId;
	}
	public void setId(int id) {
		this.careerId = id;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getWorkMode() {
		return workMode;
	}
	public void setWorkMode(String workMode) {
		this.workMode = workMode;
	}
	public String getWorkLocation() {
		return workLocation;
	}
	public void setWorkLocation(String workLocation) {
		this.workLocation = workLocation;
	}
	public String getExperience() {
		return experience;
	}
	public void setExperience(String experience) {
		this.experience = experience;
	}
	public Date getExpectedDateToJoin() {
		return expectedDateToJoin;
	}
	public void setExpectedDateToJoin(Date expectedDateToJoin) {
		this.expectedDateToJoin = expectedDateToJoin;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public boolean isActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
	}
	public Date getUpdatedDate() {
		return updatedDate;
	}
	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	public Date getPublishingDate() {
		return publishingDate;
	}
	public void setPublishingDate(Date publishingDate) {
		this.publishingDate = publishingDate;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public boolean isPublished() {
		return published;
	}
	public void setPublished(boolean published) {
		this.published = published;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
    
    
}
