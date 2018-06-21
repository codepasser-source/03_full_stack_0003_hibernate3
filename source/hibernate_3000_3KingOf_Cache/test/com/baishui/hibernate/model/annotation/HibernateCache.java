package com.baishui.hibernate.model.annotation;

 
import java.util.Date;
import java.util.List;
 
import org.hibernate.Query;
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
	public void testSaveData(){
		
		Session session = sessionFactory.getCurrentSession();
		session.beginTransaction();
		
		for (int i = 1; i <= 10; i++) {
			Category c = new Category();
			c.setPlate("category"+i);
			Topic t = new Topic();
			t.setTitle("topic"+i);
			t.setCreateTime(new Date());
			
			t.setCategory(c);
			c.getTopics().add(t); 
			session.save(c);
		} 
		session.getTransaction().commit();
		
	}
	
	@Test  
	/**
	 * one session
	 * 利用一级缓存
	 */
	public void testCache1(){ 
		Session session= sessionFactory.getCurrentSession();
		session.beginTransaction(); 
		Category c1=  (Category)session.load(Category.class, 1);
	    System.out.println(c1.getPlate());
	    Category c2=  (Category)session.load(Category.class, 1);
	    System.out.println(c2.getPlate());
		session.getTransaction().commit(); 
	}
	
	@Test  
	/**
	 * two session
	 * 利用二级缓存
	 */
	public void testCache2(){ 
		try {
			Session session= sessionFactory.getCurrentSession();
			session.beginTransaction(); 
			Category c1=  (Category)session.load(Category.class, 1);
		    System.out.println(c1.getPlate()); 
			session.getTransaction().commit(); 
			
			Session session2 = sessionFactory.getCurrentSession();
			session2.beginTransaction(); 
			Category c2 = (Category) session2.load(Category.class, 1);
			System.out.println(c2.getPlate());
			session2.getTransaction().commit(); 
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}
	
	
	@Test  
	/**
	 * 一个session  或 两个 session
	 * 利用查询缓存
	 */
	public void testQueryCache(){ 
		try {
			Session session= sessionFactory.getCurrentSession();
			session.beginTransaction();  
			String hql ="from Category c";
			Query query = session.createQuery(hql);
			query.setCacheable(true); //设置使用 二级缓存 否则发出两条查询语句
			List<Category>  categories =( List<Category>)query.list();
			
			for (Category c : categories) {
				 System.out.println(c.getPlate()); 
			} 
	       List<Category>  categories1 =( List<Category>)query.list(); 
			for (Category c : categories1) {
				 System.out.println(c.getPlate()); 
			}
			session.getTransaction().commit(); 
			
			Session session2= sessionFactory.getCurrentSession();
			session2.beginTransaction();  
			 
			Query query1 = session2.createQuery(hql);
			query1.setCacheable(true); //设置使用 二级缓存 否则发出两条查询语句
		    categories =( List<Category>)query1.list();
			
			for (Category c : categories) {
				 System.out.println(c.getPlate()); 
			} 
	       
			session2.getTransaction().commit(); 
			
			 
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}
	
}
