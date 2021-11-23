package model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class Repositorio {
    private UUID idRepositorio;
    private Date fechaCreacion;
    private UUID idProyecto;
    private List<String> commits;
    private List<String> issues;
}
