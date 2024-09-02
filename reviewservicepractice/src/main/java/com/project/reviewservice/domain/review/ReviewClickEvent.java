package com.project.reviewservice.domain.review;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Date;

/*
CREATE TABLE TB_REVIEW_CLICK_EVENT (
    NO NUMBER PRIMARY KEY ,
    USER_NAME VARCHAR2(50) ,
    REVIEW_NO NUMBER ,
    REG_DATE DATE
);
CREATE SEQUENCE TB_REVIEW_CLICK_SEQ INCREMENT BY 1 ;
 */
@Entity
@Table(name = "TB_REVIEW_CLICK_EVENT")
public class ReviewClickEvent {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "tb_review_click_seq") //자동으로 PK을 관리해준다. +1전략이다.
    @SequenceGenerator(name = "tb_review_click_seq", sequenceName = "tb_review_click_seq", allocationSize = 1)
    private int no;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "review_no")
    private int reviewNo;

    @Column(name = "reg_date ")
    private Date regDate;

    public ReviewClickEvent() {
    }
    // Getters and Setters

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getReviewNo() {
        return reviewNo;
    }

    public void setReviewNo(int reviewNo) {
        this.reviewNo = reviewNo;
    }

    public Date getRegDate() {
        return regDate;
    }

    public void setRegDate(Date regDate) {
        this.regDate = regDate;
    }

    @Override
    public String toString() {
        return "ReviewClickEvent{" +
                "no=" + no +
                ", userName='" + userName + '\'' +
                ", reviewNo=" + reviewNo +
                ", regDate=" + regDate +
                '}';
    }

}