package com.jghan.myhome.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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

}
