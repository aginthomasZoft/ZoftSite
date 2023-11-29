package com.zoft.solutions.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
public class Admin {

	@Id
    private int id;
	
	@NotBlank(message = "Username is required")
    private String username;
	
	@NotBlank(message = "Password is required")
	@Size(min = 12, max = 12, message = "Password must be 12 characters long")
    private String password;
    private String role;
    
	@Override
	public String toString() {
		return "Admin [id=" + id + ", username=" + username + ", password=" + password + ", role=" + role + "]";
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
    
    
    
}
