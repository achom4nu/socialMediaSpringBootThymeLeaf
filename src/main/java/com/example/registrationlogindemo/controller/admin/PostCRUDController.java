package com.example.registrationlogindemo.controller.admin;

import com.example.registrationlogindemo.controller.FileUploadController;
import com.example.registrationlogindemo.entity.Post;
import com.example.registrationlogindemo.service.impl.ComentarioServiceImpl;
import com.example.registrationlogindemo.service.impl.PostServiceImpl;
import com.example.registrationlogindemo.storage.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.List;

@Controller
public class PostCRUDController {
    @Autowired
    PostServiceImpl postService;
    @Autowired
    ComentarioServiceImpl comentarioService;
    @Autowired
    StorageService storageService;
    @GetMapping("/admin/posts")
    public String listadoPosts(Model model){
        List<Post> listaPosts = postService.findAll();
        model.addAttribute("listaPosts", listaPosts);
        return "admin/crudPost";
    }

    @GetMapping("/admin/eliminar/post/{id}")
    public String eliminar(@PathVariable long id){
        Post post = postService.findById(id);


        post.getComentarios().clear();
        postService.save(post);
        postService.deleteById(id);
        return "redirect:/admin/posts";
    }

    @GetMapping("/admin/modificar/post/{id}")
    public String modificarPost(@PathVariable Long id, Model model) {
        // Aquí puedes recuperar el Post por su ID desde tu base de datos
        // y pasarlo al modelo
        Post post = postService.findById(id);
        model.addAttribute("objeto", post);
        model.addAttribute("tipo", "post");
        return "admin/modificarCRUD";
    }

    @PostMapping("/guardar-post")
    public String guardarPostModificado(@ModelAttribute Post post,
                                        @RequestParam("file") MultipartFile file,
                                        @RequestParam("id") Long id){
        if (!file.isEmpty()) {
            String imagen = storageService.store(file, String.valueOf(post.getId()));
            post.setImagen(MvcUriComponentsBuilder
                    .fromMethodName(FileUploadController.class, "serveFile", imagen).build().toUriString());
        }

        /*Post postActualizado = postService.findById(id);
        postActualizado.setImagen(post.getImagen());
        postActualizado.setId(id);
        postActualizado.setFecha(LocalDateTime.now());
        postActualizado.setContenido(post.getContenido());
        postActualizado.setTitulo(post.getTitulo());
        postActualizado.setUsuario(post.getUsuario());
        postActualizado.setComentarios(post.getComentarios());
        postActualizado.setNumberOfComments(post.getNumberOfComments());*/

        postService.save(post);

        return "redirect:/admin/posts";
    }
}
