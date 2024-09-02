package com.project.reviewservice.repository.review;

import com.project.reviewservice.domain.review.ReviewClickEvent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;

public interface ReviewClickEventRepository extends JpaRepository<ReviewClickEvent , Integer> {

    @Query(value = "SELECT NVL( MAX(REG_DATE) , TO_DATE('19700101' , 'YYYYMMDD') ) AS regDate FROM TB_REVIEW_CLICK_EVENT" , nativeQuery = true)
    Date findReviewByMaxDate();
}