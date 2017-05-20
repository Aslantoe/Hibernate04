package cn.jack.utils;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtils {

	static Configuration cfg = null;
	static SessionFactory sessionFactory = null;
	//类加载，只执行一次
	static{
	   cfg = new Configuration();
	   cfg.configure();
	   
	   sessionFactory = cfg.buildSessionFactory();
	   
 	}
	
	//提供返回与本地线程绑定的session方法
	public static Session getSessionObject(){
		return sessionFactory.getCurrentSession();
	
	}
	
	//返回一个SessionFactory对象供其他类使用
	public static SessionFactory getSessionFactory(){
		return sessionFactory;
	}
	
	public static void main(String[] args) {
		
	}
}
