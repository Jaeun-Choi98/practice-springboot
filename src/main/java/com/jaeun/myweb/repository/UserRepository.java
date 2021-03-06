package com.jaeun.myweb.repository;

import com.jaeun.myweb.model.User;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.persistence.Entity;
import java.util.List;

public interface UserRepository extends JpaRepository<User,Long> {
    @EntityGraph(attributePaths = {"boards"})
    List<User> findAll();
    User findByUsername(String username);
}
