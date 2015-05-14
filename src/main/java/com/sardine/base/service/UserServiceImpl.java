package com.sardine.base.service;

import com.sardine.base.dao.UserDaoI;
import com.sardine.base.entity.User;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;


/**
 * Created by weijian on 2014/10/30.
 */
@Service
public class UserServiceImpl implements UserServiceI {

     Logger logger = Logger.getLogger("service");
    @Autowired
    public UserDaoI userDaoI;

    @Override
    public User findByUsername(String username) {
        return userDaoI.findByUsername(username);
    }

    @Override
    public boolean deleteUser(User user) {
        return false;
    }

    @Override

    public boolean updateUser(User user) {

        return true;
    }
    @Override
    @PreAuthorize("hasRole('ROLE_USER')")
    public boolean test(String test){
        logger.info("-------------------------------------------------------------------------");
        System.out.println("没办法。进入了");
        return false;
    }
}
