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

	
	
	
	//演示对象导航查询
	@Test
	public void testNavigate(){
		SessionFactory sessionFactory = null;
		Session session = null;
		Transaction tx = null;
		
		try {
		    sessionFactory = HibernateUtils.getSessionFactory();
		    session = sessionFactory.openSession();
		    tx  = session.beginTransaction();
		    
		    //根据cid=3客户，在查询这个客户里面所有的联系人
		    Customer customer = session.get(Customer.class, 4);
		    System.out.println("----------"+customer.getCustName()+"---------------"); 
		    
		    //查询这个客户里面所有联系人
		    Set<LinkMan> linkMans = customer.getSetLinkMan();
		    
		    Iterator<LinkMan> it = linkMans.iterator();
		    
		    while (it.hasNext()) {
				LinkMan linkMan = (LinkMan) it.next();
				System.out.println(linkMan.toString());
			}
		    
		    System.out.println("长度："+linkMans.size());
		    
		    
		    tx.commit();
		} catch (Exception e) {
			tx.rollback();
		}finally{
			sessionFactory.close();
			session.close();
		}
		
	}
	
}
