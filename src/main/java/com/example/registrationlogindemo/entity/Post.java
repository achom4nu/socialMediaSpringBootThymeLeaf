package com.example.registrationlogindemo.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(columnDefinition = "text")
    private String contenido;

    private String titulo;

    private String imagen;

    private LocalDateTime fecha;

    private Long numberOfComments;
    @PrePersist
    protected void onCreate() {
        this.fecha = LocalDateTime.now();
    }
    //@Column(columnDefinition = "INT DEFAULT 0")
    //private List<Like> likes;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private User usuario;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "comentarios_post")
    private List<Comentario> comentarios = new ArrayList<>() ;
}
