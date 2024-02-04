package com.example.registrationlogindemo.service;

import com.example.registrationlogindemo.entity.Post;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PostService {

    void save(Post post);
    Post findById(long id);
    List<Post> findAll();
    void deleteById(long id);
}
