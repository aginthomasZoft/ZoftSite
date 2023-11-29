package com.zoft.solutions.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
@Entity
public class ContactUs {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int contactId;
	
	@NotBlank(message = "Full name is required")
	private String fullName;
	
	@NotBlank(message = "Email is required")
	@Pattern(regexp = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Z|a-z]{2,6}$", message = "Invalid email format")
	private String email;
	
	@NotBlank(message = "Company name is required")
	private String companyName;
	
	@NotBlank(message = "Phone is required")
	@Pattern(regexp = "^[0-9]{10}$", message = "Invalid phone number format")
	private String phone;
	
	private String designation;
	
	@Column(length = 6000000)
	@NotBlank(message = "Message is required")
	private String message;
	


	public ContactUs(int contactId, String fullName, String email, String companyName, String phone, String designation,
			String message) {
		super();
		this.contactId = contactId;
		this.fullName = fullName;
		this.email = email;
		this.companyName = companyName;
		this.phone = phone;
		this.designation = designation;
		this.message = message;
	}
	
	 public ContactUs() {}

	@Override
	public String toString() {
		return "ContactUs [contactId=" + contactId + ", fullName=" + fullName + ", email=" + email + ", companyName="
				+ companyName + ", phone=" + phone + ", designation=" + designation + ", message=" + message + "]";
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
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
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
