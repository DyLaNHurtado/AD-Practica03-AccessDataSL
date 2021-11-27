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

    public Optional<List<Optional<RepositorioDTO>>> getAllRepositorios() throws SQLException {
        return mapper.toDTO(this.getAll());
    }

    public Optional<RepositorioDTO> getRepositorioById(String id) throws SQLException {
        return mapper.toDTO(this.getById(id).get());
    }

    public Optional<RepositorioDTO> postRepositorio(RepositorioDTO repositorioDTO) throws SQLException {
        Optional<Repositorio> res = this.save(mapper.fromDTO(repositorioDTO).get());
        return mapper.toDTO(res.get());
    }

    public Optional<RepositorioDTO> updateRepositorio(RepositorioDTO repositorioDTO) throws SQLException {
        Optional<Repositorio> res = this.update(mapper.fromDTO(repositorioDTO).get());
        return mapper.toDTO(res.get());
    }

    public Optional<RepositorioDTO> deleteRepositorio(RepositorioDTO repositorioDTO) throws SQLException {
        Optional<Repositorio> res = this.delete(mapper.fromDTO(repositorioDTO).get());
        return mapper.toDTO(res.get());
    }
}
