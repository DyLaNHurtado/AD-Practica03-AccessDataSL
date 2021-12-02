package service;

import dto.ProgramadorDTO;
import mapper.ProgramadorMapper;
import model.Programador;
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

    /**
     * Coger todos los programadores
     * @author Dylan Hurtado y Javier González
     * @version 02/09/21 - 1.0
     */
    public Optional<List<Optional<ProgramadorDTO>>> getAllProgramadores() throws SQLException {
        return mapper.toDTO(this.getAll());
    }
    /**
     * Coger todos los programadores por id
     * @author Dylan Hurtado y Javier González
     * @version 02/09/21 - 1.0
     */
    public Optional<ProgramadorDTO> getProgramadorById(String id) throws SQLException {
        if (this.getById(id).isPresent()) {
            return mapper.toDTO(this.getById(id).get());
        }
        System.out.println("ProgramadorService -> " +
                "No se ha encontrado el Programador by id");
        return Optional.empty();
    }
    /**
     * Postear un programador
     * @author Dylan Hurtado y Javier González
     * @version 02/09/21 - 1.0
     */
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
    /**
     * Updatear un programador
     * @author Dylan Hurtado y Javier González
     * @version 02/09/21 - 1.0
     */
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
    /**
     * Deletear un programador
     * @author Dylan Hurtado y Javier González
     * @version 02/09/21 - 1.0
     */
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
    /**
     * Coger proyectos ordenados por commits
     * @author Dylan Hurtado y Javier González
     * @version 02/09/21 - 1.0
     */
    public List<Programador> getAllByProyectoSortByCommits(String idProyecto) throws SQLException {
        RepoProgramador repo = new RepoProgramador();
        return repo.getAllByProyectoSortByCommits(idProyecto).orElseThrow(()->new SQLException("ProgramadorService -> No se han encontrado programadores por idProyecto ordenados por numero de commits"));
    }
    /**
     * Coger TODA la informacion de los programadores
     * @author Dylan Hurtado y Javier González
     * @version 02/09/21 - 1.0
     */
    public List<Object> getAllProgramadoresFullInfo() throws SQLException {
        RepoProgramador repo = new RepoProgramador();
        return repo.getAllProgramadoresInfo().orElseThrow(()->new SQLException("ProgramadorService -> No se han encontrado programadores con info completa"));
    }
}