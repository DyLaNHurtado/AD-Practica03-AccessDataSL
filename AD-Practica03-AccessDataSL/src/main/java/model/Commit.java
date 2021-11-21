package model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class Commit {
    private UUID idCommit;
    private String titulo;
    private String texto;
    private LocalDate fecha;
    private Repositorio repositorio;
    private Proyecto proyecto;
    private Programador autor;
    private Issue issue;
}
