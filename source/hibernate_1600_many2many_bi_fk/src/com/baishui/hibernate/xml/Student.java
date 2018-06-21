package com.baishui.hibernate.xml;

 

import java.util.HashSet;
import java.util.Set;
 
 
 
public class Student {

	private int id;
	private String name;
	private Set<Teacher> teachers = new HashSet<Teacher>(); 
	
	
 
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	 
 
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Set<Teacher> getTeachers() {
		return teachers;
	}
	public void setTeachers(Set<Teacher> teachers) {
		this.teachers = teachers;
	}
	
 
	
	 
	
	 
	
}
