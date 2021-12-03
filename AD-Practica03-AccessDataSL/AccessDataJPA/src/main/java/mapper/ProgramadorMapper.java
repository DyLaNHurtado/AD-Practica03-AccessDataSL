package mapper;

import dto.ProgramadorDTO;
import dao.Commit;
import dao.Programador;

import java.util.List;
import java.util.Optional;

public class ProgramadorMapper extends BaseMapper<Programador, ProgramadorDTO> {

    @Override
    public Optional<Programador> fromDTO(ProgramadorDTO item) {
        return Optional.ofNullable(Programador.builder()
                .idProgramador(item.getIdProgramador())
                .nombre(item.getNombre())
                .fechaAlta(item.getFechaAlta())
                //Cuidado que es tipo Date
                .idDepartamento(item.getIdDepartamento())
                .proyectosParticipa(item.getProyectosParticipa())
                .commits(item.getCommits())
                .issues(item.getIssues())
                .tecnologias(item.getTecnologias())
                .salario(item.getSalario())
                .build());
    }

    @Override
    public Optional<ProgramadorDTO> toDTO(Programador item) {
        return Optional.ofNullable(ProgramadorDTO.builder()
                .idProgramador(item.getIdProgramador())
                .nombre(item.getNombre())
                .fechaAlta(item.getFechaAlta())
                //Cuidado que es tipo Date
                .idDepartamento(item.getIdDepartamento())
                .proyectosParticipa(item.getProyectosParticipa())
                .commits(item.getCommits())
                .issues(item.getIssues())
                .tecnologias(item.getTecnologias())
                .salario(item.getSalario())
                .build());
    }
}
