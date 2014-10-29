package com.fishing.sardine.service;

import com.fishing.sardine.entity.User;

/**
 * Created by weijian on 2014/10/29.
 */
public interface UserDaoI {

    public void addUser(User user);
    public void update(User user);
    public void delete(User user);
}
