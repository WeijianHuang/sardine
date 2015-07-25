package com.sardine.base.user.repository;

import com.sardine.base.user.entity.User;
import com.sardine.base.user.service.UserServiceI;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * 用户dao层实现类 主要与数据库 的增删改查交互
 * Created by weijian on 2014/10/29.
 */
@Repository
public class UserRepositoryImpl implements UserRepositoryI {

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
