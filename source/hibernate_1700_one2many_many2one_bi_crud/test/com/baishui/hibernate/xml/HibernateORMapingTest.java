package com.baishui.hibernate.xml; 
 
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.tool.hbm2ddl.SchemaExport;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.baishui.hibernate.annotation.Group;
import com.baishui.hibernate.annotation.User;
 

public class HibernateORMapingTest {

private static SessionFactory sf=null;
	
	@BeforeClass
	public static void beforeClass(){
		try {
			sf =  new AnnotationConfiguration().configure().buildSessionFactory(); //根据hibernate.cfg.xml创建Configuration
        } catch (Exception e) {
			e.printStackTrace();
		}
	} 
	
	
	@Test
	/**
	 * Annotation
	 * 测试级联存储
	 */
	public void testAnnotationSaveCURD(){   
		Group group = new Group();
      
		User user1 = new User();
		user1.setUserName("xr1");
		user1.setGroup(group);
		
		User user2 = new User();
		user2.setUserName("xr2"); 
	    user2.setGroup(group);
		
		User user3 = new User();
		user3.setUserName("xr3"); 
	    user3.setGroup(group);    
		
		group.setTitle("love"); 
		group.getUsers().add(user1); 
		group.getUsers().add(user2);
		group.getUsers().add(user3);
		Session session = sf.getCurrentSession(); 
		session.beginTransaction(); 
		
		session.save(group);             //单方面至存储 group ,自动存储 user
		//session.save(user);
		
		session.getTransaction().commit(); 
	}
	
	@Test
	/**
	 * Annotation
	 * 测试级联获取
	 */
	public void testAnnotationGetCURD(){   
		 
		Session session = sf.getCurrentSession(); 
		session.beginTransaction(); 
	     Group group = (Group)session.get(Group.class, 1);
	    
		//User user1  =(User) session.get(User.class, 1); 
		session.getTransaction().commit(); 
		
		// EAGER 时 可以在session外使用 级联关系，如果是LAZY 时 会出错
		 for(User user : group.getUsers()){
	    	 System.out.println(user.getId()+"--"+user.getUserName());
	     }
	}
	
	@Test
	/**
	 * Annotation
	 * 测试级联获取
	 */
	public void testAnnotationLoadCURD(){   
		 
		Session session = sf.getCurrentSession(); 
		session.beginTransaction(); 
		User user = (User)session.load(User.class, 1);  
		System.out.println(user.getGroup().getTitle()); //注意 LAZY 和 EAGER的区别 
		session.getTransaction().commit();  
	}
	
	@Test
	/**
	 * Annotation
	 * 测试级联获取
	 */
	public void testAnnotationUpdateCURD(){   
		 
		Session session = sf.getCurrentSession(); 
		session.beginTransaction(); 
		User user = (User)session.get(User.class, 5); 
		
        user.getGroup().setTitle("mylove");
		 
		session.getTransaction().commit();  
	}
	@Test
	/**
	 * Annotation
	 * 测试级联获取
	 */
	public void testAnnotationDeleteCURD(){   
		 
		Session session = sf.getCurrentSession(); 
		session.beginTransaction(); 
		Group group = (Group)session.get(Group.class,1);  
       
		session.delete(group);
		 
		session.getTransaction().commit();  
	}
	
	@Test
	/**
	 * XML
	 * 测试级联存储  
	 */
	public void testXmlSaveCURD(){   
		
		com.baishui.hibernate.xml.Group group = new com.baishui.hibernate.xml.Group();
		group.setTitle("love"); 

   
        com.baishui.hibernate.xml.User user = new com.baishui.hibernate.xml.User();
		user.setUserName("xr1");
	    user.setGroup(group);
	    
        group.getUsers().add(user);
		
		Session session = sf.getCurrentSession(); 
		session.beginTransaction();
		
		session.save(group); 
		//session.save(user);
		session.getTransaction().commit(); 
	}
	
	
	@Test
	/**
	 * XML
	 * 测试级联读取
	 */
     public void testXmlGetCURD(){   
		
		 
		Session session = sf.getCurrentSession(); 
		session.beginTransaction();
		
		/*com.baishui.hibernate.xml.User user = 
			(com.baishui.hibernate.xml.User)session.get(com.baishui.hibernate.xml.User.class, 1);*/
		com.baishui.hibernate.xml.Group group =
			(com.baishui.hibernate.xml.Group) session.get(com.baishui.hibernate.xml.Group.class, 1);
 
		session.getTransaction().commit(); 
	}
	
	
	
	public static void main(String[] args) {
		SchemaExport se = new SchemaExport(new AnnotationConfiguration().configure());
		se.create(false, false);
	}  
	
	@AfterClass
	public static void afterClass(){ 
		try {
			sf.close();  //关闭连接工厂
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
