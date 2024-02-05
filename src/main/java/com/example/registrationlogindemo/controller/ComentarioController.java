package com.example.registrationlogindemo.controller;

import com.example.registrationlogindemo.entity.Comentario;
import com.example.registrationlogindemo.entity.Post;
import com.example.registrationlogindemo.repository.ComentarioRepository;
import com.example.registrationlogindemo.repository.PostRepository;
import com.example.registrationlogindemo.service.ComentarioService;
import com.example.registrationlogindemo.service.PostService;
import com.example.registrationlogindemo.service.impl.ComentarioServiceImpl;
import com.example.registrationlogindemo.service.impl.PostServiceImpl;
import com.example.registrationlogindemo.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class ComentarioController {
    @Autowired
    ComentarioServiceImpl comentarioService;
    @Autowired
    PostServiceImpl postService;
    @Autowired
    UserServiceImpl userService;
    @GetMapping("/post/comentarios/{id}")
    public String comentariosDePost(@PathVariable long id, Model model, Authentication authentication){
        Comentario comentario = new Comentario();
        Post post = postService.findById(id);
        long idPost = post.getId();

        comentario.setPost(post);
        comentario.setUsuario(userService.findByEmail(userService.getUserDto(authentication.getName()).getEmail()));

        model.addAttribute("post", post);
        model.addAttribute("comentario", comentario);
        model.addAttribute("listadoComentarios", comentarioService.obtenerComentariosPorPostId(idPost));
        model.addAttribute("nombreUsuario", userService.getUserDto(authentication.getName()).getFirstName());
        return "add-comentario";
    }

    @PostMapping("/post/addComentario")
    public String addComentario(@ModelAttribute Comentario comentario, Model model){
        comentarioService.save(comentario);
        return "redirect:/inicio";
    }
}
