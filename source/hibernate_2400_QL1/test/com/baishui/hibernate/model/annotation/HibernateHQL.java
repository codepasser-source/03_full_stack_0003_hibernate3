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
     * 查询所有
     */
	public void testHQL_01(){
		
		Session session= sessionFactory.getCurrentSession();
		session.beginTransaction();
		String hql= "from Category c"; //ejb ql
		Query query = session.createQuery(hql); 
		List<Category> categories=(List<Category>)query.list();
		for (Category c : categories) {
			System.out.println(c.getPlate());
		}
		
		/*第二种写法
		 * for (Object c : query.list()) {
			Category c1 = (Category)c;
			System.out.println(c1.getPlate());
		}
		*/
		session.getTransaction().commit();
		
		
	}
	
	@Test
	 /**
     * 排序
     */
	public void testHQL_02(){ 
		Session session= sessionFactory.getCurrentSession();
		session.beginTransaction();
		String hql= "from Category c where c.plate >'category2'"; //ejb ql
		Query query = session.createQuery(hql); 
		List<Category> categories=(List<Category>)query.list();
		for (Category c : categories) {
			System.out.println(c.getPlate());
		}
		session.getTransaction().commit();
	}
	@Test
	 /**
     * 排序
     */
	public void testHQL_03(){ 
		Session session= sessionFactory.getCurrentSession();
		session.beginTransaction();
		String hql= "from Category c order by c.plate desc"; //ejb ql
		Query query = session.createQuery(hql); 
		List<Category> categories=(List<Category>)query.list();
		for (Category c : categories) {
			System.out.println(c.getPlate());
		}
		session.getTransaction().commit();
	}
	@Test
    /**
     * 排序
     */
	public void testHQL_04(){ 
		Session session= sessionFactory.getCurrentSession();
		session.beginTransaction();
		String hql= "select distinct c from Category c order by c.plate desc"; //ejb ql 主键不重复
		Query query = session.createQuery(hql); 
		List<Category> categories=(List<Category>)query.list();
		for (Category c : categories) {
			System.out.println(c.getPlate());
		}
		session.getTransaction().commit();
	}
	@Test
	/**
	 * 占位符 :参数名 链式编程
	 */
	public void testHQL_05(){ 
		Session session= sessionFactory.getCurrentSession();
		session.beginTransaction();
		String hql= "from Category c where c.id>:min and c.id<:max"; //ejb ql 占位符
		Query query = session.createQuery(hql).setInteger("min", 2).setInteger("max", 5); 
		
		/* 多种写法
		 * query.setParameter("min",2);
		   query.setParameter("max",5);
		*/
		List<Category> categories=(List<Category>)query.list();
		for (Category c : categories) {
			System.out.println(c.getPlate());
		}
		session.getTransaction().commit();
	}
	
	@Test
	/**
	 * 占位符 ？ 链式编程
	 */
	public void testHQL_06(){ 
		Session session= sessionFactory.getCurrentSession();
		session.beginTransaction();
		String hql= "from Category c where c.plate = ?"; //ejb ql 占位符
		Query query = session.createQuery(hql);
		query.setParameter(0,"category2");                      //占位符从0开始
		
		/* 多种写法
		 * query.setParameter("min",2);
		   query.setParameter("max",5);
		*/
		List<Category> categories=(List<Category>)query.list();
		for (Category c : categories) {
			System.out.println(c.getPlate());
		}
		session.getTransaction().commit();
	}
	
	@Test
	/**
	 * 分页
	 */
	public void testHQL_07(){ 
		Session session= sessionFactory.getCurrentSession();
		session.beginTransaction();
		String hql= "from Category c"; //ejb ql 占位符
		Query query = session.createQuery(hql);
		
		query.setFirstResult(2);   //设置开始位置 
		query.setMaxResults(3);    //设置容器size
		List<Category> categories=(List<Category>)query.list();
		for (Category c : categories) {
			System.out.println(c.getPlate());
		}
		session.getTransaction().commit();
	}
	@Test
	/**
	 * 取字段值
	 */
	public void testHQL_09(){ 
		Session session= sessionFactory.getCurrentSession();
		session.beginTransaction();
		String hql= "select c.id,c.plate from Category c";  
		Query query = session.createQuery(hql); 
		
		for(Object o : query.list()){
			Object[] o1 = (Object[])o; 
			System.out.println(o1[0]+"--"+o1[1]);
		}
		
		/*第二种写法
		 * List<Object[]> categories=(List<Object[]>)query.list();//查询出数组
		for (Object[] o : categories) {
			System.out.println(o[0]+"--"+o[1]);
		}*/
		session.getTransaction().commit();
	}
	
	@Test
	/**
	 * 设置级联读取 fetch 属性Lazy 用到时再查,不会出现第二条查询
	 */
	public void testHQL_10(){ 
		Session session= sessionFactory.getCurrentSession();
		session.beginTransaction();
		String hql= "from Topic t where t.category.plate = ?";  //ejb ql 占位符
		Query query = session.createQuery(hql).setParameter(0, "category1"); 
		 
		List<Topic> topices=(List<Topic>)query.list();          
		for (Topic t: topices) {
			System.out.println(t.getId()+"--"+t.getTitle()); //
			//System.out.print(t.getCategory().getPlate());
			System.out.println();
		}
		session.getTransaction().commit();
	}
	
	@Test
	/**
	 * 设置级联读取 fetch 属性Eager 直接出现第二条查询
	 */
	public void testHQL_10_1(){ 
		Session session= sessionFactory.getCurrentSession();
		session.beginTransaction();
		String hql= "from Topic t where t.category.plate = ?";  //ejb ql 占位符
		Query query = session.createQuery(hql).setParameter(0, "category1"); 
		 
		List<Topic> topices=(List<Topic>)query.list();          
		for (Topic t: topices) {
			System.out.println(t.getId()+"--"+t.getTitle()); 
			System.out.print(t.getCategory().getPlate());  //读取Category表数据，也会发第二条sql
			System.out.println();
		}
		session.getTransaction().commit();
	}
	

	@Test
	/**
	 * 多级导航 msg -- topic -- category
	 * 注意 fetch 属性 
	 */
	public void testHQL_11(){ 
		Session session= sessionFactory.getCurrentSession();
		session.beginTransaction();
		String hql= "from Msg m where m.topic.category.id = ?";  //ejb ql 占位符
		Query query = session.createQuery(hql).setParameter(0, 1); 
		 
		List<Msg> msgs=(List<Msg>)query.list();          
		for (Msg m: msgs) {
			
			System.out.println(m.getId()+"--"+m.getCont());  
			System.out.println(m.getTopic().getTitle());
		    System.out.println(m.getTopic().getCategory().getPlate()); 
			System.out.println();
		}
		session.getTransaction().commit();
	}
	
	@Test
	/**
	 *  VO Value Object
	 *   Dto data transfer object
	 */
	public void testHQL_12(){ 
		Session session= sessionFactory.getCurrentSession();
		session.beginTransaction();
		String hql= "Select new com.baishui.hibernate.model.annotation.MsgInfo(m.id,m.cont,m.topic.title,m.topic.category.plate) " +
				     "from Msg m";  //ejb ql 占位符
		Query query = session.createQuery(hql);
		 
		List<MsgInfo> msgs=(List<MsgInfo>)query.list();          
		for (MsgInfo m: msgs) {
			
			System.out.println(m.getId()+"--"+m.getCont()+"--"+m.getTopicName()+"--"+m.getTopicName());  
			 
			System.out.println();
		}
		session.getTransaction().commit();
		 
	}
	
	@Test
	/** 
	 *  Join  同一个模块下的 所有主题
	 *  为什么不能直接写Category 名，而必须写 t.category?
	 *  因为有可能存在多个成员变量（同一个类中），需要指明那一个成员变量的连接条件来做连接
	 */
	public void testHQL_13(){ 
		Session session= sessionFactory.getCurrentSession();
		session.beginTransaction();
		String hql= "Select t.title,c.plate " +
				     "from Topic t join t.category c";
		Query query = session.createQuery(hql);
		 
		List<Object []> msgs=(List<Object []>)query.list();          
		for (Object [] o: msgs) { 
			 System.out.println(o[0]+"--"+o[1]);
		}
		session.getTransaction().commit(); 
	}
	
	@Test
	/** 
	 *  学习使用 uniqueResult 
	 */
	public void testHQL_14(){ 
		Session session= sessionFactory.getCurrentSession();
		session.beginTransaction();
		String hql= "from Msg m where m = ?";  //对象相等  
		Query query = session.createQuery(hql); 
		
		Msg m = new Msg();
		m.setId(1); 
		query.setParameter(0,m);             //把m设置进去
		
		Msg m1 = (Msg)query.uniqueResult();  //返回唯一的结果,不必list循环了
		 System.out.println(m1.getId()+"--"+m1.getCont());
		 
		 
	/*	for (Object o: query.list()) { 
			Msg m1 = (Msg)o;
			 System.out.println(m1.getId()+"--"+m1.getCont());
		} 
		session.getTransaction().commit(); */
	}
	
	@Test
	/** 
	 *  学习使用 uniqueResult 
	 */
	public void testHQL_15(){ 
		Session session= sessionFactory.getCurrentSession();
		session.beginTransaction();
		String hql= "select count(*) from Msg m";    
		Query query = session.createQuery(hql); 
	 
		Long m1 = (Long)query.uniqueResult();  //返回唯一的结果,不必list循环了
	    System.out.println(m1); 
	}
	
	@Test
	/** 
	 *  学习使用 uniqueResult 
	 */
	public void testHQL_16(){ 
		Session session= sessionFactory.getCurrentSession();
		session.beginTransaction();
		String hql= "select max(m.id),min(m.id),avg(m.id) from Msg m";    
		Query query = session.createQuery(hql); 
	 
		Object[] m1 = (Object[])query.uniqueResult();  //返回唯一的结果,不必list循环了
	    System.out.println(m1[0]+"-"+m1[1]+"--"+m1[2]); 
	}
	

	@Test
	/** 
	 *  学习使用 between and 
	 */
	public void testHQL_17(){ 
		Session session= sessionFactory.getCurrentSession();
		session.beginTransaction();
		String hql= "from Msg m where m.id between 3 and 5";    
		Query query = session.createQuery(hql);
		 
		           
		for (Object o:  query.list()) { 
			Msg m = (Msg)o;
			 System.out.println(m.getId()+"--"+m.getCont());
		}
		session.getTransaction().commit(); 
	}
	
	@Test
	/** 
	 *  学习使用 in()
	 */
	public void testHQL_18(){ 
		Session session= sessionFactory.getCurrentSession();
		session.beginTransaction();
		String hql= "from Msg m where m.id in(2,6,8)";    
		Query query = session.createQuery(hql);
		 
		           
		for (Object o:  query.list()) { 
			Msg m = (Msg)o;
			 System.out.println(m.getId()+"--"+m.getCont());
		}
		session.getTransaction().commit(); 
	}
	
	@Test
	/** 
	 *  学习使用 is not null
	 */
	public void testHQL_19(){ 
		Session session= sessionFactory.getCurrentSession();
		session.beginTransaction();
		String hql= "from Msg m where m.cont is not null";    
		Query query = session.createQuery(hql);
		 
		           
		for (Object o:  query.list()) { 
			Msg m = (Msg)o;
			 System.out.println(m.getId()+"--"+m.getCont());
		}
		session.getTransaction().commit(); 
	}
}
