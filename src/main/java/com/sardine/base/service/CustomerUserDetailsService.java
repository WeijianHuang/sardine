package com.sardine.base.service;

import java.util.*;

import com.sardine.base.entity.User;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * Created by weijian on 2014/10/30.
 */
@Service
public class CustomerUserDetailsService implements UserDetailsService {
    protected static Logger logger = Logger.getLogger("service");//log4j，不用解释了吧。。

    @Autowired
    private UserServiceI userService;

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException, DataAccessException {
        logger.info("进入查询用户");
        System.out.println(username);
        System.out.println(userService);

        User user = userService.findByUsername(username);


        logger.info("查询完毕");
        return pottingUserAuthorities(user);
    }

    public User pottingUserAuthorities(User user) {
        String username = user.getUsername();
        String password = user.getPassword();
        boolean enable = user.isEnabled();
        boolean accountNonExpired = user.isAccountNonExpired();
        boolean credentialsNonExpired = user.isCredentialsNonExpired();
        boolean accountNonLocked = user.isAccountNonLocked();
        return new User(username, password, authoritiesAssembler(user.getAuthorities()), accountNonExpired, accountNonLocked, credentialsNonExpired, enable, user.getId());
    }

    /**
     * 用户权限组装器
     *
     * @return 权限集合
     */
    public Collection<GrantedAuthority> authoritiesAssembler(Collection<GrantedAuthority> grantedAuthoritys) {
        Set<GrantedAuthority> authorities = new HashSet<>();
        for (GrantedAuthority role : grantedAuthoritys) {
            authorities.add(new SimpleGrantedAuthority(role.getAuthority()));
        }
        return authorities;
    }

}
