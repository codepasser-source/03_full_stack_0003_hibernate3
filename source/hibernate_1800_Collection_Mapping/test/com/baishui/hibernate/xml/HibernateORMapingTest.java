package com.baishui.hibernate.xml; 
 
 
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.collections.iterators.EntrySetMapIterator;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.tool.hbm2ddl.SchemaExport;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.baishui.hibernate.annotation.Group;
import com.baishui.hibernate.annotation.User;
 

public class HibernateORMapingTest {

private static SessionFactory sf=null;
	
	@BeforeClass
	public static void beforeClass(){
		try {
			sf =  new AnnotationConfiguration().configure().buildSessionFactory(); //根据hibernate.cfg.xml创建Configuration
        } catch (Exception e) {
			e.printStackTrace();
		}
	} 
	
	
	/*@Test
	*//**
	 * Annotation
	 * 测试list集合排序
	 *//*
	public void testAnnotationListOraderCURD(){   
		Group group = new Group(); 
		User user1 = new User();
		user1.setUserName("a");
		user1.setGroup(group); 
		User user2 = new User();
		user2.setUserName("c"); 
	    user2.setGroup(group); 
		User user3 = new User();
		user3.setUserName("d"); 
	    user3.setGroup(group);   
	    User user4 = new User();
		user4.setUserName("b"); 
	    user4.setGroup(group);   
		group.setTitle("love"); 
		group.getUsers().add(user1); 
		group.getUsers().add(user2);
		group.getUsers().add(user3); 
		group.getUsers().add(user4); 
		Session session = sf.getCurrentSession(); 
		session.beginTransaction();  
		session.save(group); 
		session.getTransaction().commit(); 
		
		Session session2 = sf.getCurrentSession();
		session2.beginTransaction();
		Group group2 =(Group) session2.get(Group.class, 1);
		
		for(User user : group2.getUsers()){
			System.out.println(user.getUserName());
		}
		session2.getTransaction().commit();
		
	}*/
	@Test
	/**
	 * Annotation
	 * 测试map集合 
	 */
	public void testAnnotationMapOraderCURD(){   
		 
		
	 
		Session session = sf.getCurrentSession(); 
		session.beginTransaction();  
	    Group group =(Group)session.get(Group.class, 1); 
		session.getTransaction().commit(); 
		
		for(Map.Entry<Integer, User> entry : group.getUsers().entrySet()){
			
			 System.out.println(entry.getKey()+"--"+entry.getValue().getUserName());
			
		}
		 
		
	}
	public static void main(String[] args) {
		SchemaExport se = new SchemaExport(new AnnotationConfiguration().configure());
		se.create(false, false);
	}  
	
	@AfterClass
	public static void afterClass(){ 
		try {
			sf.close();  //关闭连接工厂
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
