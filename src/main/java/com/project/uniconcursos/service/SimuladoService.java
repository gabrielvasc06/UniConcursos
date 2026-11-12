package com.project.uniconcursos.service;

import com.project.uniconcursos.model.Simulado;
import com.project.uniconcursos.repository.SimuladoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDate;
import java.util.List;

@Service
public class SimuladoService {

    private final SimuladoRepository repository;

    public SimuladoService(SimuladoRepository repository) {
        this.repository = repository;
    }

    public List<Simulado> listarTodos() {
        return repository.findAllByOrderByDataRealizacaoDesc();
    }

    @Transactional
    public void salvar(Simulado simulado) {
        if (simulado.getDataRealizacao() == null) {
            simulado.setDataRealizacao(LocalDate.now());
        }
        if (simulado.getAcertos() < 0) {
            simulado.setAcertos(0);
        }
        if (simulado.getAcertos() > simulado.getTotalQuestoes()) {
            simulado.setAcertos(simulado.getTotalQuestoes());
        }
        repository.save(simulado);
    }

    @Transactional
    public void excluir(Long id) {
        repository.deleteById(id);
    }
}
