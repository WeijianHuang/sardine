package com.fishing.sardine.dao;

import com.fishing.sardine.entity.User;
import com.fishing.sardine.service.UserServiceI;
import org.hibernate.Query;
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
    @Autowired
    public UserServiceI userServiceI;

    public Session getSession(){
        return sessionFactory.getCurrentSession();
    }

    @Override
    public User findByUsername(String username) {
        Query query = getSession().createQuery("from User where username = ?");
        query.setString(0, username);
        return (User) query.uniqueResult();
    }

    @Override
    public boolean updateUser(User user) {
        return false;
    }

    @Override
    public boolean deleteUser(User user) {
        return false;
    }
}
