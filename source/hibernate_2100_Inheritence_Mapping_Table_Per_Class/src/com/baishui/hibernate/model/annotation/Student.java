package com.baishui.hibernate.model.annotation;

 
import javax.persistence.Entity;

@Entity 
public class Student extends Person {

	private int score;

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}
	
	
}