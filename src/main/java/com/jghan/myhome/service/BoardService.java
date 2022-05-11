package com.jghan.myhome.service;

import com.jghan.myhome.model.Board;
import com.jghan.myhome.model.User;
import com.jghan.myhome.repository.BoardRepository;
import com.jghan.myhome.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BoardService {

    @Autowired
    private BoardRepository boardRepository;

    @Autowired
    private UserRepository userRepository;

    public Board save(String username, Board board){
        //1. 넘겨받은 username을 가지고 user의 id 조회

        User user = userRepository.findByUsername(username);
        board.setUser(user);

        return boardRepository.save(board);

    }
}
