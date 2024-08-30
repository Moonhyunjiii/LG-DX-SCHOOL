package com.project.reviewservice.service.user;

import com.project.reviewservice.domain.user.User;
import com.project.reviewservice.repository.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    public User join(User user) {
        /* 로직은 우선 없다. ( 나중에 유효성 검증 추가해라 )
           바로 repository와 연결
           service는 db일을 하지 않는다. repo한테 떠넘긴다.*/
        User joinedUser = userRepository.save(user);

        return joinedUser;
    }

    public User loginCheck(String userId) {
        /* 사용자 로직 아직없다. (나중에 추가할 것) */

        Optional<User> loginedUser = userRepository.findById(userId);
        return loginedUser.orElse(null);    // 만약 값이 있으면 값 반환, 없으면 null 반환
    }

    public User login(String userId, String password) {
        /* 사용자 로직 아직 없다. (나중에 추가할 것) */
        User user = userRepository.login(userId, password);
        return user;
    }
}