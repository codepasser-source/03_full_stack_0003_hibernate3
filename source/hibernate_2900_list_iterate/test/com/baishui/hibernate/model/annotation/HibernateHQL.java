package com.baishui.hibernate.model.annotation;

 
import java.util.Date;
import java.util.Iterator;
import java.util.List;
 
 
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
 import org.hibernate.cfg.AnnotationConfiguration;
 import org.hibernate.tool.hbm2ddl.SchemaExport;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

 

public class HibernateHQL {
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
	 * list 遍历两次时  
	 *  第一次从数据库查询
	 *  第二次也从数据库查询
	 */
	public void test_QueryList(){ 
		Session session= sessionFactory.getCurrentSession();
		session.beginTransaction();
		 
		String hql = "from Topic t";		
		Query q = session.createQuery(hql); 
		List<Topic> topics = q.list(); 
		for (Topic t: topics) { 
			 
			 System.out.println(t.getTitle());
		}
		
		List<Topic> topics2 = q.list(); 
		for (Topic t: topics2) { 
			 
			 System.out.println(t.getTitle());
		}
		session.getTransaction().commit(); 
	}
	
	
	@Test 
	/**
	 * Iterator 遍历两次时  
	 *  第一次从数据库查询
	 *  第二次从缓存里查询
	 */
	public void test_QueryIterator(){ 
		Session session= sessionFactory.getCurrentSession();
		session.beginTransaction();
		 
		String hql = "from Topic t";		
		Query q = session.createQuery(hql); 
		
		Iterator<Topic> topics = q.iterate();
		
		while(topics.hasNext()) { 
			 Topic t = topics.next();
			 System.out.println(t.getTitle());
			 
		}
		
         Iterator<Topic> topics2 = q.iterate();
		
		while(topics2.hasNext()) { 
			 Topic t = topics2.next();
			 System.out.println(t.getTitle());
			 
		}
		session.getTransaction().commit(); 
	}
	
}
