package com.baishui.hibernate.model.annotation;
 

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.hibernate.annotations.BatchSize;
 

@Entity
@BatchSize(size=7)
public class Category {

	private int id;
	private String plate;
	private Set<Topic> topics = new HashSet<Topic>();
	
	@Id
	@GeneratedValue
	public int getId() {
		return id;
	}
	@OneToMany(mappedBy="category",cascade={CascadeType.ALL})
	public Set<Topic> getTopics() {
		return topics;
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
	
	public void setTopics(Set<Topic> topics) {
		this.topics = topics;
	}
	 
	
	
	
}
