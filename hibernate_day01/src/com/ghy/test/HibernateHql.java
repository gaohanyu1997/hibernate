package com.ghy.test;

import com.ghy.Utils.HibernateUtils;
import com.ghy.entity.Customer;
import com.ghy.entity.LinkMan;
import com.sun.javaws.IconUtil;
import org.hibernate.*;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.junit.Test;

import java.util.List;
import java.util.Set;

public class HibernateHql {
    //演示查询所有
    @Test
    public void testSelect1(){
        SessionFactory sessionFactory= null;
        Session session = null;
        Transaction tx = null;
        try{
            sessionFactory = HibernateUtils.getSessionFactory();
            session = sessionFactory.openSession();
            tx = session.beginTransaction();

            //1 创建query对象
            Query query = session.createQuery("from Customer");
            //2 调用方法得到结果
            List<Customer> list = query.list();
            for (Customer customer:list){
                System.out.println(customer.getCid()+"::"+customer.getCustName());
            }
            tx.commit();    //提交事务
        }catch (Exception e){
            e.printStackTrace();
            tx.rollback();
        }finally {
            session.close();
            sessionFactory.close();
        }
    }

    //演示条件查询
    @Test
    public void testSelect2(){
        SessionFactory sessionFactory= null;
        Session session = null;
        Transaction tx = null;
        try{
            sessionFactory = HibernateUtils.getSessionFactory();
            session = sessionFactory.openSession();
            tx = session.beginTransaction();

            /*//1 创建query对象
            // select * from t_customer where cid=? and custName = ?
           Query query = session.createQuery("from Customer where cid=? and custName = ?");
            //2 设置条件值
            //向？里面设置值
            //setParameter方法有两个参数
            // 第一个参数：int类型是？位置,？位置从0开始
            // 第二个参数：具体参数值
            //设置第一个？值
            query.setParameter(0,1);
            query.setParameter(1,"百度");
            //3 调用方法得到结果
            List<Customer> list =  query.list();
            for(Customer customer:list){
                System.out.println(customer.getCid()+"::"+customer.getCustName());
            }*/

            //1 创建query对象
            //select * from t_customer where custName like '%...%'
           /* Query query = session.createQuery("from Customer where custName like ?");
            //2 设置？的值
            // %浪
            query.setParameter(0,"%浪%");
            List<Customer> list =  query.list();
            for(Customer customer:list){
                System.out.println(customer.getCid()+"::"+customer.getCustName());
            }*/

           //1 创建query对象
            /*Query query = session.createQuery("from Customer order by cid desc");
            List<Customer> list =  query.list();
            for(Customer customer:list){
                System.out.println(customer.getCid()+"::"+customer.getCustName());
            }*/
            //分页 1 创建query对象
            // 写查询所有的语句
           /* Query query = session.createQuery("from Customer");
            //2 设置分页数据
            //2.1 设置开始位置
            query.setFirstResult(0);
            //2.2 设置每页显示的记录数
            query.setMaxResults(3);
            //3 调用方法得到结果
            List<Customer> list =  query.list();
            for(Customer customer:list){
                System.out.println(customer.getCid()+"::"+customer.getCustName());
            }*/

            //投影查询
            //1 创建query对象
            Query query = session.createQuery("select custName from Customer");
            //2 调用方法得到结果
            List<Object> list =  query.list();
            for(Object o:list){
                System.out.println(o);
            }

            tx.commit();    //提交事务
        }catch (Exception e){
            e.printStackTrace();
            tx.rollback();
        }finally {
            session.close();
            sessionFactory.close();
        }
    }

    //演示聚集函数使用
    @Test
    public void testSelect3(){
        SessionFactory sessionFactory= null;
        Session session = null;
        Transaction tx = null;
        try{
            sessionFactory = HibernateUtils.getSessionFactory();
            session = sessionFactory.openSession();
            tx = session.beginTransaction();

            //1 创建query对象
            Query query = session.createQuery("select count(*) from Customer");
            //2 调用方法得到结果
            //query 对象里面有方法，直接返回对象形式
            Object obj = query.uniqueResult();
            //返回int类型
            /*int count = (int)obj;*/
            //首先把object变成long类型，在变成int类型
            Long lobj = (long)obj;
            int count = lobj.intValue();
            System.out.println(count);

            tx.commit();    //提交事务
        }catch (Exception e){
            e.printStackTrace();
            tx.rollback();
        }finally {
            session.close();
            sessionFactory.close();
        }
    }

    //演示qbc查询
    @Test
    public void testQbc(){
        SessionFactory sessionFactory= null;
        Session session = null;
        Transaction tx = null;
        try{
            sessionFactory = HibernateUtils.getSessionFactory();
            session = sessionFactory.openSession();
            tx = session.beginTransaction();
            //演示查询所有
            /*//1 创建Criteria对象
            Criteria criteria = session.createCriteria(Customer.class);
            //2 调用方法得到结果
            List<Customer> list = criteria.list();
            for (Customer customer:list){
                System.out.println(customer.getCid()+"::"+customer.getCustName());
            }*/

            //演示条件查询
            //1 创建Criteria对象
            /*Criteria criteria = session.createCriteria(Customer.class);
            //2 使用Criteria对象里面里面的方法设置条件值
            //首先使用add方法,表示设置条件值
            //在add方法里面是使用类的方法实现条件设置
            // 模糊查询
            criteria.add(Restrictions.like("custName","%百%"));
            //3 调用方法得到结果
            List<Customer> list = criteria.list();
            for (Customer customer:list){
                System.out.println(customer.getCid()+"::"+customer.getCustName());
            }*/

            /*//演示排序查询
            //1 创建Criteria对象
            Criteria criteria = session.createCriteria(Customer.class);
            //2 设置对那个属性进行排序,设置排序规则
            criteria.addOrder(Order.desc("cid"));
            //3 调用方法得到结果
            List<Customer> list = criteria.list();
            for (Customer customer:list){
                System.out.println(customer.getCid()+"::"+customer.getCustName());
            }*/

            //演示分页查询
            //1 创建Criteria对象
            Criteria criteria = session.createCriteria(Customer.class);
            //2 设置分页数据
            // 2.1 设置开始位置
            // 2.2 每页显示记录数
            criteria.setFirstResult(0);
            criteria.setMaxResults(3);

            //3 调用方法得到结果
            List<Customer> list = criteria.list();
            for (Customer customer:list){
                System.out.println(customer.getCid()+"::"+customer.getCustName());
            }

            tx.commit();    //提交事务
        }catch (Exception e){
            e.printStackTrace();
            tx.rollback();
        }finally {
            session.close();
            sessionFactory.close();
        }
    }

    //演示qbc统计查询
    @Test
    public void testQbc2(){
        SessionFactory sessionFactory= null;
        Session session = null;
        Transaction tx = null;
        try{
            sessionFactory = HibernateUtils.getSessionFactory();
            session = sessionFactory.openSession();
            tx = session.beginTransaction();
            //演示统计查询
            //1 创建Criteria对象
            /*Criteria criteria = session.createCriteria(Customer.class);
            //2 设置操作
            criteria.setProjection(Projections.rowCount());

            //3 调用方法得到结果
            Object obj = criteria.uniqueResult();
            Long lobj = (Long)obj;
            int count = lobj.intValue();
            System.out.println(count);*/

            //演示离线查询
            //1 创建对象
            DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Customer.class);
            //2 最终执行时候才需要到session
            Criteria criteria =  detachedCriteria.getExecutableCriteria(session);

            List<Customer> list = criteria.list();
            for (Customer customer:list){
                System.out.println(customer.getCid()+"::"+customer.getCustName());
            }

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
