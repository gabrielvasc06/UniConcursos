package com.project.uniconcursos.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String redirecionarInicio() {
        return "redirect:/disciplinas";
    }
}
