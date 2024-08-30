package com.project.reviewservice.repository.review;


import com.project.reviewservice.domain.review.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review, Integer> {


}
