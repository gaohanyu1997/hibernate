package com.ghy.test;

import com.ghy.Utils.HibernateUtils;
import com.ghy.entity.Customer;
import com.ghy.entity.LinkMan;
import com.ghy.manytomany.User;
import com.ghy.manytomany.Role;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.junit.Test;
import sun.awt.image.ImageWatched;

import java.util.List;

public class HibernateOneToMany {
    //演示一对多级联保存
    @Test
    public void testAddDemo1(){
        SessionFactory sessionFactory= null;
        Session session = null;
        Transaction tx = null;
        try{
            sessionFactory = HibernateUtils.getSessionFactory();
            session = sessionFactory.openSession();
            tx = session.beginTransaction();

            //1 根据id查询lucy联系人，根据id查询百度的客户
            Customer baidu = (Customer) session.get(Customer.class,1);
            LinkMan lucy = (LinkMan) session.get(LinkMan.class,2);
            //2 设置持久态对象值
            //把联系人放到客户里面
            baidu.getSetlinkMan().add(lucy);
            //把客户放到联系人里面
            lucy.setCustomer(baidu);

            tx.commit();    //提交事务
        }catch (Exception e){
            e.printStackTrace();
            //回滚事务
            tx.rollback();
        }finally {
            session.close();
        }
    }
    //演示维护第三张表操作
    @Test
    public void testTable(){
        SessionFactory sessionFactory= null;
        Session session = null;
        Transaction tx = null;
        try{
            sessionFactory = HibernateUtils.getSessionFactory();
            session = sessionFactory.openSession();
            tx = session.beginTransaction();

            //1 让某个用户没有某个角色
            User user = (User) session.get(User.class, 2);
            Role role = (Role) session.get(Role.class,3);
            //2 从用户里面把角色去掉
            user.getSetRole().remove(role);

            /*//2 把角色放到用户的set集合里面
            lucy.getSetRole().add(role);*/

            tx.commit();    //提交事务
        }catch (Exception e){
            e.printStackTrace();
            //回滚事务
            tx.rollback();
        }finally {
            session.close();
        }
    }
}
