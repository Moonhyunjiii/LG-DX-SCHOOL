package com.project.reviewservice.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration //프로젝트 환경 설정을 application.properties에서 간단히 할 수도 있고
//이렇게 자바코드를 사용해서도 추가적인 환경 설정을 할 수 있습니다.
//여기는 Web 프로젝트에 대한 추가적인 환경 설정을 하고 있습니다.
public class WebConfig implements WebMvcConfigurer {

    @Value("${image.upload.dir}")
    private String uploadDir;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) { //자원의 위치를 추가적으로 세팅할 수 있습니다.
        registry.addResourceHandler("/reviewImage/**")
                .addResourceLocations("file:" + uploadDir);

        //reviewImage가 프로젝트 외부의 경로라서 따로 추가를 해주었습니다.
        //이렇게 작성하면 http://localhost8088/reviewImage 처럼 접근이 가능합니다. (원래는 resource..등으로 해야함)
        // file:uploadDir은 실제 파일 시스템 경로를 가리킵니다.
    }
}