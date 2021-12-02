package repository;

import model.Programador;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface CrudRepository<T, ID> {

    // Operaciones CRUD
    /**
     * Operacion coger TODOS
     * @author Dylan Hurtado y Javier González
     * @version 02/09/21 - 1.0
     */
    Optional<List<T>> getAll() throws SQLException;
    /**
     * Operacion coger TODOS por ID
     * @author Dylan Hurtado y Javier González
     * @version 02/09/21 - 1.0
     */
    Optional<T> getById(ID id) throws SQLException;
    /**
     * Operacion coget Guardar
     * @author Dylan Hurtado y Javier González
     * @version 02/09/21 - 1.0
     */
    Optional<T> save(T t) throws SQLException;
    /**
     * Operacion Update
     * @author Dylan Hurtado y Javier González
     * @version 02/09/21 - 1.0
     */
    Optional<T> update(T t) throws SQLException;
    /**
     * Operacion Borrar
     * @author Dylan Hurtado y Javier González
     * @version 02/09/21 - 1.0
     */
    Optional<T> delete(T t) throws SQLException;


}