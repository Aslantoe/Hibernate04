package cn.jack.hibernatetest;

import java.util.List;

import org.hibernate.query.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Test;

import cn.jack.entity.Customer;
import cn.jack.utils.HibernateUtils;

public class HibernateHQL {
    
    //������ѯ--ģ����ѯ
	@Test
	public void testHqlCriVague(){
	
		Session session = null;
		Transaction tx = null;
		
		try {
			session = HibernateUtils.getSessionObject();
			tx = session.beginTransaction();
			
			//1.����query����
			Query<Customer> query = session.createQuery("from Customer c where c.custName like ?");
			
			//2.��������ֵ
			//setParameter(arg0, arg1);
			//��һ������:?λ��,��0��ʼ
			//�ڶ�������:�������ֵ
			
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

	
	//������ѯ
	@Test
	public void testHqlCri(){
	
		Session session = null;
		Transaction tx = null;
		
		try {
			session = HibernateUtils.getSessionObject();
			tx = session.beginTransaction();
			
			//1.����query����
			Query<Customer> query = session.createQuery("from Customer c where c.cid = ? and c.custName = ?");
			
			//2.��������ֵ
			//setParameter(arg0, arg1);
			//��һ������:?λ��,��0��ʼ
			//�ڶ�������:�������ֵ
			query.setParameter(0, 4);
			
			query.setParameter(1, "��Ѷ");
			
			
			List<Customer> list = query.list();
			
			for (Customer customer : list) {
				System.out.println(customer.toString());
			}
			
			tx.commit();
		} catch (Exception e) {
			tx.rollback();
		}

	} 
		
	
	
	//HQL��ѯ����
	@Test
	public void testHqlAll(){
	
		Session session = null;
		Transaction tx = null;
		
		try {
			session = HibernateUtils.getSessionObject();
			tx = session.beginTransaction();
			
			//1.����query����
			Query<Customer> query = session.createQuery("from Customer");
			//2.���÷����õ����
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
