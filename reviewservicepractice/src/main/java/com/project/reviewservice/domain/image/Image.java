package com.project.reviewservice.domain.image;

import jakarta.persistence.*;

import java.util.Arrays;

@Entity
@Table(name = "tb_image")
public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE , generator = "tb_image_seq") // key 전략을 자동으로 시퀀스를 이용하겠다는 의미
    @SequenceGenerator(name = "tb_image_seq", sequenceName = "tb_image_seq", allocationSize = 1)
    @Column(name="IMAGE_NO")
    private int imageNo ;

    @Column(name="IMAGE_NAME")
    private String imageName ;

    @Column(name="IMAGE_PATH")
    private String imagePath;

    @Column(name="REVIEW_NO")
    private int reviewNo;


    //그 밑에는 setter & getter + toString


    public int getImageNo() {
        return imageNo;
    }

    public void setImageNo(int imageNo) {
        this.imageNo = imageNo;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public int getReviewNo() {
        return reviewNo;
    }

    public void setReviewNo(int reviewNo) {
        this.reviewNo = reviewNo;
    }

    @Override
    public String toString() {
        return "Image{" +
                "imageNo=" + imageNo +
                ", imageName='" + imageName + '\'' +
                ", imagePath='" + imagePath + '\'' +
                ", reviewNo=" + reviewNo +
                '}';
    }
}
