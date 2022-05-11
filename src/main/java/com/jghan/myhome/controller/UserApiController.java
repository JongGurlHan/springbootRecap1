package com.jghan.myhome.controller;

import com.jghan.myhome.model.Board;
import com.jghan.myhome.model.User;
import com.jghan.myhome.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.util.StringUtils;

import java.util.List;

@RestController
@RequestMapping("/api")
public class UserApiController {

    @Autowired
    private UserRepository repository;

    @GetMapping("/users")
    List<User> all() {
        return repository.findAll();
    }

    @PostMapping("/users")
    User newUser(@RequestBody User newUser) {
        return repository.save(newUser);
    }

    @GetMapping("/users/{id}")
    User one(@PathVariable Long id) {
        return repository.findById(id).orElse(null);
    }

    @PutMapping("/users/{id}")
    User replaceUser(@RequestBody User newUser, @PathVariable Long id) {

        return repository.findById(id)
                    .map(user -> {

                    //user.setBoards(newUser.getBoards());

                    user.getBoards().clear(); //user에 있는 기존 board정보 삭제
                    user.getBoards().addAll(newUser.getBoards()); //기존 board를 새 보드로 바꿈

                    for (Board board : user.getBoards()){
                        board.setUser(user);
                    }
                    return repository.save(user);
                })
                //null일 경우 새 개시글 등록
                .orElseGet(() -> {
                    newUser.setId(id);
                    return repository.save(newUser);
                });
    }

    @DeleteMapping("/users/{id}")
    void deleteUser(@PathVariable Long id) {
        repository.deleteById(id);
    }


}






