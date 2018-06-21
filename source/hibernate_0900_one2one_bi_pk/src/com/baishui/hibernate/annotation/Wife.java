package com.baishui.hibernate.annotation;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;

@Entity
public class Wife {

	private int id;
	private String name;
	private Husband husband;
	
	
	@Id
	@GeneratedValue
	public int getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	
	@OneToOne 
	@PrimaryKeyJoinColumn
	public Husband getHusband() {
		return husband;
	}
	
	
	public void setHusband(Husband husband) {
		this.husband = husband;
	}
	public void setId(int id) {
		this.id = id;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
}
