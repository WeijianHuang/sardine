package com.fishing.sardine.service;

import com.fishing.sardine.entity.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by weijian on 2014/10/29.
 */
@Service
public class UserDaoImpl implements UserDaoI {

    @Autowired
    public SessionFactory sessionFactory;

    public Session getSession(){
        return sessionFactory.getCurrentSession();
    }
    @Override
    public void addUser(User user) {
        try {
            System.out.println("1111111111111111111111111111");
            System.out.println( getSession().save(user));
            System.out.println(user.getName()+"111");
            System.out.println("执行save");
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    @Override
    public void update(User user) {
        getSession().update(user);
        System.out.println("执行更新");
    }

    @Override
    public void delete(User user) {
        getSession().delete(user);
        System.out.println("执行删除");
    }
}
