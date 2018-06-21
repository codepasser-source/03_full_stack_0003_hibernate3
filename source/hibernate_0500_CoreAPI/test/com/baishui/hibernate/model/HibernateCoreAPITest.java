package com.baishui.hibernate.model;

import java.util.Date;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;



public class HibernateCoreAPITest {

private static SessionFactory sf=null;
	
	@BeforeClass
	public static void beforeClass(){
		
		sf =  new AnnotationConfiguration().configure().buildSessionFactory(); //根据hibernate.cfg.xml创建Configuration
	}
	
	@Test
	public void testTeacherSave(){
		  
		
		Teacher tr = new Teacher();
		   
		   tr.setName("tr1");
		   tr.setTitle("english");
		   tr.setLove("xinran");
		   tr.setCreateTime(new Date());
		   tr.setZhicheng(ZhiCheng.A);
		   
		   
		   //Session session = sf.openSession(); 
		   Session session=sf.getCurrentSession();
		   
		   session.beginTransaction();        //开始事务 
		   session.save(tr);               //存储对象 
		   
		   Session session2=sf.getCurrentSession();
		   
		   System.out.println(session == session2);
		   
		   session.getTransaction().commit(); //提交 
		 
           Session session3=sf.getCurrentSession();
		   
		   System.out.println(session == session3); 
	}
	
	@Test
	public void testSave3State(){ 
		Teacher tr = new Teacher();
		   
		   tr.setName("tr1");
		   tr.setTitle("english");
		   tr.setLove("xinran");
		   tr.setCreateTime(new Date());
		   tr.setZhicheng(ZhiCheng.A);         //Transaction  
		   
		   
		   Session session=sf.getCurrentSession(); 
		   session.beginTransaction();         
		   session.save(tr);  
		   
		   System.out.println(tr.getId());    //Persistent
		   
		   session.getTransaction().commit(); //提交   
		   
		   System.out.println(tr.getId());     //Decathed
	}
	
	@Test
 	public void testDelete(){ 
		 
		   Teacher t = new Teacher();
		   t.setId(1);
		   
		   Session session=sf.getCurrentSession(); 
		   session.beginTransaction();    
		   session.delete(t);   
		   session.getTransaction().commit(); //提交    
		   
	}
	
	@Test
	public void testLoad(){  
		   Session session=sf.getCurrentSession(); 
		   session.beginTransaction();    
		   Teacher t =  (Teacher)session.load(Teacher.class,2);   
		   System.out.println(t.getName());      // 代理对象 当用到属性是 才发出sql  所以 在 session关闭之前
		   System.out.println(t.getClass());
		   session.getTransaction().commit(); //提交    
		   
		   
	}
	
	@Test
	public void testGet(){  
		   Session session=sf.getCurrentSession(); 
		   session.beginTransaction();    
		   Teacher t =  (Teacher)session.get(Teacher.class,2);   
		   session.getTransaction().commit(); //提交  
		   System.out.println(t.getClass());
		   System.out.println(t.getName());       // 真实 对象 
		   
	}
	
	@Test
	public void testUpdate(){  
		
		Teacher t =new Teacher();
		t.setId(2);
		t.setName("xr");
		t.setTitle("wodeai");
		t.setCreateTime(new Date());
		t.setZhicheng(ZhiCheng.B);
		t.setLove("cyy");
		
		   Session session=sf.getCurrentSession(); 
		   session.beginTransaction();    
		   session.update(t);  
		  
		   System.out.println(t.getClass());
		  session.getTransaction().commit();
		   
	}
	
	/**
	 * not one session
	 */
	@Test
	public void testUpdate1(){  
		
		 
		
		   Session session=sf.getCurrentSession(); 
		   session.beginTransaction();    
		   Teacher t= (Teacher)session.get(Teacher.class, 2); 
		    
		    t.setName("ss1");
		   session.getTransaction().commit(); 
		   
		    t.setTitle("ss2");
		   
		   Session session1 = sf.getCurrentSession();
		   session1.beginTransaction();
		   session1.update(t);
		   session1.getTransaction().commit(); 
		   
		   
	}
	
	
	/**
	 * xml  dynamic-update =true 
	 */
	@Test
	public void testUpdate2(){  
		
		  
		Student s = new Student();
		s.setName("xr");
		s.setAge(24);
		 
		
		   Session session=sf.getCurrentSession(); 
		   session.beginTransaction();     
		   session.save(s);
		   s.setName("wxr"); 
		   session.getTransaction().commit();  
		   
	}
	
	/**
	 * HQL  
	 */
	@Test
	public void testUpdate3(){   
		
		   Session session=sf.getCurrentSession(); 
		   session.beginTransaction();     
		   Query q = session.createQuery("update Student s set s.name='wangxinran' where s.id=1 ");
		   q.executeUpdate();
		   session.getTransaction().commit();  
		   
	}
	
	
	@Test
	public void testSaveOrUpdate(){   
		
		   Teacher tr = new Teacher();
		   
		   tr.setName("tr1");
		   tr.setTitle("english");
		   tr.setLove("xinran"); 
		   tr.setCreateTime(new Date());
		   tr.setZhicheng(ZhiCheng.A);        
		   
		   
		   Session session=sf.getCurrentSession(); 
		   session.beginTransaction();   
		   session.saveOrUpdate(tr);
		   
		   tr.setName("tr2"); 
		   session.getTransaction().commit(); //提交    
	}
	
	@Test
	public void testClear(){    
		   
		   Session session=sf.getCurrentSession(); 
		   session.beginTransaction();   
		   
		   Teacher t1 = (Teacher)session.load(Teacher.class, 1);
		   System.out.println(t1.getName());
		   
		   session.clear();       // 清理session缓存
		   
		  Teacher t2 = (Teacher)session.load(Teacher.class, 1);
		   System.out.println(t2.getName()); 
		   session.getTransaction().commit(); //提交    
	}
	
	@Test
	public void testFlush(){    
		   
		   Session session=sf.getCurrentSession(); 
		   session.beginTransaction();   
		   
		   Teacher t1 = (Teacher)session.load(Teacher.class, 3);
		   System.out.println(t1.getName());
		   
		   t1.setName("tttt");
		   
		   session.flush();
		   
		   t1.setName("tttttt");
		  
		   System.out.println(t1.getName()); 
		   session.getTransaction().commit(); //提交    
	}
	
	
	@AfterClass
	public static void afterClass(){ 
		sf.close();  //关闭连接工厂
	}
	
	
	
	
}
