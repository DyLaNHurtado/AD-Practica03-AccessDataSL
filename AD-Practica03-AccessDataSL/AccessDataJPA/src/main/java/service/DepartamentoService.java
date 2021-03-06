package service;

import dto.DepartamentoDTO;
import mapper.DepartamentoMapper;
import dao.Departamento;
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
        if (this.getById(id).isPresent()) {
            return mapper.toDTO(this.getById(id).get());
        }
        System.out.println("DepartamentoService-> " +
                "No se ha encontrado el Departamento by id");
        return Optional.empty();
    }

    public Optional<DepartamentoDTO> postDepartamento(DepartamentoDTO departamentoDTO) throws SQLException {
        if (mapper.fromDTO(departamentoDTO).isPresent()) {
            Optional<Departamento> res = this.save(mapper.fromDTO(departamentoDTO).get());
            if (res.isPresent()) {
                return mapper.toDTO(res.get());
            } else {
                System.out.println("DepartamentoService -> " +
                        "No se ha encontrado Departamento toDTO");
                return Optional.empty();
            }
        } else {
            System.out.println("DepartamentoService -> " +
                    "No se ha encontrado Departamento fromDTO");
            return Optional.empty();
        }
    }

    public Optional<DepartamentoDTO> updateDepartamento(DepartamentoDTO departamentoDTO) throws SQLException {
        if (mapper.fromDTO(departamentoDTO).isPresent()) {
            Optional<Departamento> res = this.update(mapper.fromDTO(departamentoDTO).get());
            if (res.isPresent()) {
                return mapper.toDTO(res.get());
            } else {
                System.out.println("DepartamentoService -> " +
                        "No se ha encontrado Departamento toDTO");
                return Optional.empty();
            }
        } else {
            System.out.println("DepartamentoService -> " +
                    "No se ha encontrado Departamento fromDTO");
            return Optional.empty();
        }
    }

    public Optional<DepartamentoDTO> deleteDepartamento(DepartamentoDTO departamentoDTO) throws SQLException {
        if (mapper.fromDTO(departamentoDTO).isPresent()) {
            Optional<Departamento> res = this.delete(mapper.fromDTO(departamentoDTO).get());
            if (res.isPresent()) {
                return mapper.toDTO(res.get());
            } else {
                System.out.println("DepartamentoService -> " +
                        "No se ha encontrado Departamento toDTO");
                return Optional.empty();
            }
        } else {
            System.out.println("DepartamentoService -> " +
                    "No se ha encontrado Departamento fromDTO");
            return Optional.empty();
        }
    }
    public List<Object> getDepartamentoFullInfo(String id) throws SQLException {
        RepoDepartamento repo = new RepoDepartamento();
        return repo.getDepartamentoInfo(id).orElseThrow(()->(new SQLException("Error al encontrar informacion completa de un departamento")));
    }

}