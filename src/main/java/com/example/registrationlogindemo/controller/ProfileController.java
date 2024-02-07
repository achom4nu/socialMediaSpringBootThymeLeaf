package com.example.registrationlogindemo.controller;

import com.example.registrationlogindemo.entity.Comentario;
import com.example.registrationlogindemo.entity.Post;
import com.example.registrationlogindemo.entity.User;
import com.example.registrationlogindemo.service.impl.ComentarioServiceImpl;
import com.example.registrationlogindemo.service.impl.PostServiceImpl;
import com.example.registrationlogindemo.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
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
        //Da error porque digo que le paso un long pero Usuario es de tipo User
        User usuario = userService.findById(id);
        List<Comentario> listaComentarios = comentarioService.findByUsuario(usuario);
        List<Post> listaPosts = postService.findByUsuario(usuario);

        model.addAttribute("listadoPost", listaPosts);
        model.addAttribute("listadoComentarios", listaComentarios);

        model.addAttribute("usuario", userService.findById(id));

        return "perfil";
    }

    @GetMapping("/perfil")
    public String perfil(Model model, Authentication authentication){
        User user = userService.findByEmail(userService.getUserDto(authentication.getName()).getEmail());

        return "redirect:/perfil/" + user.getId();
    }

    @GetMapping("/perfil/comentarios/{id}")
    public String mostrarComentarios(@PathVariable long id, Model model){
        User usuario = userService.findById(id);
        List<Comentario> listaComentarios = comentarioService.findByUsuario(usuario);
        model.addAttribute("listadoComentariosUser", listaComentarios);

        model.addAttribute("usuario", userService.findById(id));
        return "perfil";
    }

    @GetMapping("/perfil/posts/{id}")
    public String mostrarPosts(@PathVariable long id, Model model){
        User usuario = userService.findById(id);
        List<Post> listaPosts = postService.findByUsuario(usuario);
        model.addAttribute("listadoPostUser", listaPosts);

        model.addAttribute("usuario", userService.findById(id));
        return "perfil";
    }
}
