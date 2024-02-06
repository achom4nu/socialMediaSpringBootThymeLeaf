package com.example.registrationlogindemo.service.impl;

import com.example.registrationlogindemo.entity.Comentario;
import com.example.registrationlogindemo.entity.Post;
import com.example.registrationlogindemo.repository.ComentarioRepository;
import com.example.registrationlogindemo.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ComentarioServiceImpl {
    @Autowired
    ComentarioRepository comentarioRepository;
    @Autowired
    PostRepository postRepository;

    public void save(Comentario post){
        comentarioRepository.save(post);
    }
    public Comentario findById(long id){
        return comentarioRepository.findById(id);
    }
    public List<Comentario> findAll(){
        return comentarioRepository.findAll();
    }
    public void deleteById(long id){
        comentarioRepository.deleteById(id);
    }
    public List<Comentario> obtenerComentariosPorPostId(long id) {
        return comentarioRepository.findComentariosByPostId(id);
    }
    /*public Long countCommentsByPost(@Param("post") Post post){
        return comentarioRepository.countCommentsByPost(post);
    }*/
    public List<Comentario> findComentariosByUsuario(long id){
        return comentarioRepository.findComentariosByUsuario(id);
    }


}
