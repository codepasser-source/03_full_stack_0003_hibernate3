package com.baishui.hibernate.model.annotation;
 

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
 

@Entity
public class Category {

	private int id;
	private String plate;
	 
	
	@Id
	@GeneratedValue
	public int getId() {
		return id;
	}
	public String getPlate() {
		return plate;
	}
	 
	
	public void setId(int id) {
		this.id = id;
	}
	public void setPlate(String plate) {
		this.plate = plate;
	}
	 
	
	
	
}
