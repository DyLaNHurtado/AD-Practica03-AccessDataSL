package service;

import dto.CommitDTO;
import mapper.CommitMapper;
import model.Commit;
import repository.RepoCommit;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class CommitService extends BaseService<Commit, String, RepoCommit> {

    CommitMapper mapper = new CommitMapper();

    // Inyección de dependencias en el constructor. El servicio necesita este repositorio
    public CommitService(RepoCommit repository) {
        super(repository);
    }

    public Optional<List<Optional<CommitDTO>>> getAllCommits() throws SQLException {
        return mapper.toDTO(this.getAll());
    }

    public Optional<CommitDTO> getCommitById(String id) throws SQLException {
        return mapper.toDTO(this.getById(id).get());
    }

    public Optional<CommitDTO> postCommit(CommitDTO commitDTO) throws SQLException {
        Optional<Commit> res = this.save(mapper.fromDTO(commitDTO).get());
        return mapper.toDTO(res.get());
    }

    public Optional<CommitDTO> updateCommit(CommitDTO commitDTO) throws SQLException {
        Optional<Commit> res = this.update(mapper.fromDTO(commitDTO).get());
        return mapper.toDTO(res.get());
    }

    public Optional<CommitDTO> deleteCommit(CommitDTO commitDTO) throws SQLException {
        Optional<Commit> res = this.delete(mapper.fromDTO(commitDTO).get());
        return mapper.toDTO(res.get());
    }
}
