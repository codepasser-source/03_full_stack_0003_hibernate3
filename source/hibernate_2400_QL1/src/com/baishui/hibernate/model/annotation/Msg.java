package com.baishui.hibernate.model.annotation;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Msg {

	private int id;
	private String cont;
	private Topic topic;
	
	@Id
	@GeneratedValue
	public int getId() {
		return id;
	}
	public String getCont() {
		return cont;
	} 
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="topic_id")
	public Topic getTopic() {
		return topic;
	}
	
	public void setCont(String cont) {
		this.cont = cont;
	}
	public void setId(int id) {
		this.id = id;
	}
	public void setTopic(Topic topic) {
		this.topic = topic;
	}
	
	
}
