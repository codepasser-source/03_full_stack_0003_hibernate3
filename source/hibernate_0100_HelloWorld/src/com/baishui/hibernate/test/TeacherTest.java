package com.baishui.hibernate.test;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.cfg.Configuration;  
import com.baishui.hibernate.model.Teacher;

public class TeacherTest {
   public static void main(String[] args) {
	 
	   Teacher tr = new Teacher();
	   tr.setId(2);
	   tr.setName("tr1");
	   tr.setTitle("english"); 
	   
	   Configuration cfg = new AnnotationConfiguration().configure(); //根据hibernate.cfg.xml创建Configuration
	   SessionFactory sf = cfg.buildSessionFactory(); 
	   Session session = sf.openSession();
	   
	   session.beginTransaction();        //开始事务 
	   session.save(tr);               //存储对象
	   session.getTransaction().commit(); //提交
	   
	   session.close();                   //关闭连接会话
	   sf.close();                        //关闭连接工厂
   }
}
