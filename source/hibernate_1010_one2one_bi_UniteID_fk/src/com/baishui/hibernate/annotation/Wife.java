package com.baishui.hibernate.annotation;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.OneToOne;
 
 

@Entity
@IdClass(WifePk.class)
public class Wife {

	private int id;
	private String name;
	private String  age;
	private Husband husband;
	
	@Id
	@GeneratedValue
	public int getId() {
		return id;
	}
	
	@Id
	public String getName() {
		return name;
	}
	
 
	
	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public void setId(int id) {
		this.id = id;
	}
	public void setName(String name) {
		this.name = name;
	}

	@OneToOne(mappedBy="wife")	
	public Husband getHusband() {
		return husband;
	}

	public void setHusband(Husband husband) {
		this.husband = husband;
	}
	
	
}
