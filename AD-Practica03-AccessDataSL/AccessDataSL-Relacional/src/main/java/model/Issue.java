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

public class Issue {
    private UUID idIssue;
    private String titulo;
    private String texto;
    private Date fecha;
    private List<String> programadores;
    private UUID proyecto;
    private UUID repositorio;
    private String estado;

}
