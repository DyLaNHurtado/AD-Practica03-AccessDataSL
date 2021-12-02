package model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.time.LocalDate;
import java.util.Date;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor

/**
 * @author Dylan Hurtado y Javier González
 * @version 02/09/21 - 1.0
 * @return
 */
public class Commit {
    private String idCommit;
    private String titulo;
    private String texto;
    private LocalDate fecha;
    private String repositorio;
    private String proyecto;
    private String autor;
    private String issue;
}
