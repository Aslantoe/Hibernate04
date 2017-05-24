package cn.jack.hibernatetest;

import java.util.List;

import org.hibernate.query.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Test;

import cn.jack.entity.Customer;
import cn.jack.utils.HibernateUtils;

public class HibernateHQL {
    
	
	
	//�ۺϺ���
	@Test
	public void testHqlCount(){
	
		Session session = null;
		Transaction tx = null;
		
		try {
			session = HibernateUtils.getSessionObject();
			tx = session.beginTransaction();
			
			//1.����query����
			
			Query<Object> query = session.createQuery("select count(*) from Customer");
			
			//2.���÷����õ����
			//query�����з���,ֱ�ӷ��ض�����ʽ
			Object obj = query.uniqueResult();
			
			Long temp = (Long) obj;
			
			int count = temp.intValue();
			
			System.out.println("��¼��:"+count);
			
			tx.commit();
		} catch (Exception e) {
			tx.rollback();
		}

	} 
	
	
	
	//ͶӰ��ѯ
	@Test
	public void testHqlProjection(){
	
		Session session = null;
		Transaction tx = null;
		
		try {
			session = HibernateUtils.getSessionObject();
			tx = session.beginTransaction();
			
			//1.����query����
			//����Ҫ��ΪObject,��Ϊ��ѯ�����Ķ�����Customer��
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
	
	//��ҳ��ѯ
	@Test
	public void testHqlPaging(){
	
		Session session = null;
		Transaction tx = null;
		
		try {
			session = HibernateUtils.getSessionObject();
			tx = session.beginTransaction();
			
			//1.����query����,��ѯ����	
			Query<Customer> query = session.createQuery("from Customer");
			//2.���÷�ҳ����
			   //��ʼλ��
			query.setFirstResult(0);
			   //ÿҳ��¼��
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
	
	//�����ѯ
	@Test
	public void testHqlOrder(){
	
		Session session = null;
		Transaction tx = null;
		
		try {
			session = HibernateUtils.getSessionObject();
			tx = session.beginTransaction();
			
			//1.����query����
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
