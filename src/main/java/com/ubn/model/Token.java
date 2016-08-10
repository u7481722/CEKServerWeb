package com.ubn.model;

import java.io.Serializable;

public class Token implements Serializable{

	private long id;
	 
	private String token;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
	  
	  
	  
	
}
