package com.ghy.test;

import com.ghy.Utils.HibernateUtils;
import com.ghy.entity.Customer;
import com.ghy.entity.LinkMan;
import org.hibernate.*;
import org.hibernate.criterion.DetachedCriteria;
import org.junit.Test;

import java.util.List;
import java.util.Set;

public class HibernateManyTable {
    //演示hql内连接查询
    @Test
    public void testQbc2(){
        SessionFactory sessionFactory= null;
        Session session = null;
        Transaction tx = null;
        try{
            sessionFactory = HibernateUtils.getSessionFactory();
            session = sessionFactory.openSession();
            tx = session.beginTransaction();

            //演示hql内连接查询
            //1 创建query对象
            /*Query query = session.createQuery("from Customer c inner join c.setlinkMan");
            List list = query.list();*/

            //演示hql迫切内连接查询
            //1 创建query对象
            /*Query query = session.createQuery("from Customer c inner join fetch c.setlinkMan");
            List list = query.list();*/

            //演示hql迫切左外连接查询
            //1 创建query对象
            Query query = session.createQuery("from Customer c left outer join fetch c.setlinkMan");
            List list = query.list();


            tx.commit();    //提交事务
        }catch (Exception e){
            e.printStackTrace();
            tx.rollback();
        }finally {
            session.close();
            sessionFactory.close();
        }
    }

    //演示hql检索策略
    @Test
    public void testQbc3(){
        SessionFactory sessionFactory= null;
        Session session = null;
        Transaction tx = null;
        try{
            sessionFactory = HibernateUtils.getSessionFactory();
            session = sessionFactory.openSession();
            tx = session.beginTransaction();

            //根据cid=1客户
            //执行get方法之后，是否发送sql语句
            //调用get方法马上发送sql语句查询数据库
            /*Customer customer = (Customer) session.get(Customer.class,1);
            System.out.println(customer.getCid()+"::"+customer.getCustName());*/

            //1 调用load方法之后,不会马上发送sql语句
            // (1) 返回对象里面只要id值
            //2 得到对象里面不是id的其他值时候才会发送语句
            /*Customer customer = (Customer) session.load(Customer.class,2);
            System.out.println(customer.getCid());
            System.out.println(customer.getCustName());*/

            //根据cid=1客户,在查询这个客户里面所有联系人
            Customer customer = (Customer) session.get(Customer.class,1);
            //在查询这个客户里面所有联系人
            //直接得到客户里面联系人的set集合
            //得到set集合，没有发送语句
            Set<LinkMan> linkMan = customer.getSetlinkMan();
            //得到set集合,发送语句
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
