package cn.jack.hibernatetest;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.junit.Test;

import cn.jack.entity.Customer;
import cn.jack.utils.HibernateUtils;

public class HIbernateQBC {

	
	//离线查询
	@Test
	public void testOffline(){
		Session session = null;
		Transaction tx = null;
		
		try {
			session = HibernateUtils.getSessionObject();
			tx = session.beginTransaction();
			//不用Session
		    DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Customer.class);
		    Criteria criteria =  detachedCriteria.getExecutableCriteria(session);
		     
		    List<Customer> list = criteria.list();
		    
		    for (Customer customer : list) {
				System.out.println(customer);
			}
		    
			tx.commit();
		} catch (Exception e) {
			tx.rollback();
		}

	}
	
	
	//统计查询
	@Test
	public void testTongji(){
		Session session = null;
		Transaction tx = null;
		
		try {
			session = HibernateUtils.getSessionObject();
			tx = session.beginTransaction();
			
			//1.创建Criteria对象
		    Criteria criteria = session.createCriteria(Customer.class);
			//2.使用方法
		   criteria.setProjection(Projections.rowCount());//统计行
		    
		   Object obj = criteria.uniqueResult();
		   
		   Long lobj = (Long)obj;
		   
		   int count = lobj.intValue();
		   
		   System.out.println(count);
		   
			tx.commit();
		} catch (Exception e) {
			tx.rollback();
		}

	}
	
	
	
	//分页查询
	@Test
	public void testPaging(){
		Session session = null;
		Transaction tx = null;
		
		try {
			session = HibernateUtils.getSessionObject();
			tx = session.beginTransaction();
			
			//1.创建Criteria对象
		    Criteria criteria = session.createCriteria(Customer.class);
			//2.使用方法
		    
		    //开始位置的计算公式
		    // (当前页数-1)*每页记录数
		    
		    criteria.setFirstResult(0);
		    criteria.setMaxResults(3);
		    
		    List<Customer> list = criteria.list();
		    
		    for (Customer customer : list) {
				System.out.println(customer);
			}
		    
			tx.commit();
		} catch (Exception e) {
			tx.rollback();
		}

	}
	
	
	
	//排序查询
	@Test
	public void testOrder(){
		Session session = null;
		Transaction tx = null;
		
		try {
			session = HibernateUtils.getSessionObject();
			tx = session.beginTransaction();
			
			//1.创建Criteria对象
		    Criteria criteria = session.createCriteria(Customer.class);
			//2.使用方法
		    criteria.addOrder(Order.desc("cid"));
		    
		    List<Customer> list = criteria.list();
		    
		    for (Customer customer : list) {
				System.out.println(customer);
			}
		    
			tx.commit();
		} catch (Exception e) {
			tx.rollback();
		}

	}
	
	
	//条件查询
	@Test
	public void testCri(){
		Session session = null;
		Transaction tx = null;
		
		try {
			session = HibernateUtils.getSessionObject();
			tx = session.beginTransaction();
			
			//1.创建Criteria对象
		    Criteria criteria = session.createCriteria(Customer.class);
			//用add方法，设置条件值
		    
		    //criteria.add(Restrictions.eq("cid", 12));
		    //criteria.add(Restrictions.eq("custName", "Alibaba"));
		     
		    criteria.add(Restrictions.like("custName", "%a%"));
		    List<Customer> list =  criteria.list();
		    
		    for (Customer customer : list) {
				System.out.println(customer);
			}
		    
			tx.commit();
		} catch (Exception e) {
			tx.rollback();
		}

	}
	
	
	//查询所有
	@Test
	public void testAll(){
		Session session = null;
		Transaction tx = null;
		
		try {
			session = HibernateUtils.getSessionObject();
			tx = session.beginTransaction();
			
			//1.创建Criteria对象
		    Criteria criteria = session.createCriteria(Customer.class);
			//2.使用方法
		    List<Customer> list =  criteria.list();
		    
		    for (Customer customer : list) {
				System.out.println(customer);
			}
		    
			tx.commit();
		} catch (Exception e) {
			tx.rollback();
		}

	}
	
}
