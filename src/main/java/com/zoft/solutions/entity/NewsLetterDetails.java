package com.zoft.solutions.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
@Entity
public class NewsLetterDetails {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int newsletterId;
	
	@NotBlank(message = "Email is required")
	@Pattern(regexp = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Z|a-z]{2,6}$", message = "Invalid email format")
	private String email;
	
	
	
	public NewsLetterDetails(int newsletterId, String email) {
		super();
		this.newsletterId = newsletterId;
		this.email = email;
	}



	public NewsLetterDetails() {}



	@Override
	public String toString() {
		return "NewsLetterDetails [newsletterId=" + newsletterId + ", email=" + email + "]";
	}



	public int getNewsletterId() {
		return newsletterId;
	}



	public void setNewsletterId(int newsletterId) {
		this.newsletterId = newsletterId;
	}



	public String getEmail() {
		return email;
	}



	public void setEmail(String email) {
		this.email = email;
	}
	
	
}
