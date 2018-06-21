package com.baishui.hibernate.model.annotation;

 
import java.util.Date;
 
import org.hibernate.LockMode;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
 import org.hibernate.cfg.AnnotationConfiguration;
 import org.hibernate.tool.hbm2ddl.SchemaExport;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

 

public class HibernateCache {
	private static SessionFactory sessionFactory;
	
	@BeforeClass
	public static void beforeClass() {
		//new SchemaExport(new AnnotationConfiguration().configure()).create(false, false);
		sessionFactory = new AnnotationConfiguration().configure().buildSessionFactory();
	}
	@AfterClass
	public static void afterClass() {
		sessionFactory.close();
	}
	
	@Test
	public void testSchemaExport() {
		new SchemaExport(new AnnotationConfiguration().configure()).create(false, true);
	} 
	public static void main(String[] args) {
		beforeClass();
	}
	
	
	@Test
	public void testSave(){
		
		Accmount acc = new Accmount(); 
		acc.setBalance(100); 
		Session session = sessionFactory.getCurrentSession();
		session.beginTransaction();
		
		session.save(acc);
		session.getTransaction().commit();
		
	}
 
	@Test
	/**
	 * 加乐观锁
	 */
	public void testPessimisticLock(){
		 
		Session session1 = sessionFactory.openSession();
		Session session2 = sessionFactory.openSession();
		 
		

		session1.beginTransaction(); 
	    Accmount a1 = (Accmount)session1.load(Accmount.class, 1);  
	    
	    session2.beginTransaction();
	    Accmount a2 = (Accmount)session2.load(Accmount.class, 1);
	    
	    
	    a1.setBalance(a1.getBalance()+10);
	    
	    
	    a2.setBalance(a2.getBalance()+10);
	    
	    session1.getTransaction().commit(); 
	    System.out.println(a1.getVersion());
	    
	    session2.getTransaction().commit();
	    System.out.println(a2.getVersion());
	    
	    session1.close();
	    session2.close();
		
	}
}
