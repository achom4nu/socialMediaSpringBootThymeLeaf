package com.example.registrationlogindemo.controller;

import com.example.registrationlogindemo.entity.Comentario;
import com.example.registrationlogindemo.entity.Post;
import com.example.registrationlogindemo.entity.User;
import com.example.registrationlogindemo.repository.UserRepository;
import com.example.registrationlogindemo.service.impl.ComentarioServiceImpl;
import com.example.registrationlogindemo.service.impl.PostServiceImpl;
import com.example.registrationlogindemo.service.impl.UserServiceImpl;
import com.example.registrationlogindemo.storage.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import java.util.List;

@Controller
public class HomeController {
    @Autowired
    PostServiceImpl postService;
    @Autowired
    UserServiceImpl userService;
    @Autowired
    StorageService storageService;
    @Autowired
    ComentarioServiceImpl comentarioService;

    @Autowired
    UserRepository userRepository;
    //@Autowired
    //LikeServiceImpl likeService;
    @GetMapping("/inicio")
    public String inicio(Model model, Authentication authentication){
        Post post = new Post();
        model.addAttribute("nombreUsuario", userService.getUserDto( authentication.getName()).getFirstName());
        User user = userService.findByEmail(userService.getUserDto(authentication.getName()).getEmail());

        model.addAttribute("post", post);
        model.addAttribute("usuario", user);

        List<Post> listadoPosts = postService.findAll();
        model.addAttribute("listadoPosts", listadoPosts);
        
        model.addAttribute("listaUsuarios", userRepository.findAll());

        List<Comentario> listadoComentarios = comentarioService.findAll();
        model.addAttribute("listadoComentarios", listadoComentarios.size());

        return "inicio";
    }

    @PostMapping("/buscar")
    public String buscar(@RequestParam String palabraABuscar, Model model){
        List<Post> postsEncontrados = postService.buscarPostsPorPalabra(palabraABuscar);
        model.addAttribute("listadoPosts", postsEncontrados);

        List<Comentario> comentariosEncontrados = comentarioService.buscarPostsPorPalabra(palabraABuscar);
        model.addAttribute("listadoComentarios", comentariosEncontrados);

        model.addAttribute("listaUsuarios", userRepository.findAll());

        return "buscar";
    }



   /* @GetMapping("/post/like/{id}")
    public String darLike(@PathVariable long id, Model model){
        Post post = postService.findById(id);
        Like like = likeService.findByPost(post);
        post.addLike(like);
        postService.save(post);
        likeService.save(like);
        model.addAttribute("post", post);
        return "redirect:/inicio";
    }*/
}
