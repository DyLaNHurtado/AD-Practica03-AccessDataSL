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
/**
 * @author Dylan Hurtado y Javier González
 * @version 02/09/21 - 1.0
 * clase repositorio
 */

public class Repositorio {
    private String idRepositorio;
    private String idProyecto;
    private LocalDate fechaCreacion;
    private List<String> commits;
    private List<String> issues;
}
