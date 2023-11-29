package com.zoft.solutions.entity;

import java.sql.Date;
import java.util.Arrays;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
@Entity
public class BlogDetails {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int blogId;
    
    @NotBlank(message = "Title is required")
	private String title;
	
	@Column(length = 6000000)
	@NotBlank(message = "Content is required")
	private String content;
	
	@NotBlank(message = "Blog URL is required")
	@Pattern(regexp = "^(https?|ftp):\\/\\/[\\w-]+(\\.[\\w-]+)+([\\w.,@?^=%&:/~+#-]*[\\w@?^=%&/~+#-])?$", message = "Invalid link format")
    private String blogUrl;
	
	@NotBlank(message = "Status is required")
	private String status;
	
	
	private String coverImage;
	
	@NotBlank(message = "Preview is required")
	private String preview;
	
	
	private String[] tags=new String[100];
	
	@NotNull(message = "Created date is required")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date createdDate;
	
	@NotNull(message = "Publishing date is required")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date publishingDate;
	
	@NotNull(message = "Updated date is required")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date updatedDate;
	
	@NotBlank(message = "Created by is required")
	private String createdBy;
	
	@NotBlank(message = "Meta title is required")
	private String metaTitle;
	
	@NotBlank(message = "Meta description is required")
	private String metaDescription;
	
	@NotNull(message = "Published must be explicitly set")
	private boolean published;

	@NotNull(message = "Active must be explicitly set")
	private boolean active;
	
	private String alternateImageName;
	
	private String coverImageName;
	
	private String coverImageUrl;
	
	@NotBlank(message = "Company is required")
	private String company;

	public BlogDetails() {}



	public BlogDetails(int blogId, @NotBlank(message = "Title is required") String title,
			@NotBlank(message = "Content is required") String content,
			@NotBlank(message = "Blog URL is required") @Pattern(regexp = "^(https?|ftp):\\/\\/[\\w-]+(\\.[\\w-]+)+([\\w.,@?^=%&:/~+#-]*[\\w@?^=%&/~+#-])?$", message = "Invalid link format") String blogUrl,
			@NotBlank(message = "Status is required") String status, String coverImage,
			@NotBlank(message = "Preview is required") String preview, String[] tags,
			@NotNull(message = "Created date is required") Date createdDate,
			@NotNull(message = "Publishing date is required") Date publishingDate,
			@NotNull(message = "Updated date is required") Date updatedDate,
			@NotBlank(message = "Created by is required") String createdBy,
			@NotBlank(message = "Meta title is required") String metaTitle,
			@NotBlank(message = "Meta description is required") String metaDescription,
			@NotNull(message = "Published must be explicitly set") boolean published,
			@NotNull(message = "Active must be explicitly set") boolean active, String alternateImageName,
			String coverImageName, String coverImageUrl, @NotBlank(message = "Company is required") String company) {
		super();
		this.blogId = blogId;
		this.title = title;
		this.content = content;
		this.blogUrl = blogUrl;
		this.status = status;
		this.coverImage = coverImage;
		this.preview = preview;
		this.tags = tags;
		this.createdDate = createdDate;
		this.publishingDate = publishingDate;
		this.updatedDate = updatedDate;
		this.createdBy = createdBy;
		this.metaTitle = metaTitle;
		this.metaDescription = metaDescription;
		this.published = published;
		this.active = active;
		this.alternateImageName = alternateImageName;
		this.coverImageName = coverImageName;
		this.coverImageUrl = coverImageUrl;
		this.company = company;
	}



	@Override
	public String toString() {
		return "BlogDetails [blogId=" + blogId + ", title=" + title + ", content=" + content + ", blogUrl=" + blogUrl
				+ ", status=" + status + ", coverImage=" + coverImage + ", preview=" + preview + ", tags="
				+ Arrays.toString(tags) + ", createdDate=" + createdDate + ", publishingDate=" + publishingDate
				+ ", updatedDate=" + updatedDate + ", createdBy=" + createdBy + ", metaTitle=" + metaTitle
				+ ", metaDescription=" + metaDescription + ", published=" + published + ", active=" + active
				+ ", alternateImageName=" + alternateImageName + ", coverImageName=" + coverImageName
				+ ", coverImageUrl=" + coverImageUrl + ", company=" + company + "]";
	}



	public String getAlternateImageName() {
		return alternateImageName;
	}



	public void setAlternateImageName(String alternateImageName) {
		this.alternateImageName = alternateImageName;
	}



	public String getCompany() {
		return company;
	}


	public void setCompany(String company) {
		this.company = company;
	}

	public String getCoverImageName() {
		return coverImageName;
	}


	public void setCoverImageName(String coverImageName) {
		this.coverImageName = coverImageName;
	}


	public String getCoverImageUrl() {
		return coverImageUrl;
	}


	public void setCoverImageUrl(String coverImageUrl) {
		this.coverImageUrl = coverImageUrl;
	}


	public boolean isActive() {
		return active;
	}


	public void setActive(boolean active) {
		this.active = active;
	}


	public boolean isPublished() {
		return published;
	}

	public void setPublished(boolean published) {
		this.published = published;
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


	public String[] getTags() {
		return tags;
	}


	public void setTags(String[] tags) {
		this.tags = tags;
	}



	public Date getCreatedDate() {
		return createdDate;
	}


	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}




	public Date getPublishingDate() {
		return publishingDate;
	}


	public void setPublishingDate(Date publishingDate) {
		this.publishingDate = publishingDate;
	}


	public Date getUpdatedDate() {
		return updatedDate;
	}


	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}


	public String getCreatedBy() {
		return createdBy;
	}


	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}


	
}
