package com.example.registrationlogindemo.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeCRUDController {

    @GetMapping("/admin/inicio")
    public String inicioCrud(){
        return "admin/crudInicio";
    }
}
