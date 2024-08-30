package com.project.reviewservice.repository.image;

import com.project.reviewservice.domain.image.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ImageRepository extends JpaRepository<Image, Integer> {

    @Query(value = "SELECT * FROM TB_IMAGE WHERE REVIEW_NO = :reviewNo", nativeQuery = true)
    List<Image> findByReviewNo(@Param("reviewNo") int reviewNo);
}