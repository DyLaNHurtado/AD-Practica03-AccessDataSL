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

public class Repositorio {
    private UUID idRepositorio;
    private LocalDate fechaCreacion;
    private Proyecto proyecto;
    private List<Commit> commits;
    private List<Issue> issues;
}
