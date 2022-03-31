package com.ghy.test;

import com.ghy.Utils.HibernateUtils;
import com.ghy.entity.User;
import org.hibernate.*;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class HibernateQueryData {
    @Test
    public void testSqlQuery(){
        SessionFactory sessionFactory= null;
        Session session = null;
        Transaction tx = null;
        try{
            sessionFactory = HibernateUtils.getSessionFactory();
            session = sessionFactory.openSession();
            tx = session.beginTransaction();
            //1 创建对象
            //参数普通sql语句
            SQLQuery sqlQuery = session.createSQLQuery("select * from t_user");

            //返回的list中每部分是对象形式
            sqlQuery.addEntity(User.class);

            //调用sqlQuery里面的方法
            List<User> list = sqlQuery.list();

            for (User user:list){
                System.out.println(user);
            }
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
