package com.project.uniconcursos.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DisciplinaTests {

    @Test
    void calculaProgressoComHorasFracionadas() {
        Disciplina disciplina = new Disciplina();
        disciplina.setMetaHoras(1.5);
        disciplina.setHorasEstudadas(0.5);

        assertEquals(33, disciplina.getProgressoPercentual());
    }

    @Test
    void limitaProgressoACemPorCento() {
        Disciplina disciplina = new Disciplina();
        disciplina.setMetaHoras(2.0);
        disciplina.setHorasEstudadas(3.0);

        assertEquals(100, disciplina.getProgressoPercentual());
    }

    @Test
    void retornaZeroSemHorasEstudadas() {
        Disciplina disciplina = new Disciplina();

        assertEquals(0, disciplina.getProgressoPercentual());
        disciplina.setHorasEstudadas(null);
        assertEquals(0, disciplina.getProgressoPercentual());
    }

    @Test
    void retornaZeroSemMetaValida() {
        Disciplina disciplina = new Disciplina();
        disciplina.setHorasEstudadas(1.0);

        disciplina.setMetaHoras(null);
        assertEquals(0, disciplina.getProgressoPercentual());
        disciplina.setMetaHoras(0.0);
        assertEquals(0, disciplina.getProgressoPercentual());
        disciplina.setMetaHoras(-1.0);
        assertEquals(0, disciplina.getProgressoPercentual());
    }
}
