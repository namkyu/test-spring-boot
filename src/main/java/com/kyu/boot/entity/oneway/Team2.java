package com.kyu.boot.entity.oneway;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @Project : test_project
 * @Date : 2017-05-12
 * @Author : nklee
 * @Description :
 */
@Entity
public class Team2 {

    @Id
    @Column(name = "TEAM_ID")
    private int id;

    private String name;

    @OneToMany(mappedBy = "team", cascade = CascadeType.ALL)
    private List<Member2> member = new ArrayList<>();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Member2> getMember() {
        return member;
    }

    public void addMember(Member2 member) {
        this.member.add(member); // 이런 처리를 위해서 new ArrayList<>(); 를 미리 해주는 것 같음
    }

    @Override
    public String toString() {
        return "Team2{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
