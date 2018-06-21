package com.baishui.hibernate.model.annotation;

 
import java.util.Date;
 
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.Restrictions;
import org.hibernate.tool.hbm2ddl.SchemaExport;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.mysql.jdbc.EscapeTokenizer;


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
		new SchemaExport(new AnnotationConfiguration().configure()).create(false, false);
	} 
	public static void main(String[] args) {
		beforeClass();
	}
	
	@Test
	public void testSaveData(){
		
		Session session = sessionFactory.getCurrentSession();
		session.beginTransaction();
		
		for (int i = 1; i <= 10; i++) {
			Category cate = new Category();
			cate.setPlate("category"+i);
			session.save(cate);
		}
		
		for (int i = 1; i <= 10; i++) {
			Category cate = new Category();
			cate.setId(1);
			Topic topic = new Topic();
			topic.setTitle("topic"+i);
			topic.setCreateTime(new Date());
			topic.setCategory(cate);
			session.save(topic);
		}
		
		for (int i = 1; i <= 10; i++) { 
			Topic topic = new Topic();
			topic.setId(1); 
			Msg msg = new Msg();
			msg.setCont("cont"+i);
			msg.setTopic(topic);  
			session.save(msg);
		}
		session.getTransaction().commit();
		
	}
	
	@Test
	/** 
	 * Query By Criteria Query By Example
	 */
	public void testQBE(){ 
		Session session= sessionFactory.getCurrentSession();
		session.beginTransaction();
		
		Topic tExample = new Topic();
		tExample.setTitle("topic1");
		
		Example e = Example.create(tExample)
		.ignoreCase().enableLike();
		Criteria c = session.createCriteria(Topic.class)
		/*.add(Restrictions.gt("id", 2))
		.add(Restrictions.lt("id", 8))*/
		.add(e);
		;
		
		 
		for (Object o: c.list()) { 
			Topic t = (Topic)o;
			 System.out.println(t.getTitle()+"-"+t.getCategory().getPlate());
		}
		session.getTransaction().commit(); 
	}
	
}
