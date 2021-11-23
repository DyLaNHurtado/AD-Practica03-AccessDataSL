package model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class Programador {
    private UUID idProgramador;
    private String nombre;
    private Date fechaAlta;
    private UUID idDepartamento;
    private List<String> proyectosParticipa;
    private List<String> commits;
    private List<String> issues;
    private List<String> tecnologias;
    private Double salario;
}
