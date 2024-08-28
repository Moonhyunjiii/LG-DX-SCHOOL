package com.project.springbootstudy.service.user;

import com.project.springbootstudy.domain.user.User;
import com.project.springbootstudy.repository.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    // 로그인
    public User login(String userId, String password) throws RuntimeException {

//        User userInfo = userRepository.loginCheck(userId, password);
        User userInfo = userRepository.findByUserIdAndPassword(userId, password);
        return userInfo;


    }

    // 회원가입
    public void join(User user) throws RuntimeException {
        // 사용자 ID는 최대 10자리이다.
        if(user.getUserId().length() > 10) {
            throw new RuntimeException("사용자ID는 최대 10자리여야 합니다.");
        }

        // pw는 8자 이상 ~ 10자 이하여야 합니다.
        if(user.getPassword().length() < 8 || user.getPassword().length() >10) {
            throw new RuntimeException("비밀번호는 최소 8자리, 최대 10자리 입니다.");
        }

        // 비밀번호는 영문자, 숫자 혼용이어야 한다.
        int characterCount = 0;
        int numberCount = 0;

        for(int i = 0; i < user.getPassword().length(); i++) {
            if(user.getPassword().charAt(i) >= 'a' && user.getPassword().charAt(i) <= 'z') {
                characterCount++;
            } else if(user.getPassword().charAt(i) >= 'A' && user.getPassword().charAt(i) <= 'Z') {
                characterCount++;
            } else if(user.getPassword().charAt(i) >= '0' && user.getPassword().charAt(i) <= '9') {
                numberCount++;
            }
        }

        if(characterCount == 0 || numberCount == 0) {
            throw new RuntimeException("비밀번호는 영문자, 숫자가 혼용되어야 합니다.");
        }

        //연락처에 '-'가 있다면 빼준다 (이경우에는 오류가 아닌 걸로 처리)
        user.setPhone(user.getPhone().replace("-", ""));

        for(int i = 0; i < user.getPhone().length(); i++) {
            if(user.getPhone().charAt(i) >= '0' && user.getPhone().charAt(i) <= '9') {

            } else {
                throw new RuntimeException("연락처는 숫자와 -만 가능합니다.");
            }
        }
        userRepository.save(user);
    }
}
