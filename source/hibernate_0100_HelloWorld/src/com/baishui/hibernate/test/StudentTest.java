package com.baishui.hibernate.test;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration; 
import com.baishui.hibernate.model.Student;

public class StudentTest {
   public static void main(String[] args) {
	 
	   Student st1 = new Student();
	   st1.setId(2);
	   st1.setName("st2");
	   st1.setAge(9);
	   
	   Configuration cfg = new Configuration().configure(); //根据hibernate.cfg.xml创建Configuration
	   SessionFactory sf = cfg.buildSessionFactory(); 
	   Session session = sf.openSession(); 
	   
	   session.beginTransaction();        //开始事务 
	   session.save(st1);               //存储对象
	   session.getTransaction().commit(); //提交
	   
	   session.close();                   //关闭连接会话
	   sf.close();                        //关闭连接工厂
   }
}
