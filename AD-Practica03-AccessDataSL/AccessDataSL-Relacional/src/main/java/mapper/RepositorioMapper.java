package mapper;

import dto.RepositorioDTO;
import model.Commit;
import model.Repositorio;

import java.util.List;
import java.util.Optional;

public class RepositorioMapper extends BaseMapper<Repositorio, RepositorioDTO> {
    @Override
    public Optional<Repositorio> fromDTO(RepositorioDTO item) {
        return Optional.ofNullable(Repositorio.builder()
                .idRepositorio(item.getIdRepositorio())
                .fechaCreacion(item.getFechaCreacion())
                .idProyecto(item.getIdProyecto())
                .commits(item.getCommits())
                .issues(item.getIssues())
                .build());
    }

    @Override
    public Optional<RepositorioDTO> toDTO(Repositorio item) {
        return Optional.ofNullable(RepositorioDTO.builder()
                .idRepositorio(item.getIdRepositorio())
                .fechaCreacion(item.getFechaCreacion())
                .idProyecto(item.getIdProyecto())
                .commits(item.getCommits())
                .issues(item.getIssues())
                .build());
    }

}
