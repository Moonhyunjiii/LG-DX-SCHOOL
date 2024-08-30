package com.project.reviewservice.repository.user;

import com.project.reviewservice.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

// JPA를 상속받은 레포지토리 : 내부적으로 CONPONENT가 있음
public interface UserRepository extends JpaRepository<User, String> {

    @Query(value = "SELECT * FROM TB_USER WHERE USER_ID = :userId AND PASSWORD = :password", nativeQuery = true)
    User login(@Param("userId") String userId, @Param("password") String password);

}
