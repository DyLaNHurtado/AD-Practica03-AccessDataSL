package service;

import dto.CommitDTO;
import dto.ProgramadorDTO;
import jdk.jfr.Category;
import mapper.CommitMapper;
import mapper.ProgramadorMapper;
import model.Commit;
import model.Programador;
import repository.RepoCommit;
import repository.RepoProgramador;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class ProgramadorService extends BaseService<Programador, String, RepoProgramador> {

    ProgramadorMapper mapper = new ProgramadorMapper();

    // Inyección de dependencias en el constructor. El servicio necesita este repositorio
    public ProgramadorService(RepoProgramador repository) {
        super(repository);
    }

    public Optional<List<Optional<ProgramadorDTO>>> getAllProgramadores() throws SQLException {
        return mapper.toDTO(this.getAll());
    }

    public Optional<ProgramadorDTO> getProgramadorById(String id) throws SQLException {
        return mapper.toDTO(this.getById(id).get());
    }

    public Optional<ProgramadorDTO> postProgramador(ProgramadorDTO programadorDTO) throws SQLException {
        Optional<Programador> res = this.save(mapper.fromDTO(programadorDTO).get());
        return mapper.toDTO(res.get());
    }

    public Optional<ProgramadorDTO> updateProgramador(ProgramadorDTO programadorDTO) throws SQLException {
        Optional<Programador> res = this.update(mapper.fromDTO(programadorDTO).get());
        return mapper.toDTO(res.get());
    }

    public Optional<ProgramadorDTO> deleteProgramador(ProgramadorDTO programadorDTO) throws SQLException {
        Optional<Programador> res = this.delete(mapper.fromDTO(programadorDTO).get());
        return mapper.toDTO(res.get());
    }
}
