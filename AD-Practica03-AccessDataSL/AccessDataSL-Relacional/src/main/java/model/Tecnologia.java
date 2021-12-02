package model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
/**
 * @author Dylan Hurtado y Javier González
 * @version 02/09/21 - 1.0
 * clase tecnologias
 */

public class Tecnologia {
    private String idTecnologia;
    private String nombre;
}
