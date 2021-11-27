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

    public Optional<List<Optional<IssueDTO>>> getAllIssues() throws SQLException {
        return mapper.toDTO(this.getAll());
    }

    public Optional<IssueDTO> getIssueById(String id) throws SQLException {
        return mapper.toDTO(this.getById(id).get());
    }

    public Optional<IssueDTO> postIssue(IssueDTO issueDTO) throws SQLException {
        Optional<Issue> res = this.save(mapper.fromDTO(issueDTO).get());
        return mapper.toDTO(res.get());
    }

    public Optional<IssueDTO> updateIssue(IssueDTO issueDTO) throws SQLException {
        Optional<Issue> res = this.update(mapper.fromDTO(issueDTO).get());
        return mapper.toDTO(res.get());
    }

    public Optional<IssueDTO> deleteIssue(IssueDTO issueDTO) throws SQLException {
        Optional<Issue> res = this.delete(mapper.fromDTO(issueDTO).get());
        return mapper.toDTO(res.get());
    }
}
