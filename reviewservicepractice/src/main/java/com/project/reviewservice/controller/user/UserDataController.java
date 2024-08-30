package com.project.reviewservice.controller.user;


import com.project.reviewservice.domain.user.User;
import com.project.reviewservice.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController // Controller와의 차이점 : Controller는 화면은 반환하고 RestController는 데이터 자체를 반환
public class UserDataController {
    @Autowired
    UserService userService;

    @GetMapping("/checkUserId")
    public boolean checkUserId(@RequestParam("userId") String userId) {
        System.out.println("사용자 중복 확인 요청 ID : " + userId);

        User user = userService.loginCheck(userId);

        if(user != null) {
            return true;
        } else {
            return false;
        }
    }
}
