package model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor

public class Proyecto {
    private String idProyecto;
    private String nombre;
    private String idJefe;
    private Double presupuesto;
    private Date fechaInicio;
    private Date fechaFin;
    private List<String> tecnologias;
    private String idRepositorio;
    private String idDepartamento;
}
