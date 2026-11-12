package com.project.uniconcursos.repository;

import com.project.uniconcursos.model.Simulado;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface SimuladoRepository extends JpaRepository<Simulado, Long> {
    List<Simulado> findAllByOrderByDataRealizacaoDesc();
}
