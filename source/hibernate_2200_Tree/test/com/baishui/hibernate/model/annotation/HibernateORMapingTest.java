package com.baishui.hibernate.model.annotation; 
 
 
 
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.tool.hbm2ddl.SchemaExport;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
 
 

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
	public void testSaveOrg(){
		Org o1= new Org();
		o1.setName("1");  
		
		Org o1_1 = new Org();
		o1_1.setName("1_1"); 
		Org o1_1_1 = new Org();
		o1_1_1.setName("1_1_1"); 
		Org o1_1_2 = new Org();
		o1_1_2.setName("1_1_2");
		
		Org o1_2 = new Org();
		o1_2.setName("1_2"); 
		Org o1_2_1 = new Org();
		o1_2_1.setName("1_2_1"); 
		Org o1_2_2 = new Org();
		o1_2_2.setName("1_2_2");
		
		
		o1.getChildren().add(o1_1);
		o1.getChildren().add(o1_2); 
		o1_1.getChildren().add(o1_1_1);
		o1_1.getChildren().add(o1_1_2); 
		o1_2.getChildren().add(o1_2_1);
		o1_2.getChildren().add(o1_2_2);
		
		o1_1_1.setParent(o1_1);
		o1_1_2.setParent(o1_1);
		o1_1.setParent(o1); 
		o1_2_1.setParent(o1_2);
		o1_2_2.setParent(o1_2);
		o1_2.setParent(o1);
		
		Session session = sf.getCurrentSession();
		session.beginTransaction();
		
		session.save(o1); 
		session.getTransaction().commit();
		
		
	}
	@Test
	public void testLoadOrg(){
		 
		
		Session session = sf.getCurrentSession();
		session.beginTransaction();
		
		Org o = (Org) session.load(Org.class,1);  
		print(o,0); 
		session.getTransaction().commit(); 
	}
	
	
	private void print(Org o,int level) { 
		String s = "";
		for (int i = 0; i < level; i++) {
			s = s+"--";
		}
		System.out.println(s+o.getName());
		for(Org o1 : o.getChildren()){ 
			this.print(o1,level+1);
		}
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
