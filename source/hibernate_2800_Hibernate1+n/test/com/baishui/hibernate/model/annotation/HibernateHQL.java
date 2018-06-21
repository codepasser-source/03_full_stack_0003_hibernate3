package com.baishui.hibernate.model.annotation;

 
import java.util.Date;
import java.util.List;
 
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.annotations.BatchSize;
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
	 * 1+N 问题  注 Criteria 自动解决问题
	 * ManyToOne 中 FetchType 默认 为 EAGER 
	 * 取Topic 是 默认 关联查询 Category 对应的对象
	 * 
	 * 第一种解决方法： FetchType设置为LAZY   --test_Nquery1
	 * 第二种解决方法： join fetch            --test_Nquery2
	 * 第三种解决方法： 在Category使用@BatchSize(size=5)  --test_Nquery3 执行效率高
	 */
	public void test_Nquery1(){ 
		Session session= sessionFactory.getCurrentSession();
		session.beginTransaction();
		
		/*Criteria q = session.createCriteria(Topic.class);*/
		
		String hql = "from Topic t";		
		Query q = session.createQuery(hql); 
		for (Object o: q.list()) { 
			Topic t = (Topic)o;
			 System.out.println(t.getTitle());
		}
		session.getTransaction().commit(); 
	}
	
	@Test
	/** 
	 * 1+N 问题  注 Criteria 自动解决问题
	 * ManyToOne 中 FetchType 默认 为 EAGER 
	 * 取Topic 是 默认 关联查询 Category 对应的对象
	 * 
	 * 第一种解决方法： FetchType设置为LAZY   --test_Nquery1
	 * 第二种解决方法： join fetch            --test_Nquery2
	 * 第三种解决方法： 在Category使用@BatchSize(size=5)  --test_Nquery3 执行效率高
	 */
	public void test_Nquery2(){ 
		Session session= sessionFactory.getCurrentSession();
		session.beginTransaction();
		
		/*Criteria q = session.createCriteria(Topic.class); //默认 就是外联接查询  
*/		
		String hql = "from Topic t left join fetch t.category c";	//注意left join fetch 语法	
		Query q = session.createQuery(hql); 
		List<Topic> topics =( List<Topic>)q.list();
		
		for (Topic t :topics) { 
			 System.out.println(t.getTitle()+"--"+t.getCategory().getPlate());
		}
		session.getTransaction().commit(); 
	}
	
	@Test
	/** 
	 * 1+N 问题  注 Criteria 自动解决问题
	 * ManyToOne 中 FetchType 默认 为 EAGER 
	 * 取Topic 是 默认 关联查询 Category 对应的对象
	 * 
	 * 第一种解决方法： FetchType设置为LAZY   --test_Nquery1
	 * 第二种解决方法： join fetch            --test_Nquery2
	 * 第三种解决方法： 在Category使用@BatchSize(size=5)  --test_Nquery3 执行效率高
	 */
	public void test_Nquery3(){ 
		Session session= sessionFactory.getCurrentSession();
		session.beginTransaction();
		
		String hql = "from Topic t";		
		Query q = session.createQuery(hql); 
		
		for (Object o: q.list()) { 
			Topic t = (Topic)o;
			 System.out.println(t.getTitle()+"--"+t.getCategory().getPlate()); //用到就发 in 5个数据 考虑性能时用到
			 //注意  FetchType 在 EAGER 和 LAZY 下两种不同情况下 的 执 顺序
			 //EAGER 时每次执行 5 一次执行完输出 
			//LAZY   时每次执行 5 分两次执行分别输出
		}
		session.getTransaction().commit(); 
	}
	
}
