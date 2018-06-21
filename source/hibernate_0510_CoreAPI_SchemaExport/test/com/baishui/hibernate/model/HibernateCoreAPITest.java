package com.baishui.hibernate.model;
 
 
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.tool.hbm2ddl.SchemaExport;
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
	public void testSchemaExport(){    
		   
		   SchemaExport se = new SchemaExport(new  AnnotationConfiguration().configure());
		   se.create(false, false);
	}
	
	
	@AfterClass
	public static void afterClass(){ 
		sf.close();  //关闭连接工厂
	}
	
	
	
	
}
