package com.project.uniconcursos.model;
import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table (name = "disciplinas")
public class Disciplina {
    

    @Id 
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    @Column (name = "nome", nullable = false)
    private String nome;

    @Column (name = "ordemCiclo", nullable = false)
    private Integer ordemCiclo;

    @Column (name = "metaHoras", nullable = false)
    private Double metaHoras = 1.0;

    @Column(name = "horasEstudadas", nullable = false)
    private Double horasEstudadas = 0.0;

    @OneToMany (mappedBy = "disciplina", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Simulado> simulados = new ArrayList<>();

    public Disciplina() {
    }

    public int getProgressoPercentual() {
        if (metaHoras == null || metaHoras <= 0 || horasEstudadas == null) {
            return 0;
        }
        int percentual = (int) Math.round((horasEstudadas / metaHoras) * 100);
        return Math.min(percentual, 100);
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public Integer getOrdemCiclo() { return ordemCiclo; }
    public void setOrdemCiclo(Integer ordemCiclo) { this.ordemCiclo = ordemCiclo; }

    public Double getMetaHoras() { return metaHoras; }
    public void setMetaHoras(Double metaHoras) { this.metaHoras = metaHoras; }

    public Double getHorasEstudadas() { return horasEstudadas; }
    public void setHorasEstudadas(Double horasEstudadas) { this.horasEstudadas = horasEstudadas; }

    public List<Simulado> getSimulados() { return simulados; }
    public void setSimulados(List<Simulado> simulados) { this.simulados = simulados; }
    
}
