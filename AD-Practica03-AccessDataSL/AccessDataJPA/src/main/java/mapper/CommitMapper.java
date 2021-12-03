package mapper;

import dto.CommitDTO;
import dao.Commit;

import java.util.List;
import java.util.Optional;

public class CommitMapper extends BaseMapper<Commit, CommitDTO> {

    @Override
    public Optional<Commit> fromDTO(CommitDTO item) {
        return Optional.ofNullable(Commit.builder()
                .idCommit(item.getIdCommit())
                .titulo(item.getTitulo())
                .texto(item.getTexto())
                //ojo con el date que es DATE y no string
                .fecha(item.getFecha())
                .repositorio(item.getRepositorio())
                .proyecto(item.getProyecto())
                .autor(item.getAutor())
                .issue(item.getIssue())
                .build());
    }

    @Override
    public Optional<CommitDTO> toDTO(Commit item) {

        return Optional.ofNullable(CommitDTO.builder()
                .idCommit(item.getIdCommit())
                .titulo(item.getTitulo())
                .texto(item.getTexto())
                //ojo con el date que es DATE y no string
                .fecha(item.getFecha())
                .repositorio(item.getRepositorio())
                .proyecto(item.getProyecto())
                .autor(item.getAutor())
                .issue(item.getIssue())
                .build());
    }

}
