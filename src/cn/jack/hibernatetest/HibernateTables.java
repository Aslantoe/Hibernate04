package cn.jack.hibernatetest;

import java.util.Arrays;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.query.Query;
import org.junit.Test;

import cn.jack.entity.Customer;
import cn.jack.utils.HibernateUtils;

public class HibernateTables {
	
	
	//左外连接、迫切左外连接
	@Test
	public void testLeft(){
		Session session = null;
		Transaction tx = null;
		
		try {
			session = HibernateUtils.getSessionObject();
			tx = session.beginTransaction();
			

			Query query1 = session.createQuery("from Customer c left outer join c.setLinkMan");
			
			Query query2 = session.createQuery("from Customer c left outer join fetch c.setLinkMan");
			
			List list2 = query2.list();
			
			//返回对象
			for (Object object : list2) {
				System.out.println(object);
			}
			
			
			//返回数组
			/*List<Object[]> list1 =  query1.list();
		   
		    for (Object[] objects : list1) {
				System.out.println(Arrays.toString(objects));
			}*/
			
			tx.commit();
		} catch (Exception e) {
			tx.rollback();
		}

	}
	
    //迫切内连接、内连接
	@Test
	public void testInner(){
		Session session = null;
		Transaction tx = null;
		
		try {
			session = HibernateUtils.getSessionObject();
			tx = session.beginTransaction();
			

			Query query1 = session.createQuery("from Customer c inner join c.setLinkMan");
			
			Query query2 = session.createQuery("from Customer c inner join fetch c.setLinkMan");
			
			List list2 = query2.list();
			
			//返回对象
			for (Object object : list2) {
				System.out.println(object);
			}
			
			
			//返回数组
			/*List<Object[]> list1 =  query1.list();
		   
		    for (Object[] objects : list1) {
				System.out.println(Arrays.toString(objects));
			}*/
			
			tx.commit();
		} catch (Exception e) {
			tx.rollback();
		}

	}
}













