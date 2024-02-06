package com.example.registrationlogindemo.repository;

import com.example.registrationlogindemo.entity.Comentario;
import com.example.registrationlogindemo.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ComentarioRepository extends JpaRepository<Comentario, Long> {
    Comentario findById(long id);
    List<Comentario> findComentariosByPostId(long id);
    /*@Query("SELECT COUNT(c) FROM Comment c WHERE c.post = :post")
    Long countCommentsByPost(@Param("post") Post post);*/
    List<Comentario> findComentariosByUsuario(long id);
}
