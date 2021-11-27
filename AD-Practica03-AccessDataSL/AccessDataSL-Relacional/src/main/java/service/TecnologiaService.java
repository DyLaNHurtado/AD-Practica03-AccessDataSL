package service;

import dto.CommitDTO;
import dto.TecnologiaDTO;
import jdk.jfr.Category;
import mapper.CommitMapper;
import mapper.TecnologiaMapper;
import model.Commit;
import model.Tecnologia;
import repository.RepoCommit;
import repository.RepoTecnologia;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class TecnologiaService extends BaseService<Tecnologia, String, RepoTecnologia> {

    TecnologiaMapper mapper = new TecnologiaMapper();

    // Inyección de dependencias en el constructor. El servicio necesita este repositorio
    public TecnologiaService(RepoTecnologia repository) {
        super(repository);
    }

    public Optional<List<Optional<TecnologiaDTO>>> getAllTecnologias() throws SQLException {
        return mapper.toDTO(this.getAll());
    }

    public Optional<TecnologiaDTO> getTecnologiaById(String id) throws SQLException {
        return mapper.toDTO(this.getById(id).get());
    }

    public Optional<TecnologiaDTO> postTecnologia(TecnologiaDTO tecnologiaDTO) throws SQLException {
        Optional<Tecnologia> res = this.save(mapper.fromDTO(tecnologiaDTO).get());
        return mapper.toDTO(res.get());
    }

    public Optional<TecnologiaDTO> updateTecnologia(TecnologiaDTO tecnologiaDTO) throws SQLException {
        Optional<Tecnologia> res = this.update(mapper.fromDTO(tecnologiaDTO).get());
        return mapper.toDTO(res.get());
    }

    public Optional<TecnologiaDTO> deleteTecnologia(TecnologiaDTO tecnologiaDTO) throws SQLException {
        Optional<Tecnologia> res = this.delete(mapper.fromDTO(tecnologiaDTO).get());
        return mapper.toDTO(res.get());
    }

}
