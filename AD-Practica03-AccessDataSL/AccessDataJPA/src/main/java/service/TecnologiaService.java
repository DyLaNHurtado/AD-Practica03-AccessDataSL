package service;

import dto.TecnologiaDTO;
import mapper.TecnologiaMapper;
import model.Tecnologia;
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
        if (this.getById(id).isPresent()) {
            return mapper.toDTO(this.getById(id).get());
        }
        System.out.println("TecnologiaService -> " +
                "No se ha encontrado el Tecnologia by id");
        return Optional.empty();
    }

    public Optional<TecnologiaDTO> postTecnologia(TecnologiaDTO tecnologiaDTO) throws SQLException {
        if (mapper.fromDTO(tecnologiaDTO).isPresent()) {
            Optional<Tecnologia> res = this.save(mapper.fromDTO(tecnologiaDTO).get());
            if (res.isPresent()) {
                return mapper.toDTO(res.get());
            } else {
                System.out.println("TecnologiaService -> " +
                        "No se ha encontrado Tecnologia toDTO");
                return Optional.empty();
            }
        } else {
            System.out.println("TecnologiaService -> " +
                    "No se ha encontrado Tecnologia fromDTO");
            return Optional.empty();
        }
    }

    public Optional<TecnologiaDTO> updateTecnologia(TecnologiaDTO tecnologiaDTO) throws SQLException {
        if (mapper.fromDTO(tecnologiaDTO).isPresent()) {
            Optional<Tecnologia> res = this.update(mapper.fromDTO(tecnologiaDTO).get());
            if (res.isPresent()) {
                return mapper.toDTO(res.get());
            } else {
                System.out.println("TecnologiaService -> " +
                        "No se ha encontrado Tecnologia toDTO");
                return Optional.empty();
            }
        } else {
            System.out.println("TecnologiaService -> " +
                    "No se ha encontrado Tecnologia fromDTO");
            return Optional.empty();
        }
    }

    public Optional<TecnologiaDTO> deleteTecnologia(TecnologiaDTO tecnologiaDTO) throws SQLException {
        if (mapper.fromDTO(tecnologiaDTO).isPresent()) {
            Optional<Tecnologia> res = this.delete(mapper.fromDTO(tecnologiaDTO).get());
            if (res.isPresent()) {
                return mapper.toDTO(res.get());
            } else {
                System.out.println("TecnologiaService -> " +
                        "No se ha encontrado Tecnologia toDTO");
                return Optional.empty();
            }
        } else {
            System.out.println("TecnologiaService -> " +
                    "No se ha encontrado Tecnologia fromDTO");
            return Optional.empty();
        }
    }

}
