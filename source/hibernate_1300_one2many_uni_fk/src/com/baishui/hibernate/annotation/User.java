package com.baishui.hibernate.annotation;

import javax.persistence.Entity;
import javax.persistence.Id;
 
import javax.persistence.Table;
 
@Entity
@Table(name="anno_User")
public class User {

	private int id;
	private String userName;
	 
	
	@Id
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	 
	
}
