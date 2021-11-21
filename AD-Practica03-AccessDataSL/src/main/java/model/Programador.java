package model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class Programador {
    private UUID idProgramador;
    private String nombre;
    private LocalDate fechaAlta;
    private Departamento departamento;
    private List<Proyecto> proyectosParticipa;
    private List<Commit> commits;
    private List<Issue> issues;
    private List<Tecnologia> tecnologias;
    private Double salario;
}
