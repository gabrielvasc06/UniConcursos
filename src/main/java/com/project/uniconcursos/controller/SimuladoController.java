package com.project.uniconcursos.controller;

import com.project.uniconcursos.model.Simulado;
import com.project.uniconcursos.service.DisciplinaService;
import com.project.uniconcursos.service.SimuladoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/simulados")
public class SimuladoController {

    private final SimuladoService simuladoService;
    private final DisciplinaService disciplinaService;

    public SimuladoController(SimuladoService simuladoService, DisciplinaService disciplinaService) {
        this.simuladoService = simuladoService;
        this.disciplinaService = disciplinaService;
    }

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("simulados", simuladoService.listarTodos());
        model.addAttribute("disciplinas", disciplinaService.listarOrdenadas());
        model.addAttribute("novoSimulado", new Simulado());
        return "simulados";
    }

    @PostMapping("/salvar")
    public String salvar(@ModelAttribute Simulado simulado) {
        simuladoService.salvar(simulado);
        return "redirect:/simulados";
    }

    @GetMapping("/excluir/{id}")
    public String excluir(@PathVariable Long id) {
        simuladoService.excluir(id);
        return "redirect:/simulados";
    }
}
