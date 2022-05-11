package com.jghan.myhome.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity //db연동을 위한 모델클래스임을 명시
@Data
public class Board {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //Autoincrement 설정, IDENTITY가 많이 사용
    private Long id;

    @NotNull
    @Size(min=2, max=30, message = "제목은 2자이상 30자 이하입니다.")
    private String title;
    private String content;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @JsonIgnore
    private User user;

    //onetomany, manytoone 관계에서 보통 many쪽  에서 소유하는쪽을 @JoinColumn을 써서 적어준다
    // many 쪽에서 fk를 가지고 있다. (여기선 user_id)
//    @JoinColumn(name = "user_id", referencedColumnName = "id") //referencedColumnName는 생랴각능

}
