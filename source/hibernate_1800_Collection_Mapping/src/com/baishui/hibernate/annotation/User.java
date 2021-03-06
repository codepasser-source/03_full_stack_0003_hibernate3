package com.baishui.hibernate.annotation;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
 
import javax.persistence.ManyToOne;
 
import javax.persistence.Table;
 
@Entity
@Table(name="anno_User")
public class User {

	private int id;
	private String userName;
	private Group group;
	
	@Id
	@GeneratedValue
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
	
	@ManyToOne(cascade={CascadeType.ALL},fetch=FetchType.EAGER) 
	public Group getGroup() {
		return group;
	}
	public void setGroup(Group group) {
		this.group = group;
	}
	
	 
	
}
