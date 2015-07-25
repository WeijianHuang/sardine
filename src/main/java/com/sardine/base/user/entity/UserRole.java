package com.sardine.base.user.entity;

import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;

/**
 * Created by weijian on 2014/10/30.
 */
@Entity
@Table(name = "user_role")
public class UserRole implements GrantedAuthority {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column( nullable = false, unique = true, updatable = false)
    private String authority;
    @Column(name = "memo")
    private String memo;

    /**
     * 权限属性
     *
     * @return 权限属性
     */
    @Override
    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }

    /**
     * 权限备注
     *
     * @return 备注
     */
    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }
}

