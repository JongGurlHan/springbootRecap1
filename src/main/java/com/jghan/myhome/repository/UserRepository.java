package com.jghan.myhome.repository;

import com.jghan.myhome.model.User;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {

    @EntityGraph(attributePaths = { "boards" }) //데이터 조회시 n+1 문제가 생겨서 성능상 문제가 발생할때 사용하면 1개의 조인으로 데이터를 조회해서 성능 향상 가능
    List<User> findAll();

    User findByUsername(String username); //컬럼이름에 일치하는 사용자 데이터를 가져온다.

    //커스텀 쿼리 - JPQL
    @Query("select u from User u where u.username like %?1%") // 앞뒤로 like검색 걸었다.
    List<User> findByUsernameQuery(String username);

    //커스텀 쿼리 - 네이티브(순수) 쿼리
   @Query(value = "select * from User u where u.username like %?1%", nativeQuery = true) // 앞뒤로 like검색 걸었다.
   List<User> findByUsernameNativeQuery(String username);


}
