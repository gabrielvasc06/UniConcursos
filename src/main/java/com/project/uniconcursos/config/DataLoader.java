package com.project.uniconcursos.config;

import com.project.uniconcursos.model.Disciplina;
import com.project.uniconcursos.model.Simulado;
import com.project.uniconcursos.repository.DisciplinaRepository;
import com.project.uniconcursos.repository.SimuladoRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import java.time.LocalDate;

@Configuration
public class DataLoader implements CommandLineRunner {

    private final DisciplinaRepository disciplinaRepository;
    private final SimuladoRepository simuladoRepository;

    public DataLoader(DisciplinaRepository disciplinaRepository,
                      SimuladoRepository simuladoRepository) {
        this.disciplinaRepository = disciplinaRepository;
        this.simuladoRepository = simuladoRepository;
    }

    @Override
    public void run(String... args) {
        if (disciplinaRepository.count() == 0) {
            Disciplina portugues = new Disciplina();
            portugues.setNome("Língua Portuguesa");
            portugues.setOrdemCiclo(1);
            portugues.setMetaHoras(4.0);
            portugues.setHorasEstudadas(2.5);
            disciplinaRepository.save(portugues);

            Disciplina rlm = new Disciplina();
            rlm.setNome("Raciocínio Lógico");
            rlm.setOrdemCiclo(2);
            rlm.setMetaHoras(3.0);
            rlm.setHorasEstudadas(1.0);
            disciplinaRepository.save(rlm);

            Disciplina constitucional = new Disciplina();
            constitucional.setNome("Direito Constitucional");
            constitucional.setOrdemCiclo(3);
            constitucional.setMetaHoras(4.0);
            constitucional.setHorasEstudadas(4.0);
            disciplinaRepository.save(constitucional);

            Simulado sim1 = new Simulado();
            sim1.setTitulo("Simulado Diagnóstico 01");
            sim1.setDataRealizacao(LocalDate.now());
            sim1.setTotalQuestoes(50);
            sim1.setAcertos(43);
            sim1.setDisciplina(constitucional);
            simuladoRepository.save(sim1);
        }
    }
}
