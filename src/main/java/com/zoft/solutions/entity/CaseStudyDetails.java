package com.zoft.solutions.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.util.Arrays;

import javax.persistence.*;

@Data
@Entity
public class CaseStudyDetails {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int caseId;
	private String caseTitle;
	@Column(length = 6000000)
	private String content;
	private String clients;
	
	private String category;
	
	private String coverImg;

	private String preview;
	private String status;
	private String[] tags=new String[100];
	private String seo;
	private String createdDate;
	private String publishingDate;
	private String updatedDate;
	



	public CaseStudyDetails(int caseId, String caseTitle, String content, String clients, String category,
			String coverImg, String preview, String status, String[] tags, String seo, String createdDate,
			String publishingDate, String updatedDate) {
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
		this.seo = seo;
		this.createdDate = createdDate;
		this.publishingDate = publishingDate;
		this.updatedDate = updatedDate;
	}


   public CaseStudyDetails() {}


	@Override
	public String toString() {
		return "CaseStudyDetails [caseId=" + caseId + ", caseTitle=" + caseTitle + ", content=" + content + ", clients="
				+ clients + ", category=" + category + ", coverImg=" + coverImg + ", preview=" + preview + ", status="
				+ status + ", tags=" + Arrays.toString(tags) + ", seo=" + seo + ", createdDate=" + createdDate
				+ ", publishingDate=" + publishingDate + ", updatedDate=" + updatedDate + "]";
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





	public String getSeo() {
		return seo;
	}





	public void setSeo(String seo) {
		this.seo = seo;
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
