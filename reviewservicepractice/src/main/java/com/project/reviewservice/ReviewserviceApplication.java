package com.project.reviewservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling //이걸 해야 스케줄링을 쓸 수 있습니다!
public class ReviewserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ReviewserviceApplication.class, args);
	}

}