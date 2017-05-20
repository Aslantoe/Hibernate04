package cn.jack.hibernatetest;

import java.util.Iterator;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.junit.Test;

import cn.jack.entity.Customer;
import cn.jack.entity.LinkMan;
import cn.jack.utils.HibernateUtils;

public class HibernateDemo {

	
	
	
	//��ʾ���󵼺���ѯ
	@Test
	public void testNavigate(){
		SessionFactory sessionFactory = null;
		Session session = null;
		Transaction tx = null;
		
		try {
		    sessionFactory = HibernateUtils.getSessionFactory();
		    session = sessionFactory.openSession();
		    tx  = session.beginTransaction();
		    
		    //����cid=3�ͻ����ڲ�ѯ����ͻ��������е���ϵ��
		    Customer customer = session.get(Customer.class, 4);
		    System.out.println("----------"+customer.getCustName()+"---------------"); 
		    
		    //��ѯ����ͻ�����������ϵ��
		    Set<LinkMan> linkMans = customer.getSetLinkMan();
		    
		    Iterator<LinkMan> it = linkMans.iterator();
		    
		    while (it.hasNext()) {
				LinkMan linkMan = (LinkMan) it.next();
				System.out.println(linkMan.toString());
			}
		    
		    System.out.println("���ȣ�"+linkMans.size());
		    
		    
		    tx.commit();
		} catch (Exception e) {
			tx.rollback();
		}finally{
			sessionFactory.close();
			session.close();
		}
		
	}
	
}
