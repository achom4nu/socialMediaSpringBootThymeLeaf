package com.example.registrationlogindemo.controller;

import com.example.registrationlogindemo.entity.Post;
import com.example.registrationlogindemo.entity.User;
import com.example.registrationlogindemo.service.PostService;
import com.example.registrationlogindemo.service.impl.PostServiceImpl;
import com.example.registrationlogindemo.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class HomeController {
    @Autowired
    PostServiceImpl postService;
    @Autowired
    UserServiceImpl userService;
    //@Autowired
    //LikeServiceImpl likeService;
    @GetMapping("/inicio")
    public String inicio(Model model, Authentication authentication){
        /*authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        System.out.println(currentPrincipalName);

        /*String nombreUsuario = authentication.getName();

        // Obtén el objeto Usuario completo (puedes tener un servicio para esto)
        User usuario = userService.findByName(nombreUsuario);

        // Crea un nuevo objeto Post y establece el usuario

        User user = new User();
        user.setName(currentPrincipalName);
        post.setUsuario(user);*/
        Post post = new Post();
        model.addAttribute("post", post);

        List<Post> listadoPosts = postService.findAll();
        if (listadoPosts.isEmpty()) {
            // Si no hay posts, agregamos un post vacío al modelo
            model.addAttribute("postHay", "No hay ningun post todavía... ¡Se el primero!");
        } else {
            // Si hay posts, agregamos la lista de posts al modelo
            model.addAttribute("listadoPosts", listadoPosts);
        }
        return "inicio";
    }

    @PostMapping("/post/add")
    public String addPost(@ModelAttribute Post post){
        postService.save(post);
        return "redirect:/inicio";
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
