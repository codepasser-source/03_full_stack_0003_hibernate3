package com.baishui.hibernate.annotation;

 
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class Husband { 
	private int id;
	private String name;
	private Wife wife;
	
	@Id
	@GeneratedValue
	public int getId() {
		return id;
	}
	
	 
	 
	public String getName() {
		return name;
	}
	
	@OneToOne
	@JoinColumn
	public Wife getWife() {
		return wife;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setWife(Wife wife) {
		this.wife = wife;
	}
	
	
	
}
