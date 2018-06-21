package com.baishui.hibernate.xml;

import java.util.HashSet;
import java.util.Set;

 
 
public class Group {

	private int id;
	private String title;
	private Set<User> users = new HashSet<User>(); 
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}

	public Set<User> getUsers() {
		return users;
	}

	public void setUsers(Set<User> users) {
		this.users = users;
	}
	
	
}
