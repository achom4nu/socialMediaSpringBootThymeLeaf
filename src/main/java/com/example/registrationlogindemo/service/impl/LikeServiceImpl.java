/*package com.example.registrationlogindemo.service.impl;

import com.example.registrationlogindemo.entity.Like;
import com.example.registrationlogindemo.entity.Post;
import com.example.registrationlogindemo.repository.LikeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LikeServiceImpl {
    @Autowired
    LikeRepository likeRepository;
    public void save(Like like){
        likeRepository.save(like);
    }
    public Like findById(long id){
       return likeRepository.findById(id);
    }
    public List<Like> findAll(){
       return likeRepository.findAll();
    }
    public void deleteById(long id){
        likeRepository.deleteById(id);
    }
    public Like findByPost(Post post){
        return likeRepository.findByPost(post);
    }

}*/
