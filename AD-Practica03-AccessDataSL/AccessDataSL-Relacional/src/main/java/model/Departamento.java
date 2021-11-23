package model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class Departamento {
    private UUID idDepartamento;
    private String nombre;
    private UUID idJefe;
    private Double presupuesto;
    private List<String> proyFinalizados;
    private List<String> proyDesarrollo;
    private Double presupuestoAnual;
    private List<String> historialJefes;
}
