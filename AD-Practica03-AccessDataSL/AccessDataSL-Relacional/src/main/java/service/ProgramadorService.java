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
        if (this.getById(id).isPresent()) {
            return mapper.toDTO(this.getById(id).get());
        }
        System.out.println("ProgramadorService -> " +
                "No se ha encontrado el Programador by id");
        return Optional.empty();
    }

    public Optional<ProgramadorDTO> postProgramador(ProgramadorDTO programadorDTO) throws SQLException {
        if (mapper.fromDTO(programadorDTO).isPresent()) {
            Optional<Programador> res = this.save(mapper.fromDTO(programadorDTO).get());
            if (res.isPresent()) {
                return mapper.toDTO(res.get());
            } else {
                System.out.println("ProgramadorService -> " +
                        "No se ha encontrado Programador toDTO");
                return Optional.empty();
            }
        } else {
            System.out.println("ProgramadorService -> " +
                    "No se ha encontrado Programador fromDTO");
            return Optional.empty();
        }
    }

    public Optional<ProgramadorDTO> updateProgramador(ProgramadorDTO programadorDTO) throws SQLException {
        if (mapper.fromDTO(programadorDTO).isPresent()) {
            Optional<Programador> res = this.update(mapper.fromDTO(programadorDTO).get());
            if (res.isPresent()) {
                return mapper.toDTO(res.get());
            } else {
                System.out.println("ProgramadorService -> " +
                        "No se ha encontrado Programador toDTO");
                return Optional.empty();
            }
        } else {
            System.out.println("ProgramadorService -> " +
                    "No se ha encontrado Programador fromDTO");
            return Optional.empty();
        }
    }

    public Optional<ProgramadorDTO> deleteProgramador(ProgramadorDTO programadorDTO) throws SQLException {
        if (mapper.fromDTO(programadorDTO).isPresent()) {
            Optional<Programador> res = this.delete(mapper.fromDTO(programadorDTO).get());
            if (res.isPresent()) {
                return mapper.toDTO(res.get());
            } else {
                System.out.println("ProgramadorService -> " +
                        "No se ha encontrado Programador toDTO");
                return Optional.empty();
            }
        } else {
            System.out.println("ProgramadorService -> " +
                    "No se ha encontrado Programador fromDTO");
            return Optional.empty();
        }
    }
}