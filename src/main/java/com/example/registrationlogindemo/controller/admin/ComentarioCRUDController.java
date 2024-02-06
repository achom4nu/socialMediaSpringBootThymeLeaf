package com.example.registrationlogindemo.controller.admin;

import com.example.registrationlogindemo.entity.Comentario;
import com.example.registrationlogindemo.service.impl.ComentarioServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class ComentarioCRUDController {
    @Autowired
    ComentarioServiceImpl comentarioService;
    @GetMapping("/admin/comentarios")
    public String listadoComentarios(Model model){
        List<Comentario> listaComentarios = comentarioService.findAll();
        model.addAttribute("listaComentarios", listaComentarios);
        return "admin/crudComentario";
    }

    // Endpoint para modificar un Comentario
    @GetMapping("/admin/modificar/comentario/{id}")
    public String modificarComentario(@PathVariable Long id, Model model) {
        // Aquí puedes recuperar el Comentario por su ID desde tu base de datos
        // y pasarlo al modelo
        Comentario comentario = comentarioService.findById(id);
        model.addAttribute("objeto", comentario);
        model.addAttribute("tipo", "comentario");
        return "admin/modificarCRUD";
    }

    @PostMapping("/guardar-comentario")
    public String guardarComentario(@ModelAttribute Comentario comentario) {
        comentarioService.save(comentario);
        return "redirect:/admin/comentarios";
    }

    @GetMapping("/admin/eliminar/comentario/{id}")
    public String eliminarComentario(@PathVariable long id){
        comentarioService.deleteById(id);
        return "redirect:/admin/comentarios";
    }

}
