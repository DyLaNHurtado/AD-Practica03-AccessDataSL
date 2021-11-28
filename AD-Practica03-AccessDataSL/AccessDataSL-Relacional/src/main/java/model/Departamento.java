package model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@Builder

public class Departamento {
    private String idDepartamento;
    private String nombre;
    private String idJefe;
    private List<String> trabajadores;
    private Double presupuesto;
    private List<String> proyFinalizados;
    private List<String> proyDesarrollo;
    private Double presupuestoAnual;
    private List<String> historialJefes;
}
