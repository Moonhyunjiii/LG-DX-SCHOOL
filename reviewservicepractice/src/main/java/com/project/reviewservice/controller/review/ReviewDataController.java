package com.project.reviewservice.controller.review;


import com.project.reviewservice.domain.image.Image;
import com.project.reviewservice.domain.review.Review;
import com.project.reviewservice.service.image.ImageService;
import com.project.reviewservice.service.review.ReviewService;
import jakarta.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.List;

@RestController
public class ReviewDataController {

    @Autowired
    ReviewService reviewService;

    @Autowired
    ImageService imageService;

    public static final Logger log = LoggerFactory.getLogger("com.project.reviewservice.controller.review.ReviewDataController.findReviewByNo");

    @GetMapping("/reviews/{reviewNo}")
    public HashMap<String, Object> findReviewByNo(@PathVariable(name="reviewNo") Integer reviewNo, HttpSession session) {

        String username = (String) session.getAttribute("username");

        log.info(username + " - " + reviewNo + " review click");

        HashMap<String, Object> data = new HashMap();

        // 지금 우리가 필요한 데이터
        Review review = reviewService.findReviewByNo(reviewNo);
        List<Image> images = imageService.getImagesByReviewNo(reviewNo);

        data.put("review", review);
        data.put("images", images);

        return data;
    }

    @PostMapping("/review/saveReview")
    public ResponseEntity<String> saveImages(@RequestParam(name="title") String title,
                                             @RequestParam(name="content") String content,
                                             @RequestParam(name="images") MultipartFile[] files,
                                             HttpSession session) {
        // 리뷰 저장 reviewId, title, content
        String userId = (String) session.getAttribute("userId");
        Review review = reviewService.saveReview(userId, title, content); // 리뷰번호도 가지고 있음

        imageService.saveImages(files, review.getReviewNo());

        return ResponseEntity.ok("success");
    }
}
