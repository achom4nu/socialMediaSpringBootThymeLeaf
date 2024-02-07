package com.example.registrationlogindemo.service.impl;

import com.example.registrationlogindemo.entity.Post;
import com.example.registrationlogindemo.entity.User;
import com.example.registrationlogindemo.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostServiceImpl {
    @Autowired
    PostRepository postRepository;
    public void save(Post post){
        postRepository.save(post);
    }
    public Post findById(long id){
        return postRepository.findById(id);
    }
    public List<Post> findAll(){
        return postRepository.findAll();
    }
    public void deleteById(long id){
        postRepository.deleteById(id);
    }
    public List<Post> findByUsuario(User user){
        return postRepository.findByUsuario(user);
    }
    /*public int countComentariosByPost(Post post){
        return postRepository.countComentariosByPost(post);
    }*/
}
