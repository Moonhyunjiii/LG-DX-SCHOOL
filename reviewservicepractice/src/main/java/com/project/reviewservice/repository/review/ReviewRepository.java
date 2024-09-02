package com.project.reviewservice.repository.review;


import com.project.reviewservice.domain.review.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Integer> {
    //이곳에 추가
    @Query(value = "SELECT * FROM TB_REVIEW WHERE TO_CHAR(REVIEW_DATE , 'YYYYMMDD') = :yyyymmdd" , nativeQuery = true)
    List<Review> findReviewsByDay(@Param(value = "yyyymmdd") String yyyymmmdd);

}
