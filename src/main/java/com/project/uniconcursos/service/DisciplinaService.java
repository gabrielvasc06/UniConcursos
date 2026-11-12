package com.project.uniconcursos.service;

import com.project.uniconcursos.model.Disciplina;
import com.project.uniconcursos.repository.DisciplinaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class DisciplinaService {

    private final DisciplinaRepository repository;

    public DisciplinaService(DisciplinaRepository repository) {
        this.repository = repository;
    }

    public List<Disciplina> listarOrdenadas() {
        return repository.findAllByOrderByOrdemCicloAsc();
    }

    @Transactional
    public void salvar(Disciplina disciplina) {
        if (disciplina.getHorasEstudadas() == null) {
            disciplina.setHorasEstudadas(0.0);
        }
        if (disciplina.getMetaHoras() == null || disciplina.getMetaHoras() <= 0) {
            disciplina.setMetaHoras(1.0);
        }
        repository.save(disciplina);
    }

    @Transactional
    public void adicionarHoras(Long id, double horas) {
        Disciplina disciplina = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Disciplina não encontrada: " + id));
        disciplina.setHorasEstudadas(disciplina.getHorasEstudadas() + horas);
        repository.save(disciplina);
    }

    @Transactional
    public void reiniciarCiclo() {
        List<Disciplina> todas = repository.findAll();
        todas.forEach(d -> d.setHorasEstudadas(0.0));
        repository.saveAll(todas);
    }

    @Transactional
    public void excluir(Long id) {
        repository.deleteById(id);
    }
}
