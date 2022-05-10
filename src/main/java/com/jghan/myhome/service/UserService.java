package com.jghan.myhome.service;

import com.jghan.myhome.model.Role;
import com.jghan.myhome.model.User;
import com.jghan.myhome.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {


    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    //유저 저장
    public User save(User user){
        // 1.비밀번호 인코딩
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);

        // 2.회원 활성화 여부 - 기본적으로 enabled로 설정
        user.setEnabled(true);


        // 3. role정보 추가
        Role role = new Role();
        role.setId(1L);
        user.getRoles().add(role);
        return userRepository.save(user);
    }
}
