package cn.jack.hibernatetest;

import java.util.List;

import org.hibernate.query.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Test;

import cn.jack.entity.Customer;
import cn.jack.utils.HibernateUtils;

public class HibernateHQL {
    
    //条件查询--模糊查询
	@Test
	public void testHqlCriVague(){
	
		Session session = null;
		Transaction tx = null;
		
		try {
			session = HibernateUtils.getSessionObject();
			tx = session.beginTransaction();
			
			//1.创建query对象
			Query<Customer> query = session.createQuery("from Customer c where c.custName like ?");
			
			//2.设置条件值
			//setParameter(arg0, arg1);
			//第一个参数:?位置,从0开始
			//第二个参数:具体参数值
			
			//
			query.setParameter(0, "%a%");
			
			List<Customer> list = query.list();
			
			for (Customer customer : list) {
				System.out.println(customer.toString());
			}
			
			tx.commit();
		} catch (Exception e) {
			tx.rollback();
		}

	} 

	
	//条件查询
	@Test
	public void testHqlCri(){
	
		Session session = null;
		Transaction tx = null;
		
		try {
			session = HibernateUtils.getSessionObject();
			tx = session.beginTransaction();
			
			//1.创建query对象
			Query<Customer> query = session.createQuery("from Customer c where c.cid = ? and c.custName = ?");
			
			//2.设置条件值
			//setParameter(arg0, arg1);
			//第一个参数:?位置,从0开始
			//第二个参数:具体参数值
			query.setParameter(0, 4);
			
			query.setParameter(1, "腾讯");
			
			
			List<Customer> list = query.list();
			
			for (Customer customer : list) {
				System.out.println(customer.toString());
			}
			
			tx.commit();
		} catch (Exception e) {
			tx.rollback();
		}

	} 
		
	
	
	//HQL查询所有
	@Test
	public void testHqlAll(){
	
		Session session = null;
		Transaction tx = null;
		
		try {
			session = HibernateUtils.getSessionObject();
			tx = session.beginTransaction();
			
			//1.创建query对象
			Query<Customer> query = session.createQuery("from Customer");
			//2.调用方法得到结果
			List<Customer> list = query.list();
			
			for (Customer customer : list) {
				System.out.println(customer.toString());
			}
			
			tx.commit();
		} catch (Exception e) {
			tx.rollback();
		}

	} 
		
	
}
