package com.example.registrationlogindemo.service;

import com.example.registrationlogindemo.entity.Comentario;
import com.example.registrationlogindemo.entity.Post;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ComentarioService {
    void save(Comentario comentario);
    Comentario findById(long id);
    List<Comentario> findAll();
    void deleteById(long id);
    List<Comentario> obtenerComentariosPorPostId(long id);

}
