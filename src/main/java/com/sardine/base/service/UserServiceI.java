package com.sardine.base.service;

import com.sardine.base.entity.User;

/**
 * 用户服务层接口，对用户模块进行逻辑处理
 * Created by weijian on 2014/10/30.
 */

public interface UserServiceI {
    public User findByUsername(String username);
    public boolean updateUser(User user);
    public boolean deleteUser(User user);
    public boolean test(String test);
}
