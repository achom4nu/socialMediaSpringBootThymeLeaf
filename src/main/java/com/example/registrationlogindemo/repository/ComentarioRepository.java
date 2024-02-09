package com.example.registrationlogindemo.repository;

import com.example.registrationlogindemo.entity.Comentario;
import com.example.registrationlogindemo.entity.Post;
import com.example.registrationlogindemo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface ComentarioRepository extends JpaRepository<Comentario, Long> {
    Comentario findById(long id);
    List<Comentario> findComentariosByPostId(long id);
    /*@Query("SELECT COUNT(c) FROM Comment c WHERE c.post = :post")
    Long countCommentsByPost(@Param("post") Post post);*/
    List<Comentario> findByUsuario(User user);
    @Modifying
    @Transactional
    @Query("DELETE FROM Comentario c WHERE c.post = :post")
    void deleteByPost(@Param("post") Post post);
    @Query("SELECT c FROM Comentario c WHERE LOWER(c.contenido) LIKE LOWER(CONCAT('%', :palabraBusqueda, '%'))")
    List<Comentario> findByPalabraBusqueda(@Param("palabraBusqueda") String palabraBusqueda);
}
