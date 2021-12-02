package service;

import lombok.RequiredArgsConstructor;
import repository.CrudRepository;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public abstract class BaseService<T, ID, R extends CrudRepository<T, ID>> {

    protected final R repository;

    // Obtiene todos
    public Optional<List<T>> getAll() throws SQLException {
        return repository.getAll();
    }

    // Obtiene por ID
    public Optional<T> getById(ID id) throws SQLException {
        return repository.getById(id);
    }

    // Salva
    public Optional<T> save(T t) throws SQLException {
        return repository.save(t);
    }

    // Actualiza
    public Optional<T> update(T t) throws SQLException {
        return repository.update(t);
    }

    // Elimina
    public Optional<T> delete(T t) throws SQLException {
        return repository.delete(t);
    }


}
