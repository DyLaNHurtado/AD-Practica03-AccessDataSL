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

public class Repositorio {
    private String idRepositorio;
    private Date fechaCreacion;
    private String idProyecto;
    private List<String> commits;
    private List<String> issues;
}