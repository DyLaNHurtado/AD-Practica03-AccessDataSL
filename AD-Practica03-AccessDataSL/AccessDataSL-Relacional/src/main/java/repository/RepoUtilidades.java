package repository;

import database.DataBaseController;
import model.Utilidades;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class RepoUtilidades implements CrudRepository<Utilidades, String> {
    @Override
    public Optional<List<Utilidades>> getAll() throws SQLException {
        System.out.println("Obteniendo todas las utilidades");
        String query = "SELECT * FROM utilidades";
        DataBaseController db = DataBaseController.getInstance();
        ArrayList<Utilidades> list ;
        db.open();
        ResultSet result = db.select(query).orElseThrow(() -> new SQLException("Error al consultar utilidades"));
        list = new ArrayList<>();
        while (result.next()) {
            list.add(
                    new Utilidades(
                            result.getString("idParticipaciones"),
                            result.getString("idProyecto"),
                            List.of(result.getString("programadores").split(";"))));
        }
        db.close();
        return Optional.of(list);
    }

    @Override
    public Optional<Utilidades> getById(String id) throws SQLException {
        System.out.println("Obteniendo utilidades con id: " + id);
        String query = "SELECT * FROM utilidades WHERE idUtilidades = ?";
        DataBaseController db = DataBaseController.getInstance();
        Utilidades utilidades = null;
        db.open();
        ResultSet result = db.select(query, id).orElseThrow(() -> new SQLException("Error al consultar utilidades con ID " + id));
        if (result.first()) {
            utilidades = new Utilidades(
                    result.getString("idUtilidades"),
                    result.getString("idProyecto"),
                    List.of(result.getString("tecnologias").split(";"))
            );
        }
        db.close();
        return Optional.ofNullable(utilidades);
    }

    @Override
    public Optional<Utilidades> save(Utilidades utilidades) throws SQLException {

        System.out.println("Insertando utilidades");
        String query = "INSERT INTO utilidades VALUES (?, ?, ?)";
        DataBaseController db = DataBaseController.getInstance();
        db.open();
        db.insert(query, UUID.randomUUID().toString(),
                        utilidades.getIdProyecto(),
                        String.join(";", utilidades.getTecnologias()))
                .orElseThrow(() -> new SQLException("Error insertar utilidades"));
        return Optional.of(utilidades);
    }

    @Override
    public Optional<Utilidades> update(Utilidades utilidades) throws SQLException {

        System.out.println("Actualizando utilidades con id: " + utilidades.getIdUtilidades());
        String query = "UPDATE utilidades SET idUtilidades= ?, idProyecto = ?, tecnologias = ? WHERE idUtilidades = ?";
        DataBaseController db = DataBaseController.getInstance();
        db.open();
        db.update(query, utilidades.getIdUtilidades(),
                utilidades.getIdProyecto(),
                String.join(";", utilidades.getTecnologias()),
                utilidades.getIdUtilidades());
        db.close();

        return Optional.of(utilidades);
    }

    @Override
    public Optional<Utilidades> delete(Utilidades utilidades) throws SQLException {
        System.out.println("Eliminando utilidades con id: " + utilidades.getIdUtilidades());
        String query = "DELETE FROM utilidades WHERE idUtilidades = ?";
        DataBaseController db = DataBaseController.getInstance();
        db.open();
        db.delete(query, utilidades.getIdUtilidades());
        db.close();

        return Optional.of(utilidades);
    }
}
