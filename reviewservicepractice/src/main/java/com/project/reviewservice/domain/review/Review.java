package com.project.reviewservice.domain.review;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name="TB_REVIEW")
public class Review {

    @Id
    @Column(name="REVIEW_NO")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "tb_review_seq") // 자동으로 PK를 생성해준다. +1전략이다.
    @SequenceGenerator(name = "tb_review_seq", sequenceName = "tb_review_seq", allocationSize = 1)
    private int reviewNo;

    @Column(name="REVIEWER_ID")
    private String reviewerId;

    @Column(name="REVIEW_TITLE")
    private String reviewTitle;

    @Column(name="REVIEW_CONTENT")
    private String reviewContent;

    @Column(name="REVIEW_DATE")
    private Date reviewDate;

    public Review() {}

    public int getReviewNo() {
        return reviewNo;
    }

    public void setReviewNo(int reviewNo) {
        this.reviewNo = reviewNo;
    }

    public String getReviewerId() {
        return reviewerId;
    }

    public void setReviewerId(String reviewerId) {
        this.reviewerId = reviewerId;
    }

    public String getReviewTitle() {
        return reviewTitle;
    }

    public void setReviewTitle(String reviewTitle) {
        this.reviewTitle = reviewTitle;
    }

    public String getReviewContent() {
        return reviewContent;
    }

    public void setReviewContent(String reviewContent) {
        this.reviewContent = reviewContent;
    }

    public Date getReviewDate() {
        return reviewDate;
    }

    public void setReviewDate(Date reviewDate) {
        this.reviewDate = reviewDate;
    }

    @Override
    public String toString() {
        return "Review{" +
                "reviewNo=" + reviewNo +
                ", reviewerId='" + reviewerId + '\'' +
                ", reviewTitle='" + reviewTitle + '\'' +
                ", reviewContent='" + reviewContent + '\'' +
                ", reviewDate=" + reviewDate +
                '}';
    }
}
