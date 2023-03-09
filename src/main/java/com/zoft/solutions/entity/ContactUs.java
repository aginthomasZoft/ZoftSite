package com.zoft.solutions.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import javax.persistence.*;

@Data
@Entity
public class ContactUs {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int contactId;
	private String fullName;
	private String email;
	private String companyName;
	private String Phone;
	
	private String designation;
	@Column(length = 6000000)
	private String message;
	


	public ContactUs(int contactId, String fullName, String email, String companyName, String phone, String designation,
			String message) {
		super();
		this.contactId = contactId;
		this.fullName = fullName;
		this.email = email;
		this.companyName = companyName;
		this.Phone = phone;
		this.designation = designation;
		this.message = message;
	}
	
	 public ContactUs() {}

	@Override
	public String toString() {
		return "ContactUs [contactId=" + contactId + ", fullName=" + fullName + ", email=" + email + ", companyName="
				+ companyName + ", Phone=" + Phone + ", designation=" + designation + ", message=" + message + "]";
	}

	public int getContactId() {
		return contactId;
	}

	public void setContactId(int contactId) {
		this.contactId = contactId;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getPhone() {
		return Phone;
	}

	public void setPhone(String phone) {
		Phone = phone;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	
	
}
