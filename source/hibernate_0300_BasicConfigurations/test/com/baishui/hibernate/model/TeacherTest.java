package com.baishui.hibernate.model;

import java.util.Date;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;
 
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;


public class TeacherTest {

	private static SessionFactory sf=null;
	
	@BeforeClass
	public static void beforeClass(){
		
		sf =  new AnnotationConfiguration().configure().buildSessionFactory(); //根据hibernate.cfg.xml创建Configuration
	}
	
	@Test
	public void testTeacherSave(){
		   Teacher tr = new Teacher();
		   tr.setId(1);
		   tr.setName("tr1");
		   tr.setTitle("english");
		   tr.setLove("xinran");
		   tr.setCreateTime(new Date());
		   tr.setZhicheng(ZhiCheng.A);
		   
		   
		   Session session = sf.openSession(); 
		   session.beginTransaction();        //开始事务 
		   session.save(tr);               //存储对象
		   session.getTransaction().commit(); //提交 
		   session.close();                   //关闭连接会话
		                 
		
	}
	
	@AfterClass
	public static void afterClass(){
		
		sf.close();  //关闭连接工厂
	}
	
	
}
