package com.baishui.hibernate.xml;

public class Student {

	private int id;
	private String name;
	private StudentIDCard siCard;
	
	 
 
	public String getName() {
		return name;
	}
	 
	 
	public void setName(String name) {
		this.name = name;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}


	public StudentIDCard getSiCard() {
		return siCard;
	}


	public void setSiCard(StudentIDCard siCard) {
		this.siCard = siCard;
	}
	 
	
	
	
}