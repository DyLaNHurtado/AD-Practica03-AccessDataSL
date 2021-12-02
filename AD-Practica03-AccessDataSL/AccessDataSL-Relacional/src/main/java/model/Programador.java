package model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor

public class Programador {
    private String idProgramador;
    private String nombre;
    private LocalDate fechaAlta;
    private String idDepartamento;
    private List<String> proyectosParticipa;
    private List<String> commits;
    private List<String> issues;
    private List<String> tecnologias;
    private Double salario;
}
