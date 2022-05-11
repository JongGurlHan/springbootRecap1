package com.jghan.myhome.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity //db연동을 위한 모델클래스임을 명시
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //Autoincrement 설정, IDENTITY가 많이 사용
    private Long id;

    private String username;
    private String password;
    private Boolean enabled;

    //user에 해당하는 권한이 알아서 조회돼서, roles에 담긴다.
    @ManyToMany
    @JoinTable(
            name = "user_role",
            joinColumns = @JoinColumn(name="user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private List<Role> roles = new ArrayList<>();

//    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    @OneToMany(mappedBy = "user", fetch = FetchType)
    private List<Board> boards = new ArrayList<>();

    //UserApiController에서 board정보 저장할때 cascade설정이 없으면 user부분만 저장된다.
    //CascadeType.DELETE: 사용자 삭제시 cascade의해서 보드 정보가 삭제되고, 사용자도 삭제된다.

    //orphanRemoval: 기본값은 false, 부모가 없는 데이터는 다 지운다.

    //fetch = FetchType. : 사용자 조회시 board클래스에 대한 데이터를 같이가져올지, 필요할때 나중에 가져올지를 설정한다.
    //Eager: 사용자 정보 가져올때 같이 가져온다. OneToOne, ManyToOne 일때 기본값(하나의 값만 온다는게 보장되기 때문) 
    //LAZY: board를 사용할때 그때 데이터를 조회(가져온다) OneToMany, ManyToMany 일때 기본값(여러개의 데이터, 혹은 필요없는데이터 까지 조회되기 때문) 
}
