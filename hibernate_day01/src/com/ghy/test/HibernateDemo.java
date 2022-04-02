package com.ghy.test;

import com.ghy.Utils.HibernateUtils;
import com.ghy.entity.Customer;
import com.ghy.entity.LinkMan;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.junit.Test;

import java.util.Set;

public class HibernateDemo {
    //演示对象导航查询
    @Test
    public void testSelect1(){
        SessionFactory sessionFactory= null;
        Session session = null;
        Transaction tx = null;
        try{
            sessionFactory = HibernateUtils.getSessionFactory();
            session = sessionFactory.openSession();
            tx = session.beginTransaction();

            //根据cid=1客户,在查询这个客户里面所有联系人
            Customer customer = (Customer) session.get(Customer.class,1);
            //在查询这个客户里面所有联系人
            //直接得到客户里面联系人的set集合
            Set<LinkMan> linkMan = customer.getSetlinkMan();
            System.out.println(linkMan.size());

            tx.commit();    //提交事务
        }catch (Exception e){
            e.printStackTrace();
            tx.rollback();
        }finally {
            session.close();
            sessionFactory.close();
        }
    }
}
