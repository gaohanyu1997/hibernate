package com.ghy.test;

import com.ghy.Utils.HibernateUtils;
import com.ghy.entity.Customer;
import com.ghy.entity.LinkMan;
import com.ghy.manytomany.Role;
import com.ghy.manytomany.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.junit.Test;

public class HibernateManyToMany {
    //演示多对多级联保存
    @Test
    public void testSave(){
        SessionFactory sessionFactory= null;
        Session session = null;
        Transaction tx = null;
        try{
            sessionFactory = HibernateUtils.getSessionFactory();
            session = sessionFactory.openSession();
            tx = session.beginTransaction();

            //添加两个用户,为每个用户添加两个角色
            //1 创建对象
            User user1 = new User();
            user1.setUser_name("lucy");
            user1.setUser_password("123");

            User user2 = new User();
            user2.setUser_name("mary");
            user2.setUser_password("456");

            Role r1 = new Role();
            r1.setRole_name("总经理");
            r1.setRole_memo("总经理");

            Role r2 = new Role();
            r2.setRole_name("秘书");
            r2.setRole_memo("秘书");

            Role r3 = new Role();
            r3.setRole_name("保安");
            r3.setRole_memo("保安");
            //2 建立关系,把角色放到用户里面
            //user1 --- r1/r2
            user1.getSetRole().add(r1);
            user1.getSetRole().add(r2);

            //user2 --- r2/r3
            user2.getSetRole().add(r2);
            user2.getSetRole().add(r3);

            //3 保存用户
            session.save(user1);
            session.save(user2);

            tx.commit();    //提交事务
        }catch (Exception e){
            e.printStackTrace();
            //回滚事务
            tx.rollback();
        }finally {
            session.close();
        }
    }

    //演示多对多级联删除
    @Test
    public void testDelete(){
        SessionFactory sessionFactory= null;
        Session session = null;
        Transaction tx = null;
        try{
            sessionFactory = HibernateUtils.getSessionFactory();
            session = sessionFactory.openSession();
            tx = session.beginTransaction();

            User user = (User) session.get(User.class,1);
            session.delete(user);

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
