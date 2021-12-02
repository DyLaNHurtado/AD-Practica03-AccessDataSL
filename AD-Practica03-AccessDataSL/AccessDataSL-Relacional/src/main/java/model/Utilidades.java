package model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
/**
 * @author Dylan Hurtado y Javier González
 * @version 02/09/21 - 1.0
 *  clase utilidades
 */

public class Utilidades {
    private String idUtilidades;
    private String idProyecto;
    private List<String> tecnologias;
}
