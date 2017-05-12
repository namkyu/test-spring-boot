package com.kyu.boot.entity;


import com.kyu.boot.constants.RoleType;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;

/**
 * @Project : test_project
 * @Date : 2017-05-10
 * @Author : nklee
 * @Description :
 */
@Entity
public class Role {

    @Id
    private int roleId;

    @Enumerated(EnumType.STRING)
    private RoleType roleType;

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public RoleType getRoleType() {
        return roleType;
    }

    public void setRoleType(RoleType roleType) {
        this.roleType = roleType;
    }

    @Override
    public String toString() {
        return "Role{" +
                "roleId=" + roleId +
                ", roleType=" + roleType +
                '}';
    }
}

