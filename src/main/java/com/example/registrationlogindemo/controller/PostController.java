package com.example.registrationlogindemo.controller;

import com.example.registrationlogindemo.entity.Post;
import com.example.registrationlogindemo.repository.PostRepository;
import com.example.registrationlogindemo.service.PostService;
import com.example.registrationlogindemo.service.UserService;
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

@Controller
public class PostController {
    @Autowired
    PostServiceImpl postService;
    @Autowired
    UserServiceImpl userService;
    @Autowired
    StorageService storageService;
    @GetMapping("/post/detalle/{id}")
    public String detalle(@PathVariable long id, Model model, Authentication authentication){
        Post post = new Post();
        post = postService.findById(id);
        model.addAttribute("nombreUsuario", userService.getUserDto(authentication.getName()).getFirstName());
        model.addAttribute("post", post);
        return "detalle";
    }

    @PostMapping("/post/add")
    public String addPost(@ModelAttribute Post post, @RequestParam("file") MultipartFile file){
        if (!file.isEmpty()) {
            String imagen = storageService.store(file, String.valueOf(post.getId()));
            System.out.println("La imagen a guardar es : " + imagen);
            post.setImagen(MvcUriComponentsBuilder
                    .fromMethodName(FileUploadController.class, "serveFile", imagen).build().toUriString());
        }
        postService.save(post);
        return "redirect:/inicio";
    }

}
