package com.project.reviewservice.service.image;

import com.project.reviewservice.domain.image.Image;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@SpringBootTest
public class ImageServiceTest {

    @Autowired
    ImageService imageService;

    @Test
    public void 이미지_등록_테스트() {

        byte[] test = "test".getBytes();
        MultipartFile file1 = new MockMultipartFile("file", "test-image1.png", "image/png", test);
        int reviewNo = 3;

        try {
            Image image = imageService.saveImage(file1, reviewNo);
            System.out.println(image);
        } catch(IOException e) {
            System.out.println("싫패 ㅠㅠ");
        }
    }

    @Test
    public void 이미지_여러개_등록_테스트() {
        byte[] test1 = "test1111".getBytes();
        MultipartFile file1 = new MockMultipartFile("file", "test-image1111.png", "image/png", test1);
        int reviewNo = 3;

        byte[] test2 = "test2222".getBytes();
        MultipartFile file2 = new MockMultipartFile("file", "test-image2222.png", "image/png", test2);

        MultipartFile[] files = new MultipartFile[2];
        files[0] = file1;
        files[1] = file2;

        imageService.saveImages(files, 2);
    }
}
