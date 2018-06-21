package com.baishui.hibernate.model.annotation;

import java.util.Date;
 
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Topic {
	
	private int id;
	private String title;
	private Category category;
	
	private Date createTime;
	
	@Id
	@GeneratedValue
	public int getId() {
		return id;
	}
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="category_id")
	public Category getCategory() {
		return category;
	}
	
	public String getTitle() {
		return title;
	}
	public void setCategory(Category category) {
		this.category = category;
	}
	public void setId(int id) {
		this.id = id;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	

}
