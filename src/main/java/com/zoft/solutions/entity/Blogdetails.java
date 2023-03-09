package com.zoft.solutions.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.util.Arrays;

import javax.persistence.*;

@Data
@Entity

public class Blogdetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int blogId;
	private String title;
	
	@Column(length = 6000000)
	private String content;
	private String blogUrl;
	private String status;
	
	private String coverImage;
	private String preview;
	private String category;
	private String[] tags=new String[100];
	private String seo;
	private Date createdDate;
	private String publishingDate;
	private String updatedDate;
	private String createdBy;
	

    


	public Blogdetails(int blogId, String title, String content, String blogUrl, String status, String coverImage,
			String preview, String category, String[] tags, String seo, Date createdDate, String publishingDate,
			String updatedDate, String createdBy) {
		super();
		this.blogId = blogId;
		this.title = title;
		this.content = content;
		this.blogUrl = blogUrl;
		this.status = status;
		this.coverImage = coverImage;
		this.preview = preview;
		this.category = category;
		this.tags = tags;
		this.seo = seo;
		this.createdDate = createdDate;
		this.publishingDate = publishingDate;
		this.updatedDate = updatedDate;
		this.createdBy = createdBy;
	}

	public Blogdetails() {}
	
	@Override
	public String toString() {
		return "Blogdetails [blogId=" + blogId + ", title=" + title + ", content=" + content + ", blogUrl=" + blogUrl
				+ ", status=" + status + ", coverImage=" + coverImage + ", preview=" + preview + ", category="
				+ category + ", tags=" + Arrays.toString(tags) + ", seo=" + seo + ", createdDate=" + createdDate
				+ ", publishingDate=" + publishingDate + ", updatedDate=" + updatedDate + ", createdBy=" + createdBy
				+ "]";
	}


	public int getBlogId() {
		return blogId;
	}


	public void setBlogId(int blogId) {
		this.blogId = blogId;
	}


	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public String getContent() {
		return content;
	}


	public void setContent(String content) {
		this.content = content;
	}


	public String getBlogUrl() {
		return blogUrl;
	}


	public void setBlogUrl(String blogUrl) {
		this.blogUrl = blogUrl;
	}


	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}


	public String getCoverImage() {
		return coverImage;
	}


	public void setCoverImage(String coverImage) {
		this.coverImage = coverImage;
	}


	public String getPreview() {
		return preview;
	}


	public void setPreview(String preview) {
		this.preview = preview;
	}


	public String getCategory() {
		return category;
	}


	public void setCategory(String category) {
		this.category = category;
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


	public Date getCreatedDate() {
		return createdDate;
	}


	public void setCreatedDate(Date createdDate) {
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


	public String getCreatedBy() {
		return createdBy;
	}


	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}


	
}
