package model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
public class Participaciones {

    private String idParticipaciones;
    private String idProyecto;
    private List<String> programadores;
}
