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
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import java.util.List;

@Controller
public class PostController {
    @Autowired
    PostServiceImpl postService;
    @Autowired
    UserRepository userRepository;
    @Autowired
    UserServiceImpl userService;
    @Autowired
    StorageService storageService;
    @Autowired
    ComentarioServiceImpl comentarioService;
    @GetMapping("/post/detalle/{id}")
    public String detalle(@PathVariable long id, Model model, Authentication authentication){
        Post post = new Post();
        post = postService.findById(id);
        model.addAttribute("nombreUsuario", userService.getUserDto(authentication.getName()).getFirstName());
        model.addAttribute("post", post);

        List<Comentario> listadoComentarios = comentarioService.obtenerComentariosPorPostId(id);
        model.addAttribute("listadoComentarios", listadoComentarios);

        model.addAttribute("listaUsuarios", userRepository.findAll());

        return "detalle";
    }

    @PostMapping("/post/add")
    public String addPost(@ModelAttribute Post post,
                          @RequestParam("file") MultipartFile file,
                          @RequestParam("user") Long userId,
                          Authentication authentication){
        if (!file.isEmpty()) {
            String imagen = storageService.store(file, String.valueOf(post.getId()));
            post.setImagen(MvcUriComponentsBuilder
                    .fromMethodName(FileUploadController.class, "serveFile", imagen).build().toUriString());
        }
        User usuario = userService.findByEmail(userService.getUserDto(authentication.getName()).getEmail());
        usuario.setId(userId);

        post.setUsuario(usuario);
        postService.save(post);
        return "redirect:/inicio";
    }

}
