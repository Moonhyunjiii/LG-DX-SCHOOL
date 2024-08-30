package com.project.reviewservice.controller.home;

import com.project.reviewservice.domain.review.Review;
import com.project.reviewservice.domain.user.User;
import com.project.reviewservice.service.review.ReviewService;
import com.project.reviewservice.service.user.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class HomeViewController {

    @Autowired
    UserService userService;

    @Autowired
    ReviewService reviewService;


    @GetMapping("/")  //홈페이지 접근 시 로그인으로 유도
    public String home(HttpSession session , Model model) {

        //세션 여부 확인 -> 세션 없으면 login, 있으면 main
        if(session.getAttribute("username") == null) {
            return "/login";
        }

        model.addAttribute("username" , session.getAttribute("username"));
        model.addAttribute("userId" , session.getAttribute("userId"));
        return "main";
    }


    @GetMapping("/login")
    public String login(HttpSession session , RedirectAttributes redirectAttributes) {
        //세션 여부 확인 -> 세션 없으면 login, 있으면 main
        if(session.getAttribute("username") == null) {
            return "/login";
        }

        redirectAttributes.addFlashAttribute("username", session.getAttribute("username"));
        redirectAttributes.addFlashAttribute("userId", session.getAttribute("userId"));
        return "redirect:/main";
    }

    @GetMapping("/main")
    public String main(HttpSession session , Model model) {

        if(session.getAttribute("username") == null) {
            return "/login";
        }
        model.addAttribute("username" , session.getAttribute("username"));
        model.addAttribute("userId", session.getAttribute("userId"));

        List<Review> reviews = reviewService.findAllReviews();

        model.addAttribute("reviews", reviews);
        return "main";
    }

    @GetMapping("/register")
    public String register() {
        return "register"; // 알아서 templates 폴더안에 있는 register.html을 찾아준다.
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute User user, Model model) {
        User joinedUser = userService.join(user);

        if(joinedUser != null) {
            return "/login";
        } else {
            model.addAttribute("error", "실패했대요 ㅎㅎ");
            return "/register";
        }

    }

    @PostMapping("/login-process")
    public String loginProcess(@RequestParam("userId") String userId
                             , @RequestParam("password") String password
                             , Model model
                             , HttpSession session) {
        User user = userService.login(userId, password);
        if(user != null) {
            model.addAttribute("username", user.getName());
            session.setAttribute("username", user.getName());
            session.setAttribute("userId", userId);
            model.addAttribute("reviews", reviewService.findAllReviews());
            return "/main";
        } else {
            model.addAttribute("error", "로그인에 실패했습니다.ㅠㅠ");
            return "/login";
        }
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();

        return "redirect:/login";
    }
}
