package service;

import dto.IssueDTO;
import mapper.IssueMapper;
import model.Issue;
import repository.RepoIssue;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class IssueService extends BaseService<Issue, String, RepoIssue> {

    IssueMapper mapper = new IssueMapper();

    // Inyección de dependencias en el constructor. El servicio necesita este repositorio
    public IssueService(RepoIssue repository) {
        super(repository);
    }
    /**
     * Coger todos los isuues
     * @author Dylan Hurtado y Javier González
     * @version 02/09/21 - 1.0
     */
    public Optional<List<Optional<IssueDTO>>> getAllIssues() throws SQLException {
        return mapper.toDTO(this.getAll());
    }
    /**
     * Coger todos los issue por id
     * @author Dylan Hurtado y Javier González
     * @version 02/09/21 - 1.0
     */
    public Optional<IssueDTO> getIssueById(String id) throws SQLException {
        if (this.getById(id).isPresent()) {
            return mapper.toDTO(this.getById(id).get());
        }
        System.out.println("IssueService -> " +
                "No se ha encontrado el Issue by id");
        return Optional.empty();
    }
    /**
     * Postear un Issue
     * @author Dylan Hurtado y Javier González
     * @version 02/09/21 - 1.0
     */
    public Optional<IssueDTO> postIssue(IssueDTO issueDTO) throws SQLException {
        if (mapper.fromDTO(issueDTO).isPresent()) {
            Optional<Issue> res = this.save(mapper.fromDTO(issueDTO).get());
            if (res.isPresent()) {
                return mapper.toDTO(res.get());
            } else {
                System.out.println("IssueService -> " +
                        "No se ha encontrado Issue toDTO");
                return Optional.empty();
            }
        } else {
            System.out.println("IssueService -> " +
                    "No se ha encontrado Issue fromDTO");
            return Optional.empty();
        }
    }
    /**
     * Updatear un issue
     * @author Dylan Hurtado y Javier González
     * @version 02/09/21 - 1.0
     */
    public Optional<IssueDTO> updateIssue(IssueDTO issueDTO) throws SQLException {
        if (mapper.fromDTO(issueDTO).isPresent()) {
            Optional<Issue> res = this.update(mapper.fromDTO(issueDTO).get());
            if (res.isPresent()) {
                return mapper.toDTO(res.get());
            } else {
                System.out.println("IssueService -> " +
                        "No se ha encontrado Issue toDTO");
                return Optional.empty();
            }
        } else {
            System.out.println("IssueService -> " +
                    "No se ha encontrado Issue fromDTO");
            return Optional.empty();
        }
    }
    /**
     * Deletear un issue
     * @author Dylan Hurtado y Javier González
     * @version 02/09/21 - 1.0
     */
    public Optional<IssueDTO> deleteIssue(IssueDTO issueDTO) throws SQLException {
        if (mapper.fromDTO(issueDTO).isPresent()) {
            Optional<Issue> res = this.delete(mapper.fromDTO(issueDTO).get());
            if (res.isPresent()) {
                return mapper.toDTO(res.get());
            } else {
                System.out.println("IssueService -> " +
                        "No se ha encontrado Issue toDTO");
                return Optional.empty();
            }
        } else {
            System.out.println("IssueService -> " +
                    "No se ha encontrado Issue fromDTO");
            return Optional.empty();
        }
    }
    /**
     * Coger Issues abiertas por proyectoID
     * @author Dylan Hurtado y Javier González
     * @version 02/09/21 - 1.0
     */
    public List<Issue> getAllAbiertasByProyecto(String idProyecto) throws SQLException {
        RepoIssue repo = new RepoIssue();
        return repo.getAllAbiertasByProyecto(idProyecto).orElseThrow(()->(new SQLException("IssueService -> Error al encontrar las issues abiertas por idProyecto")));
    }
}
