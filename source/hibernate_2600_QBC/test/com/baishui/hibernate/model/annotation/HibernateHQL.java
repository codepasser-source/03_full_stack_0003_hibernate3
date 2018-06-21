package com.baishui.hibernate.model.annotation;

 
import java.util.Date;
 
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.criterion.Restrictions;
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
	 *  Query By Criteria
	 */
	public void testQBC(){ 
		Session session= sessionFactory.getCurrentSession();
		session.beginTransaction();
		
		//标准约束
	    Criteria c = session.createCriteria(Topic.class) //相当于HQL from Topic t 
//	    .add(Restrictions.gt("id", 1))  //greater than id
//	    .add(Restrictions.lt("id", 2))  //little than id
//		.add(Restrictions.like("title", "%2")) 
	    .add(Restrictions.between("id", 4, 6))
	    .createCriteria("category")
	    .add(Restrictions.between("id", 0, 2))
	    ;
		//DetachedCriteria
		for (Object o: c.list()) { 
			Topic t = (Topic)o;
			 System.out.println(t.getTitle()+"-"+t.getCategory().getPlate());
		}
		session.getTransaction().commit(); 
	}
	
}
