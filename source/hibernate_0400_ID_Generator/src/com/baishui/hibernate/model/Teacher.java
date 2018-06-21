package com.baishui.hibernate.model;

import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
 
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

@Entity
@Table(name="teacher") 
@javax.persistence.TableGenerator(
   name="Teacher_Tab_GEN",
   table="GENERATOR_Table",
   pkColumnName="pr_key",
   valueColumnName="pr_value",
   pkColumnValue="TeacherID",
   allocationSize=1
)

//@SequenceGenerator(name="teacher_SEQ",sequenceName="teacher_SEQ_DB")
public class Teacher {

	private int id;
	private String name;
	private String title; 
	private String love;
	private Date createTime;
	private ZhiCheng zhicheng;
	
	
	@Id
    //@GeneratedValue(strategy=GenerationType.AUTO) 
	@GeneratedValue(strategy=GenerationType.TABLE,generator="Teacher_Tab_GEN") 
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	@Basic
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
	
	
	@Enumerated(EnumType.STRING)
	public ZhiCheng getZhicheng() {
		return zhicheng;
	}
	public void setZhicheng(ZhiCheng zhicheng) {
		this.zhicheng = zhicheng;
	}
	 
	
	
	
}
