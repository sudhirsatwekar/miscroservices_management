package com.user.UserService.entities;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

//lombok not working

@Entity
@Table(name="miro_users")
public class User {
	@Id
	@Column(name="ID")
	private String userId;
	
	@Column(name="NAME",length=20)
	private String name;
	
	@Column(name="EMAIL")
	private String email;
	
	@Column(name="ABOUT")
	private String about;
	
	//will come from another microservice
	@Transient
	private List<Rating> ratings=new ArrayList<>();
	
//	//will come from another microservice
//		@Transient
//		private List<Rating> hotels=new ArrayList<>();

	public User() {
		super();
	}


	public User(String userId, String name, String email, String about, List<Rating> ratings) {
		super();
		this.userId = userId;
		this.name = name;
		this.email = email;
		this.about = about;
		this.ratings = ratings;
		
	}

	

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAbout() {
		return about;
	}

	public void setAbout(String about) {
		this.about = about;
	}


	public List<Rating> getRatings() {
		return ratings;
	}
	


	public void setRatings(List<Rating> ratings) {
		this.ratings = ratings;
	}
	@Override
	public String toString() {
		return "User [userId=" + userId + ", name=" + name + ", email=" + email + ", about=" + about + "]";
	}

}
