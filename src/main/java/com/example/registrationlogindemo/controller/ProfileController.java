package com.example.registrationlogindemo.controller;

import com.example.registrationlogindemo.entity.Comentario;
import com.example.registrationlogindemo.entity.Post;
import com.example.registrationlogindemo.entity.User;
import com.example.registrationlogindemo.service.impl.ComentarioServiceImpl;
import com.example.registrationlogindemo.service.impl.PostServiceImpl;
import com.example.registrationlogindemo.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class ProfileController {
    @Autowired
    UserServiceImpl userService;
    @Autowired
    PostServiceImpl postService;
    @Autowired
    ComentarioServiceImpl comentarioService;
    @GetMapping("/perfil/{id}")
    public String perfil(@PathVariable long id, Model model){
        List<Comentario> listaComentarios = comentarioService.findComentariosByUsuario(id);
        List<Post> listaPosts = postService.findPostByUserId(id);
        model.addAttribute("listadoPost", listaPosts);
        model.addAttribute("listadoComentarios", listaComentarios);

        model.addAttribute("usuario", userService.findById(id));

        return "perfil";
    }

    @GetMapping("/perfil/comentarios/{id}")
    public String mostrarComentarios(@PathVariable long id, Model model){
        List<Comentario> listaComentarios = comentarioService.findComentariosByUsuario(id);
        model.addAttribute("listadoComentariosUser", listaComentarios);

        model.addAttribute("usuario", userService.findById(id));
        return "perfil";
    }

    @GetMapping("/perfil/posts/{id}")
    public String mostrarPosts(@PathVariable long id, Model model){
        List<Post> listaPosts = postService.findPostByUserId(id);
        model.addAttribute("listadoPost", listaPosts);

        model.addAttribute("usuario", userService.findById(id));
        return "perfil";
    }
}
