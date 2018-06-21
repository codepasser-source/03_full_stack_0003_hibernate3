package com.baishui.hibernate.model;

import java.util.Date; 
import javax.persistence.Column;
import javax.persistence.Entity;
 
 
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

@Entity
@Table(name="teacher")  
@IdClass(TeacherPK.class)
public class Teacher {

	private int id;
	private String name;
	
	
	private String title; 
	private String love;
	private Date createTime; 
 
	
	@Id 
    public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	@Id 
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@Column(name="_title")
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
	@Transient
	public String getLove() {
		return love;
	}
	public void setLove(String love) {
		this.love = love;
	}
	
	@Temporal(TemporalType.DATE)
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
 
	
	
	
}
