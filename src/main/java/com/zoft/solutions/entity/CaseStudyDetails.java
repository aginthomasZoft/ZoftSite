package com.zoft.solutions.entity;

import java.util.Arrays;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Entity
public class CaseStudyDetails {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int caseId;
	
	@NotBlank(message = "Title is required")
	private String caseTitle;
	
	@Column(length = 6000000)
	@NotBlank(message = "Content is required")
	private String content;
	
	@NotBlank(message = "Client is required")
	private String clients;
	
	@NotBlank(message = "Category is required")
	private String category;
	
	private String coverImg;

	@NotBlank(message = "Preview is required")
	private String preview;
	
	@NotBlank(message = "Status is required")
	private String status;
	
	private String[] tags=new String[100];
	
	@NotNull(message = "Created date is required")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private String createdDate;
	
	@NotNull(message = "Publishing date is required")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private String publishingDate;
	
	@NotNull(message = "Updated date is required")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private String updatedDate;
	
	@NotBlank(message = "Meta title is required")
	private String metaTitle;
	
	@NotBlank(message = "Meta description is required")
	private String metaDescription;
	
	@NotNull(message = "Published must be explicitly set")
	private boolean published;
	
	@NotNull(message = "Active must be explicitly set")
	private boolean active;
	
	 public CaseStudyDetails(){}	


	@Override
	public String toString() {
		return "CaseStudyDetails [caseId=" + caseId + ", caseTitle=" + caseTitle + ", content=" + content + ", clients="
				+ clients + ", category=" + category + ", coverImg=" + coverImg + ", preview=" + preview + ", status="
				+ status + ", tags=" + Arrays.toString(tags) + ", createdDate=" + createdDate
				+ ", publishingDate=" + publishingDate + ", updatedDate=" + updatedDate + ", metaTitle=" + metaTitle
				+ ", metaDescription=" + metaDescription + ", published=" + published + ", active=" + active + "]";
	}

	public CaseStudyDetails(int caseId, String caseTitle, String content, String clients, String category,
			String coverImg, String preview, String status, String[] tags,String createdDate,
			String publishingDate, String updatedDate, String metaTitle, String metaDescription, boolean published,
			boolean active) {
		super();
		this.caseId = caseId;
		this.caseTitle = caseTitle;
		this.content = content;
		this.clients = clients;
		this.category = category;
		this.coverImg = coverImg;
		this.preview = preview;
		this.status = status;
		this.tags = tags;
		this.createdDate = createdDate;
		this.publishingDate = publishingDate;
		this.updatedDate = updatedDate;
		this.metaTitle = metaTitle;
		this.metaDescription = metaDescription;
		this.published = published;
		this.active = active;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
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

	public boolean isPublished() {
		return published;
	}

	public void setPublished(boolean published) {
		this.published = published;
	}

	public int getCaseId() {
		return caseId;
	}





	public void setCaseId(int caseId) {
		this.caseId = caseId;
	}





	public String getCaseTitle() {
		return caseTitle;
	}





	public void setCaseTitle(String caseTitle) {
		this.caseTitle = caseTitle;
	}





	public String getContent() {
		return content;
	}





	public void setContent(String content) {
		this.content = content;
	}





	public String getClients() {
		return clients;
	}





	public void setClients(String clients) {
		this.clients = clients;
	}





	public String getCategory() {
		return category;
	}





	public void setCategory(String category) {
		this.category = category;
	}





	public String getCoverImg() {
		return coverImg;
	}





	public void setCoverImg(String coverImg) {
		this.coverImg = coverImg;
	}





	public String getPreview() {
		return preview;
	}





	public void setPreview(String preview) {
		this.preview = preview;
	}





	public String getStatus() {
		return status;
	}





	public void setStatus(String status) {
		this.status = status;
	}





	public String[] getTags() {
		return tags;
	}





	public void setTags(String[] tags) {
		this.tags = tags;
	}

	public String getCreatedDate() {
		return createdDate;
	}





	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}





	public String getPublishingDate() {
		return publishingDate;
	}





	public void setPublishingDate(String publishingDate) {
		this.publishingDate = publishingDate;
	}





	public String getUpdatedDate() {
		return updatedDate;
	}





	public void setUpdatedDate(String updatedDate) {
		this.updatedDate = updatedDate;
	}

}
