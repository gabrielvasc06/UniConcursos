package com.project.uniconcursos.controller;

import com.project.uniconcursos.model.Disciplina;
import com.project.uniconcursos.service.DisciplinaService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/disciplinas")
public class DisciplinaController {

    private final DisciplinaService service;

    public DisciplinaController(DisciplinaService service) {
        this.service = service;
    }

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("disciplinas", service.listarOrdenadas());
        model.addAttribute("novaDisciplina", new Disciplina());
        return "disciplinas";
    }

    @PostMapping("/salvar")
    public String salvar(@ModelAttribute Disciplina disciplina) {
        service.salvar(disciplina);
        return "redirect:/disciplinas";
    }

    @PostMapping("/{id}/estudar")
    public String registrarEstudo(@PathVariable Long id,
                                  @RequestParam(defaultValue = "1.0") double horas) {
        service.adicionarHoras(id, horas);
        return "redirect:/disciplinas";
    }

    @PostMapping("/reiniciar")
    public String reiniciarCiclo() {
        service.reiniciarCiclo();
        return "redirect:/disciplinas";
    }

    @GetMapping("/excluir/{id}")
    public String excluir(@PathVariable Long id) {
        service.excluir(id);
        return "redirect:/disciplinas";
    }
}
