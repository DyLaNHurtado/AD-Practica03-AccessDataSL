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
 * clase issue
 */
public class Issue {
    private String idIssue;
    private String titulo;
    private String texto;
    private LocalDate fecha;
    private List<String> programadores;
    private String proyecto;
    private String repositorio;
    private String estado;

}
