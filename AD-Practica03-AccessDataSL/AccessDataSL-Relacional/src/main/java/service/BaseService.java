package service;

import lombok.RequiredArgsConstructor;
import repository.CrudRepository;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public abstract class BaseService<T, ID, R extends CrudRepository<T, ID>> {

    protected final R repository;
    /**
     * Metodo obtener todos
     * @author Dylan Hurtado y Javier González
     * @version 02/09/21 - 1.0
     */
    public Optional<List<T>> getAll() throws SQLException {
        return repository.getAll();
    }

    /**
     * Metodo obtener por ID
     * @author Dylan Hurtado y Javier González
     * @version 02/09/21 - 1.0
     */
    public Optional<T> getById(ID id) throws SQLException {
        return repository.getById(id);
    }

    /**
     * Metodo guardar
     * @author Dylan Hurtado y Javier González
     * @version 02/09/21 - 1.0
     */
    public Optional<T> save(T t) throws SQLException {
        return repository.save(t);
    }

    /**
     * Metodo update
     * @author Dylan Hurtado y Javier González
     * @version 02/09/21 - 1.0
     */
    public Optional<T> update(T t) throws SQLException {
        return repository.update(t);
    }

    /**
     * Metodo eliminar
     * @author Dylan Hurtado y Javier González
     * @version 02/09/21 - 1.0
     */
    public Optional<T> delete(T t) throws SQLException {
        return repository.delete(t);
    }


}
