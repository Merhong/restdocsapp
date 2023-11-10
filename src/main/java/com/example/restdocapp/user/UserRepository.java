package com.example.restdocapp.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    // JPQL 쿼리
    @Query("select u from User u where u.username = :username and u.password = :password")
    Optional<User> findByUserNameAndPassword(@Param("username") String username, @Param("password") String password); // Name 쿼리가 발동하지만... 위에 @Query JPQL 쿼리를 붙여준다.
}
