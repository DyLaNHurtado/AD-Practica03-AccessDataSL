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

public class Creaciones {
    private String idCreaciones;
    private String idIssue;
    private String idProgramador;
}
