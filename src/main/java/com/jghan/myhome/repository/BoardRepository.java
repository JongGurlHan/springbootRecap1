package com.jghan.myhome.repository;

import com.jghan.myhome.model.Board;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BoardRepository extends JpaRepository<Board, Long> { //<모델명, PK의 타입>

    //제목으로 게시글 검색
    List<Board> findByTitle(String title);
    
    //제목 혹은 내용으로 검색
    List<Board> findByTitleOrContent(String title, String content);

    //게시글 검색
    //findByTitleContaining: title 이 포함된 모든 결과 검색 (like 검색과 비슷)
    Page<Board> findByTitleContainingOrContentContaining(String title, String content, Pageable pageable);



}
