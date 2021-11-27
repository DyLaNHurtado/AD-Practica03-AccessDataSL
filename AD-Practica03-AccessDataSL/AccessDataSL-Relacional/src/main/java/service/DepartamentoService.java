package service;

import dto.CommitDTO;
import dto.DepartamentoDTO;
import jdk.jfr.Category;
import mapper.CommitMapper;
import mapper.DepartamentoMapper;
import model.Commit;
import model.Departamento;
import repository.RepoCommit;
import repository.RepoDepartamento;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class DepartamentoService extends BaseService<Departamento, String, RepoDepartamento> {

    DepartamentoMapper mapper = new DepartamentoMapper();

    // Inyección de dependencias en el constructor. El servicio necesita este repositorio
    public DepartamentoService(RepoDepartamento repository) {
        super(repository);
    }

    public Optional<List<Optional<DepartamentoDTO>>> getAllDepartamentos() throws SQLException {
        return mapper.toDTO(this.getAll());
    }

    public Optional<DepartamentoDTO> getDepartamentoById(String id) throws SQLException {
        return mapper.toDTO(this.getById(id).get());
    }

    public Optional<DepartamentoDTO> postDepartamento(DepartamentoDTO departamentoDTO) throws SQLException {
        Optional<Departamento> res = this.save(mapper.fromDTO(departamentoDTO).get());
        return mapper.toDTO(res.get());
    }

    public Optional<DepartamentoDTO> updateDepartamento(DepartamentoDTO departamentoDTO) throws SQLException {
        Optional<Departamento> res = this.update(mapper.fromDTO(departamentoDTO).get());
        return mapper.toDTO(res.get());
    }

    public Optional<DepartamentoDTO> deleteDepartamento(DepartamentoDTO departamentoDTO) throws SQLException {
        Optional<Departamento> res = this.delete(mapper.fromDTO(departamentoDTO).get());
        return mapper.toDTO(res.get());
    }
}
