package cn.jack.hibernatetest;

import java.util.List;

import org.hibernate.query.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Test;

import cn.jack.entity.Customer;
import cn.jack.utils.HibernateUtils;

public class HibernateHQL {
    
	
	
	//聚合函数
	@Test
	public void testHqlCount(){
	
		Session session = null;
		Transaction tx = null;
		
		try {
			session = HibernateUtils.getSessionObject();
			tx = session.beginTransaction();
			
			//1.创建query对象
			
			Query<Object> query = session.createQuery("select count(*) from Customer");
			
			//2.调用方法得到结果
			//query里面有方法,直接返回对象形式
			Object obj = query.uniqueResult();
			
			Long temp = (Long) obj;
			
			int count = temp.intValue();
			
			System.out.println("记录数:"+count);
			
			tx.commit();
		} catch (Exception e) {
			tx.rollback();
		}

	} 
	
	
	
	//投影查询
	@Test
	public void testHqlProjection(){
	
		Session session = null;
		Transaction tx = null;
		
		try {
			session = HibernateUtils.getSessionObject();
			tx = session.beginTransaction();
			
			//1.创建query对象
			//泛型要改为Object,因为查询出来的对象不是Customer了
			Query<Object> query = session.createQuery("select custName from Customer");
			
			List<Object> list = query.list();
			
			for (Object object : list) {
				System.out.println(object);
			}
			
			tx.commit();
		} catch (Exception e) {
			tx.rollback();
		}

	} 
	
	//分页查询
	@Test
	public void testHqlPaging(){
	
		Session session = null;
		Transaction tx = null;
		
		try {
			session = HibernateUtils.getSessionObject();
			tx = session.beginTransaction();
			
			//1.创建query对象,查询所有	
			Query<Customer> query = session.createQuery("from Customer");
			//2.设置分页参数
			   //开始位置
			query.setFirstResult(0);
			   //每页记录数
			query.setMaxResults(3);
			
			List<Customer> list = query.list();
			
			for (Customer customer : list) {
				System.out.println(customer.toString());
			}
			
			tx.commit();
		} catch (Exception e) {
			tx.rollback();
		}

	} 
	
	//排序查询
	@Test
	public void testHqlOrder(){
	
		Session session = null;
		Transaction tx = null;
		
		try {
			session = HibernateUtils.getSessionObject();
			tx = session.beginTransaction();
			
			//1.创建query对象
			String hql = "";
			
			Query<Customer> query = session.createQuery("from Customer order by CID desc");
			
			
			List<Customer> list = query.list();
			
			for (Customer customer : list) {
				System.out.println(customer.toString());
			}
			
			tx.commit();
		} catch (Exception e) {
			tx.rollback();
		}

	} 
	
	
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
