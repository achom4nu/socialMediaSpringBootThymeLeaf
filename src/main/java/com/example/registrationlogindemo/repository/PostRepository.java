package com.example.registrationlogindemo.repository;

import com.example.registrationlogindemo.entity.Comentario;
import com.example.registrationlogindemo.entity.Post;
import com.example.registrationlogindemo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    Post findById(long id);
    List<Post> findByUsuario(User user);

    /*@Query("SELECT COUNT(c) FROM Comentario c WHERE c.post = :post")
    int countComentariosByPost(@Param("post") Post post);*/
    @Query("SELECT p FROM Post p WHERE LOWER(p.contenido) LIKE LOWER(CONCAT('%', :palabraBusqueda, '%'))")
    List<Post> findByPalabraBusqueda(@Param("palabraBusqueda") String palabraBusqueda);
}
