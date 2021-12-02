package repository;

import database.DataBaseController;
import model.Departamento;
import model.Proyecto;
import model.Tecnologia;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class RepoTecnologia implements CrudRepository<Tecnologia, String> {
    /**
     * Coger todas las info tecnologias
     * @author Dylan Hurtado y Javier González
     * @version 02/09/21 - 1.0
     */
    @Override
    public Optional<List<Tecnologia>> getAll() throws SQLException {
        System.out.println("Obteniendo todos los tecnologia");
        String query = "SELECT * FROM tecnologia";
        DataBaseController db = DataBaseController.getInstance();
        ArrayList<Tecnologia> list = null;
        db.open();
        ResultSet result = db.select(query).orElseThrow(() -> new SQLException("Error al consultar tecnologias"));
        list = new ArrayList<>();
        while (result.next()) {
            list.add(
                    new Tecnologia(
                            result.getString("idTecnologia"),
                            result.getString("nombre")

                    ));
        }
        db.close();
        return Optional.of(list);
    }
    /**
     * Coger tecnologias por id
     * @author Dylan Hurtado y Javier González
     * @version 02/09/21 - 1.0
     */
    @Override
    public Optional<Tecnologia> getById(String id) throws SQLException {
        System.out.println("Obteniendo tecnologia con id: " + id);
        String query = "SELECT * FROM tecnologia WHERE idTecnologia = ?";
        DataBaseController db = DataBaseController.getInstance();
        Tecnologia tecnologia = null;
        db.open();
        ResultSet result = db.select(query, id).orElseThrow(() -> new SQLException("Error al consultar departamento con ID " + id));
        if (result.first()) {
            tecnologia = new Tecnologia(
                    result.getString("idTecnologia"),
                    result.getString("nombre")
            );}
        db.close();
        return Optional.ofNullable(tecnologia);
    }
    /**
     * Guardar tecnologia
     * @author Dylan Hurtado y Javier González
     * @version 02/09/21 - 1.0
     */
    @Override
    public Optional<Tecnologia> save(Tecnologia tecnologia) throws SQLException {
        System.out.println("Insertando tecnologia");
        String query = "INSERT INTO tecnologia VALUES (?, ?)";
        DataBaseController db = DataBaseController.getInstance();
        db.open();
        db.insert(query, UUID.randomUUID().toString(),
                        tecnologia.getNombre())
                .orElseThrow(() -> new SQLException("Error insertar tecnologia"));


        return Optional.of(tecnologia);
    }
    /**
     * Updatear tecnologia
     * @author Dylan Hurtado y Javier González
     * @version 02/09/21 - 1.0
     */
    @Override
    public Optional<Tecnologia> update(Tecnologia tecnologia) throws SQLException {
        System.out.println("Actualizando tecnologia con id: " + tecnologia.getIdTecnologia());
        String query = "UPDATE tecnologia SET idTecnologia = ?, nombre = ? WHERE idTecnologia = ?";
        DataBaseController db = DataBaseController.getInstance();
        db.open();
        db.update(query, tecnologia.getIdTecnologia(),
                tecnologia.getNombre(),tecnologia.getIdTecnologia());
        db.close();

        return Optional.of(tecnologia);
    }
    /**
     * Borrar tecnologia
     * @author Dylan Hurtado y Javier González
     * @version 02/09/21 - 1.0
     */
    @Override
    public Optional<Tecnologia> delete(Tecnologia tecnologia) throws SQLException {
        System.out.println("Eliminando tecnologia con id: " + tecnologia.getIdTecnologia());
        String query = "DELETE FROM tecnologia WHERE idTecnologia = ?";
        DataBaseController db = DataBaseController.getInstance();
        db.open();
        db.delete(query, tecnologia.getIdTecnologia());
        db.close();

        return Optional.of(tecnologia);
    }
}
