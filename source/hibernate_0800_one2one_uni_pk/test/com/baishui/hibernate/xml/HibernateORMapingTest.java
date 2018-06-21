package com.baishui.hibernate.xml;

 

 
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.tool.hbm2ddl.SchemaExport;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.baishui.hibernate.annotation.Husband;
import com.baishui.hibernate.annotation.Wife;



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
	
	@Test
	public void one2oneUniPkAnnotionTest(){
		
		Wife w1 = new Wife();
		w1.setName("xr");
		
		Husband h1 = new Husband();
		h1.setName("yy");
		h1.setWife(w1); 
		
		Session session = sf.getCurrentSession(); 
		session.beginTransaction(); 
		session.save(w1);
		session.save(h1);
		session.getTransaction().commit();
	 
		
	}
	
	@Test
	public void one2oneUniPkXmlTest(){
		
		try {
			
		    Student s1= new Student();
			s1.setName("xr"); 
			StudentIDCard sc1=new StudentIDCard();
			sc1.setNum("521");
			sc1.setStudent(s1); 
			Session session=sf.getCurrentSession(); 
			session.beginTransaction();
			session.save(s1); 
			session.save(sc1); 
			session.getTransaction().commit(); 

			/*	
			Session session=sf.getCurrentSession();
			
			session.beginTransaction();
			StudentIDCard s =(StudentIDCard)session.load(StudentIDCard.class, 1);	
			
			System.out.println(s.getStudent().getName());
			session.getTransaction().commit();*/
			
			 
			  
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		 
		 
		
	}
	
	@AfterClass
	public static void afterClass(){ 
		try {
			sf.close();  //关闭连接工厂
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	 public static void main(String[] args) {
		
		 SchemaExport se = new SchemaExport(new  AnnotationConfiguration().configure());
		 se.create(false, false);
		 
	}
	
	
}
