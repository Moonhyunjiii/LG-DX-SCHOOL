package com.project.springbootstudy.controller.user;

import com.project.springbootstudy.domain.user.User;
import com.project.springbootstudy.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserVeiwController {

    @Autowired
    UserService userService;

    @GetMapping("/user/login")
    public String goLogin() {
        return "/user/login";
    }

    // 로그인 하기
    @PostMapping("login-process")
    public String loginCheck(@RequestParam(name="userId") String userId
                           , @RequestParam(name="password") String password
                           , Model model) {

        User userCheck = userService.login(userId, password);

        if(userCheck != null) {
            model.addAttribute("success", "환영합니다, ");
            model.addAttribute("userName", userCheck.getName());
            return "home";
        }
        model.addAttribute("error", "없는 회원입니다.");
        return "user/login";
    }

    @GetMapping("/user/join")
    public String goJoin() {
        return "/user/join";
    }

    @PostMapping("join-process")
    public String joinProcess(@ModelAttribute User user, Model model) {
        try {
            userService.join(user);
            model.addAttribute("success", "회원가입이 성공적으로 완료됐습니다.");
            return "/user/login";
        } catch (RuntimeException e) {
            model.addAttribute("error", e.getMessage());
            return "/user/join";
        }
    }

}
