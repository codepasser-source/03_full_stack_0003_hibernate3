package com.baishui.hibernate.model.annotation;

 
import java.util.Date;
import java.util.List;
 

import org.hibernate.Query;
import org.hibernate.SQLQuery;
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
	 *  学习使用Topic  OneToMany Msg is empty
	 */
	public void testHQL_20(){ 
		Session session= sessionFactory.getCurrentSession();
		session.beginTransaction();
		String hql= "from Topic t where t.msgs is empty";    
		Query query = session.createQuery(hql);
		  
		for (Object o:  query.list()) { 
			Topic t = (Topic)o;
			 System.out.println(t.getTitle()+"-"+t.getCategory().getPlate());
		}
		session.getTransaction().commit(); 
	}
	
	@Test
	/** 
	 *  学习使用like
	 */
	public void testHQL_21(){ 
		Session session= sessionFactory.getCurrentSession();
		session.beginTransaction();
		String hql= "from Topic t where t.title like '%5'";    
		Query query = session.createQuery(hql);
		  
		for (Object o:  query.list()) { 
			Topic t = (Topic)o;
			 System.out.println(t.getTitle()+"-"+t.getCategory().getPlate());
		}
		session.getTransaction().commit(); 
	}
	@Test
	/** 
	 *  学习使用like
	 */
	public void testHQL_22(){ 
		Session session= sessionFactory.getCurrentSession();
		session.beginTransaction();
		String hql= "from Topic t where t.title like '_____5'";   // 五个下划线 
		Query query = session.createQuery(hql);
		  
		for (Object o:  query.list()) { 
			Topic t = (Topic)o;
			 System.out.println(t.getTitle()+"-"+t.getCategory().getPlate());
		}
		session.getTransaction().commit(); 
	}
	
	@Test
	/** 
	 *  学习使用HQL 中的函数
	 */
	public void testHQL_23(){ 
		Session session= sessionFactory.getCurrentSession();
		session.beginTransaction();
		String hql= "select lower(t.title)," +
				    "upper(t.title)," +
				    "trim(t.title)," +
				    "concat(t.title,'***')," +
				    "length(t.title)" +
				    "from Topic t";   
		Query query = session.createQuery(hql);
		 List<Object[]> topics =(List<Object[]>) query.list();
		for (Object[] o: topics) {  
			 System.out.println(o[0]+"--"+o[1]+"--"+o[2]+"--"+o[3]);
		}
		session.getTransaction().commit(); 
	}
	@Test
	/** 
	 *  学习使用HQL 中的函数
	 */
	public void testHQL_24(){ 
		Session session= sessionFactory.getCurrentSession();
		session.beginTransaction();
		String hql= "select abs(t.id)," +
				    "sqrt(t.id)," + 
				    "mod(t.id,2)" +
				    "from Topic t";   
		Query query = session.createQuery(hql);
		 List<Object[]> topics =(List<Object[]>) query.list();
		for (Object[] o: topics) {  
			 System.out.println(o[0]+"--"+o[1]+"--"+o[2]);
		}
		session.getTransaction().commit(); 
	}
	
	@Test
	/** 
	 *  学习使用HQL 中的 数据库系统日期和时间
     */
	public void testHQL_25(){ 
		Session session= sessionFactory.getCurrentSession();
		session.beginTransaction();
		String hql= "select current_date," +   //数据库系统日期
				    "current_time," +          //数据库系统时间
				    "current_timestamp " +      //数据库系统日期和时间
				    " from Topic t";   
		Query query = session.createQuery(hql);
		
		for (Object o:  query.list()) {
			 Object []o1=(Object [])o;
			 System.out.println(o1[0]+"--"+o1[1]+"--"+o1[2]);
		}
		session.getTransaction().commit(); 
	}
	@Test
	/** 
	 *  日期时间的比较
     */
	public void testHQL_26(){ 
		Session session= sessionFactory.getCurrentSession();
		session.beginTransaction();
		String hql=  " from Topic t where createTime < :date";   
		Query query = session.createQuery(hql).setParameter("date", new Date());
		
				
		for (Object o:  query.list()) {
			Topic t=(Topic)o;
			 System.out.println(t.getTitle());
		}
		session.getTransaction().commit(); 
	}
	@Test
	public void testHQL_27() {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		Query q = session.createQuery("select t.title, count(*) from Topic t group by t.title") ;
		for(Object o : q.list()) {
			Object[] arr = (Object[])o;
			System.out.println(arr[0] + "|" + arr[1]);
		}
		session.getTransaction().commit();
		session.close();
		
	}
	
	@Test
	public void testHQL_28() {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		Query q = session.createQuery("select t.title, count(*) from Topic t group by t.title having count(*) >= 1") ;
		for(Object o : q.list()) {
			Object[] arr = (Object[])o;
			System.out.println(arr[0] + "|" + arr[1]);
		}
		session.getTransaction().commit();
		session.close();
		
	}
	
	@Test
	public void testHQL_29() {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		Query q = session.createQuery("from Topic t where t.id < (select avg(t.id) from Topic t)") ;
		for(Object o : q.list()) {
			Topic t = (Topic)o;
			System.out.println(t.getTitle());
		}
		session.getTransaction().commit();
		session.close();
		
	}
	
	@Test
	public void testHQL_30() {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		Query q = session.createQuery("from Topic t where t.id < ALL (select t.id from Topic t where mod(t.id, 2)= 0) ") ;
		for(Object o : q.list()) {
			Topic t = (Topic)o;
			System.out.println(t.getTitle());
		}
		session.getTransaction().commit();
		session.close();
		
	}
	
	//用in 可以实现exists的功能
	//但是exists执行效率高
	@Test
	public void testHQL_31() {
		Session session = sessionFactory.openSession();
		session.beginTransaction();// t.id not in (1)
		Query q = session.createQuery("from Topic t where not exists (select m.id from Msg m where m.topic.id=t.id)") ;
//		Query q = session.createQuery("from Topic t where exists (select m.id from Msg m where m.topic.id=t.id)") ;
		for(Object o : q.list()) {
			Topic t = (Topic)o;
			System.out.println(t.getTitle());
		}
		session.getTransaction().commit();
		session.close();
		
	}
	
	//update and delete
	//规范并没有说明是不是要更新persistent object，所以如果要使用，建议在单独的trasaction中执行 
	@Test
	public void testHQL_32() {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		Query q = session.createQuery("update Topic t set t.title = upper(t.title)") ;
		
		q.executeUpdate();
		q = session.createQuery("from Topic");
		for(Object o : q.list()) {
			Topic t = (Topic)o;
			System.out.println(t.getTitle());
		}
		session.createQuery("update Topic t set t.title = lower(t.title)")
			.executeUpdate();
		session.getTransaction().commit();
		session.close();
		
	}
	
	//不重要
	@Test
	public void testHQL_33() {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		Query q = session.getNamedQuery("topic.selectCertainTopic");
		q.setParameter("id", 5);
		Topic t = (Topic)q.uniqueResult();
		System.out.println(t.getTitle());
		session.getTransaction().commit();
		session.close();
		
	}
	
	//Native（了解）
	@Test
	public void testHQL_34() {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		SQLQuery q = session.createSQLQuery("select * from category limit 2,4").addEntity(Category.class);
		List<Category> categories = (List<Category>)q.list();
		for(Category c : categories) {
			System.out.println(c.getPlate());
		}
		session.getTransaction().commit();
		session.close();
		
	}
	
	@Test
	public void testHQL_35() {
		//尚未实现JPA命名的NativeSQL
		
	}
	
}
