package com.baishui.hibernate.model;

 

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.cfg.Configuration;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;


public class StudentTest {

	private static SessionFactory sf=null;
	
	@BeforeClass
	public static void beforeClass(){
		try {
			sf =  new AnnotationConfiguration().configure().buildSessionFactory(); //根据hibernate.cfg.xml创建Configuration

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testTeacherSave(){
		
		   Student st1 = new Student();
		   st1.setName("st1");
		   st1.setAge(24);
		   
		   
		   Session session = sf.openSession(); 
		   session.beginTransaction();        //开始事务 
		   session.save(st1);               //存储对象
		   session.getTransaction().commit(); //提交 
		   session.close();                   //关闭连接会话
		                 
		
	}
	
	@AfterClass
	public static void afterClass(){
		
		sf.close();  //关闭连接工厂
	}
	
	
	

}
