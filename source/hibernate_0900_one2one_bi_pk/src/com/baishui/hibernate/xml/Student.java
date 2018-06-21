package com.baishui.hibernate.xml;

public class Student {

	private int studentid;
	private String name;
	private StudentIDCard studentIdCard;
	
	 
	 
	public String getName() {
		return name;
	}
	public int getStudentid() {
		return studentid;
	}
	 
	 
	public StudentIDCard getStudentIdCard() {
		return studentIdCard;
	}
	public void setStudentIdCard(StudentIDCard studentIdCard) {
		this.studentIdCard = studentIdCard;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setStudentid(int studentid) {
		this.studentid = studentid;
	}
	
	
	
}
