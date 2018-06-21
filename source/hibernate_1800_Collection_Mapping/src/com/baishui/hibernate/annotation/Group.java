package com.baishui.hibernate.annotation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MapKey;
import javax.persistence.OrderBy;
 
 
import javax.persistence.OneToMany;
import javax.persistence.Table;

 


@Entity
@Table(name="anno_Group")
public class Group {

	private int id;
	private String title;
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
	
	//set 集合写法
	/*private Set<User> users = new HashSet<User>(); 
	@OneToMany(mappedBy="group",
			cascade={CascadeType.ALL}
	) 
	public Set<User> getUsers() {
		return users;
	} 
	public void setUsers(Set<User> users) {
		this.users = users;
	}*/
	//list 写法
	/*
	private List<User> users = new ArrayList<User>(); 
	@OneToMany(mappedBy="group",cascade={CascadeType.ALL}) 
	@OrderBy("userName ASC")
	public List<User> getUsers() {
		return users;
	}
	public void setUsers(List<User> users) {
		this.users = users;
	}
	
	*/
	private Map<Integer, User> users = new HashMap<Integer, User>();
	
	@OneToMany(mappedBy="group",cascade={CascadeType.ALL},fetch=FetchType.EAGER)
	@MapKey(name="id")
	public Map<Integer, User> getUsers() {
		return users;
	}
	public void setUsers(Map<Integer, User> users) {
		this.users = users;
	}
	
	
	
	
	
}
