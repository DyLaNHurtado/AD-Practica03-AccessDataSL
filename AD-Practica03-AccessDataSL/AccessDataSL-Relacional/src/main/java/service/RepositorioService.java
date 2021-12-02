package service;

import dto.CommitDTO;
import dto.RepositorioDTO;
import mapper.CommitMapper;
import mapper.RepositorioMapper;
import model.Commit;
import model.Repositorio;
import repository.RepoCommit;
import repository.RepoRepositorio;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class RepositorioService extends BaseService<Repositorio, String, RepoRepositorio> {

    RepositorioMapper mapper = new RepositorioMapper();

    // Inyección de dependencias en el constructor. El servicio necesita este repositorio
    public RepositorioService(RepoRepositorio repository) {
        super(repository);
    }
    /**
     * Coger todos los repositorios
     * @author Dylan Hurtado y Javier González
     * @version 02/09/21 - 1.0
     */
    public Optional<List<Optional<RepositorioDTO>>> getAllRepositorios() throws SQLException {
        return mapper.toDTO(this.getAll());
    }
    /**
     * Coger todos los repositorios por id
     * @author Dylan Hurtado y Javier González
     * @version 02/09/21 - 1.0
     */
    public Optional<RepositorioDTO> getRepositorioById(String id) throws SQLException {
        if (this.getById(id).isPresent()) {
            return mapper.toDTO(this.getById(id).get());
        }
        System.out.println("RepositorioService -> " +
                "No se ha encontrado el Repositorio by id");
        return Optional.empty();
    }
    /**
     * Postear un repositorio
     * @author Dylan Hurtado y Javier González
     * @version 02/09/21 - 1.0
     */
    public Optional<RepositorioDTO> postRepositorio(RepositorioDTO repositorioDTO) throws SQLException {
        if (mapper.fromDTO(repositorioDTO).isPresent()) {
            Optional<Repositorio> res = this.save(mapper.fromDTO(repositorioDTO).get());
            if (res.isPresent()) {
                return mapper.toDTO(res.get());
            } else {
                System.out.println("RepositorioService -> " +
                        "No se ha encontrado Repositorio toDTO");
                return Optional.empty();
            }
        } else {
            System.out.println("RepositorioService -> " +
                    "No se ha encontrado Repositorio fromDTO");
            return Optional.empty();
        }
    }
    /**
     * Updatear un repositorio
     * @author Dylan Hurtado y Javier González
     * @version 02/09/21 - 1.0
     */
    public Optional<RepositorioDTO> updateRepositorio(RepositorioDTO repositorioDTO) throws SQLException {
        if (mapper.fromDTO(repositorioDTO).isPresent()) {
            Optional<Repositorio> res = this.update(mapper.fromDTO(repositorioDTO).get());
            if (res.isPresent()) {
                return mapper.toDTO(res.get());
            } else {
                System.out.println("RepositorioService -> " +
                        "No se ha encontrado Repositorio toDTO");
                return Optional.empty();
            }
        } else {
            System.out.println("RepositorioService -> " +
                    "No se ha encontrado Repositorio fromDTO");
            return Optional.empty();
        }
    }
    /**
     * Deletear un repositorio
     * @author Dylan Hurtado y Javier González
     * @version 02/09/21 - 1.0
     */
    public Optional<RepositorioDTO> deleteRepositorio(RepositorioDTO repositorioDTO) throws SQLException {
        if (mapper.fromDTO(repositorioDTO).isPresent()) {
            Optional<Repositorio> res = this.delete(mapper.fromDTO(repositorioDTO).get());
            if (res.isPresent()) {
                return mapper.toDTO(res.get());
            } else {
                System.out.println("RepositorioService -> " +
                        "No se ha encontrado Repositorio toDTO");
                return Optional.empty();
            }
        } else {
            System.out.println("RepositorioService -> " +
                    "No se ha encontrado Repositorio fromDTO");
            return Optional.empty();
        }
    }
}
