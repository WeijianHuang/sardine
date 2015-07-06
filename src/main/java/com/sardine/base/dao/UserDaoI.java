package com.sardine.base.dao;

import com.sardine.base.entity.User;

/**
 * 用户dao层接口 主要与数据库 的增删改查交互
 * Created by weijian on 2014/10/29.
 */
public interface UserDaoI {

    public User findByUsername(String username);
    public boolean updateUser(User user);
    public boolean deleteUser(User user);
}
