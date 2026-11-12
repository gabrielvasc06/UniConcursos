package com.project.uniconcursos.repository;

import com.project.uniconcursos.model.Disciplina;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface DisciplinaRepository extends JpaRepository<Disciplina, Long> {
    List<Disciplina> findAllByOrderByOrdemCicloAsc();
}
