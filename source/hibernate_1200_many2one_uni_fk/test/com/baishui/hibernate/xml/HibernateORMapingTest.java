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
    public void testCRUD(){
    	
    	com.baishui.hibernate.annotation.Group group = new Group();
    	group.setTitle("1");
    	
    	com.baishui.hibernate.annotation.User user = new User();
    	user.setGroup(group);
    	user.setUserName("xr");
    	
    	Session session = sf.getCurrentSession();
    	
    	session.beginTransaction();
    	session.save(user);
    	session.getTransaction().commit();
    	
    }
	
	@AfterClass
	public static void afterClass(){ 
		try {
			sf.close();  //关闭连接工厂
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public static void main(String[] args) {
		SchemaExport se = new SchemaExport(new AnnotationConfiguration().configure());
		se.create(false, false);
	}
	 
	
	
}
