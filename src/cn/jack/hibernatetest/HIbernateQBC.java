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

	
	//���߲�ѯ
	@Test
	public void testOffline(){
		Session session = null;
		Transaction tx = null;
		
		try {
			session = HibernateUtils.getSessionObject();
			tx = session.beginTransaction();
			//����Session
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
	
	
	//ͳ�Ʋ�ѯ
	@Test
	public void testTongji(){
		Session session = null;
		Transaction tx = null;
		
		try {
			session = HibernateUtils.getSessionObject();
			tx = session.beginTransaction();
			
			//1.����Criteria����
		    Criteria criteria = session.createCriteria(Customer.class);
			//2.ʹ�÷���
		   criteria.setProjection(Projections.rowCount());//ͳ����
		    
		   Object obj = criteria.uniqueResult();
		   
		   Long lobj = (Long)obj;
		   
		   int count = lobj.intValue();
		   
		   System.out.println(count);
		   
			tx.commit();
		} catch (Exception e) {
			tx.rollback();
		}

	}
	
	
	
	//��ҳ��ѯ
	@Test
	public void testPaging(){
		Session session = null;
		Transaction tx = null;
		
		try {
			session = HibernateUtils.getSessionObject();
			tx = session.beginTransaction();
			
			//1.����Criteria����
		    Criteria criteria = session.createCriteria(Customer.class);
			//2.ʹ�÷���
		    
		    //��ʼλ�õļ��㹫ʽ
		    // (��ǰҳ��-1)*ÿҳ��¼��
		    
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
	
	
	
	//�����ѯ
	@Test
	public void testOrder(){
		Session session = null;
		Transaction tx = null;
		
		try {
			session = HibernateUtils.getSessionObject();
			tx = session.beginTransaction();
			
			//1.����Criteria����
		    Criteria criteria = session.createCriteria(Customer.class);
			//2.ʹ�÷���
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
	
	
	//������ѯ
	@Test
	public void testCri(){
		Session session = null;
		Transaction tx = null;
		
		try {
			session = HibernateUtils.getSessionObject();
			tx = session.beginTransaction();
			
			//1.����Criteria����
		    Criteria criteria = session.createCriteria(Customer.class);
			//��add��������������ֵ
		    
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
	
	
	//��ѯ����
	@Test
	public void testAll(){
		Session session = null;
		Transaction tx = null;
		
		try {
			session = HibernateUtils.getSessionObject();
			tx = session.beginTransaction();
			
			//1.����Criteria����
		    Criteria criteria = session.createCriteria(Customer.class);
			//2.ʹ�÷���
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
