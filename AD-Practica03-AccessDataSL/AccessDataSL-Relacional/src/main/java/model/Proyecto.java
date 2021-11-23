package model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class Proyecto {
    private UUID idProyecto;
    private String nombre;
    private UUID idJefe;
    private Double presupuesto;
    private Date fechaInicio;
    private Date fechaFin;
    private List<String> tecnologias;
    private UUID idRepositorio;
}
