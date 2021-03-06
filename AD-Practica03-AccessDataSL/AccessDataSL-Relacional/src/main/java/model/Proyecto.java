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
/**
 * @author Dylan Hurtado y Javier González
 * @version 02/09/21 - 1.0
 * clase proyecto
 */

public class Proyecto {
    private String idProyecto;
    private String nombre;
    private String idJefe;
    private Double presupuesto;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private List<String> tecnologias;
    private String idRepositorio;
    private String idDepartamento;
}
