package com.example.registrationlogindemo.controller;

import com.example.registrationlogindemo.entity.Post;
import com.example.registrationlogindemo.repository.PostRepository;
import com.example.registrationlogindemo.service.PostService;
import com.example.registrationlogindemo.service.impl.PostServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class PostController {
    @Autowired
    PostServiceImpl postService;
    @GetMapping("/post/detalle/{id}")
    public String detalle(@PathVariable long id, Model model){
        Post post = new Post();
        post = postService.findById(id);
        model.addAttribute("post", post);
        return "detalle";
    }


}
