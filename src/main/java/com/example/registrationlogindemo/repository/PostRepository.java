package com.example.registrationlogindemo.repository;

import com.example.registrationlogindemo.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    Post findById(long id);
}
