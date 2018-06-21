package com.baishui.hibernate.annotation;

 

public class WifePk implements java.io.Serializable{

	private int id;
	private String name;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@Override
	public boolean equals(Object obj) {
		 if(obj instanceof WifePk){
			 WifePk pk = (WifePk)obj;
			 if(this.id == pk.getId() && this.name.equals(pk.getName())){
				 return true;
			 }
		 }
		return false;
	}
	@Override
	public int hashCode() { 
		return this.name.hashCode();
	}
}
