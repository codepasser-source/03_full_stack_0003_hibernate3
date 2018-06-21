package com.baishui.hibernate.annotation;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
 
 
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name="anno_Group")
public class Group {

	private int id;
	private String title;
	private Set<User> users = new HashSet<User>();
	
	
	@Id
	@GeneratedValue
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

	@OneToMany(mappedBy="group") 
	public Set<User> getUsers() {
		return users;
	}

	public void setUsers(Set<User> users) {
		this.users = users;
	}
	
	
}
