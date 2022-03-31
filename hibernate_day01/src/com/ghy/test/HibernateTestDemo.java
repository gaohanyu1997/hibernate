package com.ghy.test;

import com.ghy.Utils.HibernateUtils;
import com.ghy.entity.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.junit.Test;

import java.io.Serializable;

public class HibernateTestDemo {
    //验证一级缓存存在
    @Test
    public void testTx(){
        Session session = null;
        Transaction tx = null;
        try{
            //与本地线程绑定的session
            session = HibernateUtils.getSessionObject();
            tx = session.beginTransaction();    //开启事务
            //添加
            User user = new User();
            user.setUsername("小王2");
            user.setPassword("222");
            user.setAddress("china");
            session.save(user);

            tx.commit();    //提交事务
        }catch (Exception e){
            e.printStackTrace();
            //回滚事务
            tx.rollback();
        }finally {
            //session.close();
        }
    }
}
