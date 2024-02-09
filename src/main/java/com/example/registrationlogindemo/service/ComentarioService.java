package com.example.registrationlogindemo.service;

import com.example.registrationlogindemo.entity.Comentario;
import com.example.registrationlogindemo.entity.Post;
import com.example.registrationlogindemo.entity.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ComentarioService {
    void save(Comentario comentario);
    Comentario findById(long id);
    List<Comentario> findAll();
    void deleteById(long id);
    List<Comentario> obtenerComentariosPorPostId(long id);
    List<Comentario> findByUsuario(User user);
    void deleteByPost(Post post);
    List<Comentario> buscarPostsPorPalabra(String palabraBusqueda);
}
