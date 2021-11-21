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

public class Proyecto {
    private UUID idProyecto;
    private String nombre;
    private Programador jefe;
    private Double presupuesto;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private List<Tecnologia> tecnologias;
    private Repositorio repositorio;
}
