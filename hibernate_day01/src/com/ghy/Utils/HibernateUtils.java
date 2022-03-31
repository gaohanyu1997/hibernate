package com.ghy.Utils;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtils {
    static Configuration cfg = null;
    static SessionFactory sessionFactory = null;
    static{
        cfg = new Configuration().configure();
        sessionFactory = cfg.buildSessionFactory();
    }

    //提供方法返回本地线程的session的方法
    public static Session getSessionObject(){
        return sessionFactory.getCurrentSession();
    }

    public static SessionFactory getSessionFactory(){
        return sessionFactory;
    }

    public static void main(String[] args) {
    }
}
