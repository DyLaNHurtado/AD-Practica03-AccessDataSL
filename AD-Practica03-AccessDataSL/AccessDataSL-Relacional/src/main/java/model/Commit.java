package model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class Commit {
    private UUID idCommit;
    private String titulo;
    private String texto;
    private Date fecha;
    private UUID repositorio;
    private UUID proyecto;
    private UUID autor;
    private UUID issue;
}
