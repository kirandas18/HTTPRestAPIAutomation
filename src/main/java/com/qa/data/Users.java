package com.qa.data;

public class Users {
	
	String name;
	String job;
	String id;
	String CreatedAt;
	
	
	public Users(){
		
	}
	
	public Users(String name,String job) {
		
		this.name=name;
		this.job=job;
	}

	
	
	//Getters and Setters methods
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCreatedAt() {
		return CreatedAt;
	}

	public void setCreatedAt(String createdAt) {
		CreatedAt = createdAt;
	}

	public String getJob() {
		return job;
	}

	public void setJob(String job) {
		this.job = job;
	}
	
	
	
	
}
