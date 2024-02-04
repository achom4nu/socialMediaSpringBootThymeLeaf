package com.example.registrationlogindemo.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Comentario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(columnDefinition = "text")
    private String contenido;
    private String titulo;
    private String imagen;
    private LocalDate fecha;
    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private User usuario;
    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post;

}
