package com.project.reviewservice.controller.review;


import com.project.reviewservice.domain.review.Review;
import com.project.reviewservice.service.review.ReviewService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ReviewDataController {

    @Autowired
    ReviewService reviewService;
    
    @PostMapping("/review/saveReview")
    public ResponseEntity<String> saveReview(@RequestParam("title") String title
                                           , @RequestParam("content") String content
                                           , HttpSession session) {

        // 리뷰 저장을 위한 데이터
        String reviewerId = (String) session.getAttribute("userId");
        Review savedReview = reviewService.saveReview(reviewerId, title, content);

        if(savedReview != null){
            return ResponseEntity.ok("success");
        } else {
            return ResponseEntity.ok("fail");
        }
    }
}
