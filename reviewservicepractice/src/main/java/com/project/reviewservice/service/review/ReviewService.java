package com.project.reviewservice.service.review;

import com.project.reviewservice.domain.review.Review;
import com.project.reviewservice.repository.review.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ReviewService {

    @Autowired
    ReviewRepository reviewRepository;

    public Review saveReview(String reviewerId, String title, String content) {
        Review review = new Review();
        review.setReviewerId(reviewerId);
        review.setReviewTitle(title);
        review.setReviewContent(content);
        review.setReviewDate(new Date());
        // reviewNo는 시퀀스를 사용해서 자동으로 들어간다.
        // reviewDate는 ? null이 들어가면 우리가 자동으로 값을 넣어주면 된다.

        Review savedReview = reviewRepository.save(review);

        return savedReview;
    }

    public List<Review> findAllReviews() {
        List<Review> reviews = reviewRepository.findAll();
        return reviews;

    }

    public Review findReviewByNo(int reviewNo) {
        Optional<Review> review = reviewRepository.findById(reviewNo);
        return review.orElse(null);
    }
}
