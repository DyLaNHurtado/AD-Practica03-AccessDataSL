package model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class Departamento {
    private UUID idDepartamento;
    private String nombre;
    private Programador jefe;
    private Double presupuesto;
    private List<Proyecto> proyFinalizados;
    private List<Proyecto> proyDesarrollo;
    private Double presupuestoAnual;
    private List<Programador> historialJefes;
}
