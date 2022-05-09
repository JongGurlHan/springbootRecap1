package com.jghan.myhome.repository;

import com.jghan.myhome.model.Board;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<Board, Long> { //<모델명, PK의 타입>




}
