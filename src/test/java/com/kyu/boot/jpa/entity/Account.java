package com.kyu.boot.jpa.entity;

import com.kyu.boot.jpa.converter.attribute.LocalDateAttributeConverter;
import com.kyu.boot.jpa.converter.attribute.LocalDateTimeAttributeConverter;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Project : test_project
 * @Date : 2017-05-10
 * @Author : nklee
 * @Description :
 */
@Entity
public class Account {

    @Id
    private int accountId;

    private String accountName;

    @Convert(converter = LocalDateAttributeConverter.class)
    @Column(columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDate changed;

    @Convert(converter = LocalDateTimeAttributeConverter.class)
    @Column(columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime changed1;

    @Temporal(TemporalType.TIMESTAMP) // 추가 안하면 오류 발생
    @Column(columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Date changed2;

    // default fetch type = LAZY
    @OneToMany(mappedBy = "account", fetch = FetchType.LAZY)
    private List<AccountRole> accountRoles = new ArrayList<>();

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public LocalDate getChanged() {
        return changed;
    }

    public void setChanged(LocalDate changed) {
        this.changed = changed;
    }

    public LocalDateTime getChanged1() {
        return changed1;
    }

    public void setChanged1(LocalDateTime changed1) {
        this.changed1 = changed1;
    }

    public Date getChanged2() {
        return changed2;
    }

    public void setChanged2(Date changed2) {
        this.changed2 = changed2;
    }

    public List<AccountRole> getAccountRoles() {
        return accountRoles;
    }

    public void setAccountRoles(List<AccountRole> accountRoles) {
        this.accountRoles = accountRoles;
    }

    @Override
    public String toString() {
        return "Account{" +
                "accountId=" + accountId +
                ", accountName='" + accountName + '\'' +
                ", changed=" + changed +
                ", changed1=" + changed1 +
                ", changed2=" + changed2 +

                // 이 부분을 제거해 줘야 한다. 하지 않으면 stack overflow 에 빠진다.
//                ", accountRoles=" + accountRoles +
                '}';
    }
}
