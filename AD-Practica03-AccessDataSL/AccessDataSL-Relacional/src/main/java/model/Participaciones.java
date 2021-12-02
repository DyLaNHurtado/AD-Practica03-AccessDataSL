package model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
/**
 * @author Dylan Hurtado y Javier González
 * @version 02/09/21 - 1.0
 * clase participaciones
 */
public class Participaciones {

    private String idParticipaciones;
    private String idProyecto;
    private List<String> programadores;
}
