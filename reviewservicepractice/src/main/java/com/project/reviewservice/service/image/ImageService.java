package com.project.reviewservice.service.image;

import com.project.reviewservice.domain.image.Image;
import com.project.reviewservice.repository.image.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Service
public class ImageService {

    @Autowired
    ImageRepository imageRepository;

    @Value("${image.upload.dir}")
    private String uploadDir;

    public void saveImages(MultipartFile[] files, int reviewNo){
        for(MultipartFile file : files) {
            try{
                saveImage(file, reviewNo);
            } catch(IOException e) {
                System.out.println("실패한거있음 ㅜㅠ");
            }

        }
    }

    public Image saveImage(MultipartFile file, int reviewNo) throws IOException {
        String fileName = file.getOriginalFilename(); //업로드할 파일명 가져오기
        Path uploadPath = Paths.get(uploadDir).toAbsolutePath().normalize(); //업로드할 경로 가져오기(절대경로)
        Path filePath = uploadPath.resolve(fileName); //최종 경로 세팅 (경로 + 파일명)

        Files.createDirectories(uploadPath); //해당 폴더가 없으면 생성
        file.transferTo(filePath.toFile());  //해당 위치에 파일을 실제로 저장

        Image image = new Image();
        image.setImageName(fileName);
        image.setImagePath(uploadDir + fileName);
        image.setReviewNo(reviewNo);

        return imageRepository.save(image);
    }

    // 하나의 리뷰에 여러 이미지가 가능하게..
    public List<Image> getImagesByReviewNo(int reviewNo) {
        return imageRepository.findByReviewNo(reviewNo);
    }
}
