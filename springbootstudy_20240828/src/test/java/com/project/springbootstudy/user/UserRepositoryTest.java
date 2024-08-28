package com.project.springbootstudy.user;

import com.project.springbootstudy.domain.user.User;
import com.project.springbootstudy.repository.user.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

@SpringBootTest
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;


    @Test
    public void 회원가입테스트() {

        User user = new User();
        user.setUserId("test002");
        user.setPassword("pass002");
        user.setAge(30);
        user.setGender("Male");
        user.setPhone("010-1231-1231");
        user.setHomeAddr("주소구 주소동 주소리");

        userRepository.save(user);
    }

    @Test
    public void 회원수정테스트() {

        User user = new User();
        user.setUserId("test002");
        user.setPassword("555555");
        user.setAge(50);
        user.setGender("female");
        user.setPhone("01022233231");
        user.setName("twtw");

        userRepository.save(user);
    }


    @Test
    public void 회원삭제테스트() {
        String userId = "test001";

        userRepository.deleteById(userId);
    }

    @Test
    public void 회원전체조회테스트() {
        List<User> users = userRepository.findAll();

        for(User user : users) {
            System.out.println(user.toString());
        }
    }

    @Test
    public void 특정회원조회테스트() {
        String userId = "test001";
        Optional<User> user = userRepository.findById(userId);

        if(user.isPresent()) {
            System.out.println(user.get().toString());
        } else {
            System.out.println("그런 사용자는 없습니다.");
        }
    }

    //만약 userId가 아닌 다른 조건으로 조회를 하고 싶다면?
    @Test
    public void 성별로조회하기() {

        String gender = "Male";
        List<User> users = userRepository.findByGender(gender);


        for(User user : users) {
            System.out.println(user.toString());
        }

    }

}
