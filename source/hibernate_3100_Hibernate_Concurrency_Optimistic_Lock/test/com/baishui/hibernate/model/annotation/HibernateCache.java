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
	 * 不加锁的同时 会出现 重复读取 提交之后 刷掉其他线程的操作
	 */
	public void testOperation1(){
		
		 
		Session session = sessionFactory.getCurrentSession();
		session.beginTransaction();
		
		Accmount acc =(Accmount) session.load(Accmount.class, 1);
		//do some caculation
		acc.setBalance(acc.getBalance()+10);//提交之后 可能 刷掉 其他线程的 运算
		
		session.getTransaction().commit();
		
	}
	@Test
	/**
	 * 加悲观锁
	 */
	public void testPessimisticLock(){
		
		 
		Session session = sessionFactory.getCurrentSession();
		session.beginTransaction(); 
		Accmount acc =(Accmount) session.load(Accmount.class, 1 ,LockMode.UPGRADE);//加锁
		//do some caculation
		acc.setBalance(acc.getBalance()+10);
		
		session.getTransaction().commit();
		
	}
}
