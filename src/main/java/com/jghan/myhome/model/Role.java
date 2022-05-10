package com.jghan.myhome.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity //db연동을 위한 모델클래스임을 명시
@Data
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //Autoincrement 설정, IDENTITY가 많이 사용
    private Long id;

    private String name; //권한명

    @ManyToMany(mappedBy = "roles") //mappedBy: User클래스에 있는 컬럼명이 된다.
    private List<User> users;


}
