package repository;

import dao.Programador;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface CrudRepository<T, ID> {

    // Operaciones CRUD

    // Obtiene todos
    Optional<List<T>> getAll() throws SQLException;

    // Obtiene por ID
    Optional<T> getById(ID id) throws SQLException;

    // Salva
    Optional<T> save(T t) throws SQLException;

    // Actualiza
    Optional<T> update(T t) throws SQLException;

    // Elimina
    Optional<T> delete(T t) throws SQLException;


}