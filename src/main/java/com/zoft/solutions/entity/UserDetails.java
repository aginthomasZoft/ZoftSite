package com.zoft.solutions.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import javax.persistence.*;

@Data
@Entity
public class UserDetails {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userId;
	
	private String name;
	private String password;
	private String email;
	private String status;
	private String lastLogin;
	
	
	    @OneToOne(cascade = CascadeType.ALL)
	    @JoinColumn(name = "accessId", referencedColumnName = "accessId")
	    private UserAccess userAccessId ;
	 


	public UserDetails(int userId, String name, String password, String email, String status, String lastLogin,
			UserAccess userAccessId) {
		super();
		this.userId = userId;
		this.name = name;
		this.password = password;
		this.email = email;
		this.status = status;
		this.lastLogin = lastLogin;
		this.userAccessId = userAccessId;
	}

	public UserDetails() {}
	
	@Override
	public String toString() {
		return "UserDetails [userId=" + userId + ", name=" + name + ", password=" + password + ", email=" + email
				+ ", status=" + status + ", lastLogin=" + lastLogin + ", userAccessId=" + userAccessId + "]";
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getLastLogin() {
		return lastLogin;
	}

	public void setLastLogin(String lastLogin) {
		this.lastLogin = lastLogin;
	}

	public UserAccess getUserAccessId() {
		return userAccessId;
	}

	public void setUserAccessId(UserAccess userAccessId) {
		this.userAccessId = userAccessId;
	}

	
	
	
}
