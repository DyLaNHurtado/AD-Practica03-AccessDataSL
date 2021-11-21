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

public class Issue {
    private UUID idIssue;
    private String titulo;
    private String texto;
    private LocalDate fecha;
    private List<Programador> programadores;
    private Proyecto proyecto;
    private Repositorio repositorio;
    private Issue issue;
    private String estado;

}
