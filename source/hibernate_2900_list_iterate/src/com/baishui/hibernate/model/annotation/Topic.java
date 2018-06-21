package com.baishui.hibernate.model.annotation;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
 
import javax.persistence.Entity;

import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
 
import javax.persistence.OneToMany;

@Entity 
public class Topic {
	
	private int id;
	private String title;
	private Date createTime; 
	private Category category;
	private Set<Msg> msgs = new HashSet<Msg>();
	
	
	@Id
	@GeneratedValue
	public int getId() {
		return id;
	}
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="category_id")
	public Category getCategory() {
		return category;
	}
	
	@OneToMany(mappedBy="topic")
    public Set<Msg> getMsgs() {
		return msgs;
	}
	
	
	public String getTitle() {
		return title;
	}

	public void setMsgs(Set<Msg> msgs) {
		this.msgs = msgs;
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
