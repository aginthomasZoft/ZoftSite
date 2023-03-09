package com.zoft.solutions.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import javax.persistence.*;

@Data
@Entity
public class UserAccess {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int accessId;
	
	private String blog;
	private String caseStudy;
	private String paymaster;
	private String status;
	private String createdDate;
	
	 @OneToOne(mappedBy = "userAccessId")
    private UserDetails userId;
	




	public UserAccess(int accessId, String blog, String caseStudy, String paymaster, String status, String createdDate) {
		super();
		this.accessId = accessId;
		this.blog = blog;
		this.caseStudy = caseStudy;
		this.paymaster = paymaster;
		this.status = status;
		this.createdDate = createdDate;
		
	}

    public UserAccess() {}

	@Override
	public String toString() {
		return "UserAccess [accessId=" + accessId + ", blog=" + blog + ", caseStudy=" + caseStudy + ", paymaster="
				+ paymaster + ", status=" + status + ", createdDate=" + createdDate + ", userId=" + "]";
	}



	public int getAccessId() {
		return accessId;
	}

	public void setAccessId(int accessId) {
		this.accessId = accessId;
	}

	public String getBlog() {
		return blog;
	}

	public void setBlog(String blog) {
		this.blog = blog;
	}

	public String getCaseStudy() {
		return caseStudy;
	}

	public void setCaseStudy(String caseStudy) {
		this.caseStudy = caseStudy;
	}

	public String getPaymaster() {
		return paymaster;
	}

	public void setPaymaster(String paymaster) {
		this.paymaster = paymaster;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}

	
	
	
	
}
