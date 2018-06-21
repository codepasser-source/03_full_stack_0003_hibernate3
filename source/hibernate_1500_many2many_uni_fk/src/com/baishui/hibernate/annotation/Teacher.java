package com.baishui.hibernate.annotation;

import java.util.HashSet;
import java.util.Set;
 

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany; 
 


@Entity
 
public class Teacher {

	private int id;
	private String title;
	private Set<Student> students = new HashSet<Student>();
	
	
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

	@ManyToMany
	@JoinTable(name="joinTable",
	   joinColumns={@JoinColumn(name="teacher_id")},
	   inverseJoinColumns={@JoinColumn(name="student_id")}
	)
	public Set<Student> getStudents() {
		return students;
	}

	public void setUsers(Set<Student> students) {
		this.students = students;
	}
	
	
}
