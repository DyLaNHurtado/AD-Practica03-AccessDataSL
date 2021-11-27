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

public class Issue {
    private String idIssue;
    private String titulo;
    private String texto;
    private Date fecha;
    private List<String> programadores;
    private String proyecto;
    private String repositorio;
    private String estado;

}
