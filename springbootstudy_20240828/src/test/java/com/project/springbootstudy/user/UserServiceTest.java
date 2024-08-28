package com.project.springbootstudy.user;

import com.project.springbootstudy.domain.user.User;
import com.project.springbootstudy.repository.user.UserRepository;
import com.project.springbootstudy.service.user.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UserServiceTest {

    @Autowired
    UserService userService;

    @Autowired
    UserRepository userRepository;

    @Test
    public void loginTest() {
        String userId = "test001";
        String password = "111111";
        User user1 = new User();
        user1.setUserId(userId);
        user1.setPassword(password);
        user1.setAge(50);
        user1.setGender("female");
        user1.setPhone("01022233231");
        user1.setName("twtw");

        Assertions.assertTrue(userService.login(userId, password));
    }
}
