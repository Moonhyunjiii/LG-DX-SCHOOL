package com.project.reviewservice.controller.analysis;


import com.project.reviewservice.domain.review.Review;
import com.project.reviewservice.service.review.ReviewService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class AnalysisDataController {

    private static final Logger log = LoggerFactory.getLogger("com.project.reviewservice.controller.analysis.AnalysisDataController");

    @Autowired
    ReviewService reviewService;

    @PostMapping("/review-analysis")
    public void requestReviewAnalysisToFlask(@RequestParam(name="day") String day) {

        //전송하려는 데이터는 특정 일자의 리뷰
        List<Review> reviews = reviewService.findReviewsByDay(day.replace("-" , ""));

        if(reviews.size() == 0) {
            log.info("0건에 대한 리뷰분석 시도. 결과 없음");
            return;
        }

        log.info(day + "일자 " + reviews.size() + "건에 대한 리뷰분석을 요청했습니다.");


        //SpringBoot에서 HTTP 요청을 보낼때 사용하는 클래스 (Flask 에 Post방식으로 HTTP 요청을 보낼것이다)
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:5000/receive-data";

        // 전송할 데이터를 준비합니다.
        Map<String, Object> data = new HashMap<>();
        data.put( "reviews" , reviews);
        data.put("day", day);

        //헤더 설정 (json으로 보낼거야~)
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        // 요청 엔티티 생성
        HttpEntity<Map<String, Object>> requestEntity = new HttpEntity<>(data, headers);

        // Flask 서버로 POST 요청을 보냅니다.
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, requestEntity, String.class);

        System.out.println("[플라스크 측 응답] " + response.getBody());

    }
}
