package service;

import dto.CommitDTO;
import mapper.CommitMapper;
import model.Commit;
import repository.RepoCommit;

import javax.xml.bind.annotation.XmlElement;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class CommitService extends BaseService<Commit, String, RepoCommit> {

    CommitMapper mapper = new CommitMapper();
    /**
     * Inyección de dependencias en el constructor. El servicio necesita este repositorio
     * @author Dylan Hurtado y Javier González
     * @version 02/09/21 - 1.0
     */
    public CommitService(RepoCommit repository) {
        super(repository);
    }
    /**
     * Coger todos los commit
     * @author Dylan Hurtado y Javier González
     * @version 02/09/21 - 1.0
     */
    public Optional<List<Optional<CommitDTO>>> getAllCommits() throws SQLException {
        return mapper.toDTO(this.getAll());
    }
    /**
     * Coger todos los commit por id
     * @author Dylan Hurtado y Javier González
     * @version 02/09/21 - 1.0
     */
    public Optional<CommitDTO> getCommitById(String id) throws SQLException {
        if (this.getById(id).isPresent()) {
            return mapper.toDTO(this.getById(id).get());
        }
        System.out.println("CommitService -> " +
                "No se ha encontrado el Commit by id");
        return Optional.empty();
    }
    /**
     * Postear un commit
     * @author Dylan Hurtado y Javier González
     * @version 02/09/21 - 1.0
     */
    public Optional<CommitDTO> postCommit(CommitDTO commitDTO) throws SQLException {
        if (mapper.fromDTO(commitDTO).isPresent()) {
            Optional<Commit> res = this.save(mapper.fromDTO(commitDTO).get());
            if (res.isPresent()) {
                return mapper.toDTO(res.get());
            } else {
                System.out.println("CommitService -> " +
                        "No se ha encontrado Commit toDTO");
                return Optional.empty();
            }
        } else {
            System.out.println("CommitService -> " +
                    "No se ha encontrado Commit fromDTO");
            return Optional.empty();
        }
    }
    /**
     * Updatear un commit
     * @author Dylan Hurtado y Javier González
     * @version 02/09/21 - 1.0
     */
    public Optional<CommitDTO> updateCommit(CommitDTO commitDTO) throws SQLException {
        if (mapper.fromDTO(commitDTO).isPresent()) {
            Optional<Commit> res = this.update(mapper.fromDTO(commitDTO).get());
            if (res.isPresent()) {
                return mapper.toDTO(res.get());
            } else {
                System.out.println("CommitService -> " +
                        "No se ha encontrado Commit toDTO");
                return Optional.empty();
            }
        } else {
            System.out.println("CommitService -> " +
                    "No se ha encontrado Commit fromDTO");
            return Optional.empty();
        }
    }
    /**
     * Deletear un commit
     * @author Dylan Hurtado y Javier González
     * @version 02/09/21 - 1.0
     */
    public Optional<CommitDTO> deleteCommit(CommitDTO commitDTO) throws SQLException {
        if (mapper.fromDTO(commitDTO).isPresent()) {
            Optional<Commit> res = this.delete(mapper.fromDTO(commitDTO).get());
            if (res.isPresent()) {
                return mapper.toDTO(res.get());
            } else {
                System.out.println("CommitService -> " +
                        "No se ha encontrado Commit toDTO");
                return Optional.empty();
            }
        } else {
            System.out.println("CommitService -> " +
                    "No se ha encontrado Commit fromDTO");
            return Optional.empty();
        }
    }
}
