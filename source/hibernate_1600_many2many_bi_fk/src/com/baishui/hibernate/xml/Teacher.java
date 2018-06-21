package com.baishui.hibernate.xml;

import java.util.HashSet;
import java.util.Set;
 
 

 
 
public class Teacher {

	private int id;
	private String title;
	private Set<Student> students = new HashSet<Student>();
 
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

	public Set<Student> getStudents() {
		return students;
	}

	public void setStudents(Set<Student> students) {
		this.students = students;
	}

 
 
	 
	
	
}
