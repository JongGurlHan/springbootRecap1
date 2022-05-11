package com.jghan.myhome.repository;

import com.jghan.myhome.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username); //컬럼이름에 일치하는 사용자 데이터를 가져온다.
}
