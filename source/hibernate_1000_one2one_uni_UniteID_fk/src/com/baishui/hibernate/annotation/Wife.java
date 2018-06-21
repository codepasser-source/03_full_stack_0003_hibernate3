package com.baishui.hibernate.annotation;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.IdClass;
 
 

@Entity
@IdClass(WifePk.class)
public class Wife {

	private int id;
	private String name;
	private String  age;
	
	
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
	
	
}
