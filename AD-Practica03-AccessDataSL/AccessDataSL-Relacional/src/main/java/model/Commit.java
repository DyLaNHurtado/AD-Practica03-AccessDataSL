package model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor

public class Commit {
    private String idCommit;
    private String titulo;
    private String texto;
    private Date fecha;
    private String repositorio;
    private String proyecto;
    private String autor;
    private String issue;
}
