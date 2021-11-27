package mapper;

import dto.IssueDTO;
import model.Commit;
import model.Issue;

import java.util.List;
import java.util.Optional;

public class IssueMapper extends BaseMapper<Issue, IssueDTO> {

    @Override
    public Optional<Issue> fromDTO(IssueDTO item) {
        return Optional.ofNullable(Issue.builder()
                .idIssue(item.getIdIssue())
                .titulo(item.getTitulo())
                .texto(item.getTexto())
                //Cuidado que es tipo Date
                .fecha(item.getFecha())
                .programadores(item.getProgramadores())
                .proyecto(item.getProyecto())
                .repositorio(item.getRepositorio())
                .estado(item.getEstado())
                .build());
    }

    @Override
    public Optional<IssueDTO> toDTO(Issue item) {
        return Optional.ofNullable(IssueDTO.builder()
                .idIssue(item.getIdIssue())
                .titulo(item.getTitulo())
                .texto(item.getTexto())
                //Cuidado que es tipo Date
                .fecha(item.getFecha())
                .programadores(item.getProgramadores())
                .proyecto(item.getProyecto())
                .repositorio(item.getRepositorio())
                .estado(item.getEstado())
                .build());
    }
}
