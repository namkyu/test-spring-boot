package com.kyu.boot.jpa.entity;

import javax.persistence.*;

/**
 * @Project : test_project
 * @Date : 2017-05-10
 * @Author : nklee
 * @Description :
 */
@Entity
public class AccountRole {

    @Id
    @GeneratedValue
    private int id;

    @ManyToOne
    @JoinColumn(name = "ACCOUNT_ID")
    private Account account;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ROLE_ID")
    private Role role;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "AccountRole{" +
                "id=" + id +
//                ", account=" + account +
                ", role=" + role +
                '}';
    }
}
