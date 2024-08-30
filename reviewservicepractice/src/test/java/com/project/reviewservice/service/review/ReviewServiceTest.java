package com.project.reviewservice.service.review;

import com.project.reviewservice.domain.review.Review;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class ReviewServiceTest {

    @Autowired
    ReviewService reviewService;


    @Test
    public void 리뷰_저장_테스트() {
        // given
        String reviewerId = "123123";
        String title = "리뷰테스트";
        String content = "리뷰리뷰테스트내용내용";

        // when
        Review review = reviewService.saveReview(reviewerId, title, content);

        // then
        System.out.println(review);
        // 만약 정상적으로 들어갔다면 review 데이터가 있을 것
    }

    @Test
    public void 리뷰_전체_조회_테스트() {
        //given

        //when
        List<Review> reviews = reviewService.findAllReviews();

        //then
        for(Review review : reviews) {
            System.out.println(review);
        }
    }
}
