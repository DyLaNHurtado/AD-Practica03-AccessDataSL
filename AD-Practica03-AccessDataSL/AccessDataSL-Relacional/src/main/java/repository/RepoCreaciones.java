package repository;

import database.DataBaseController;
import model.Conocimiento;
import model.Creaciones;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class RepoCreaciones implements CrudRepository<Creaciones, String> {
    @Override
    public Optional<List<Creaciones>> getAll() throws SQLException {
        System.out.println("Obteniendo todas las creaciones");
        String query = "SELECT * FROM creaciones";
        DataBaseController db = DataBaseController.getInstance();
        ArrayList<Creaciones> list = null;
        db.open();
        ResultSet result = db.select(query).orElseThrow(() -> new SQLException("Error al consultar creaciones"));
        list = new ArrayList<>();
        while (result.next()) {
            list.add(
                    new Creaciones(
                            result.getString("idCreaciones"),
                            result.getString("idIssue"),
                            result.getString("idProgramador")
                    ));
        }
        db.close();
        return Optional.of(list);
    }

    @Override
    public Optional<Creaciones> getById(String id) throws SQLException {
        System.out.println("Obteniendo creaciones con id: " + id);
        String query = "SELECT * FROM creaciones WHERE idCreaciones = ?";
        DataBaseController db = DataBaseController.getInstance();
        Creaciones creaciones = null;
        db.open();
        ResultSet result = db.select(query, id).orElseThrow(() -> new SQLException("Error al consultar creaciones con ID " + id));
        if (result.first()) {
            creaciones = new Creaciones(
                    result.getString("idCreaciones"),
                    result.getString("idIssue"),
                    result.getString("idProgramador")
            );
        }
        db.close();
        return Optional.ofNullable(creaciones);
    }

    @Override
    public Optional<Creaciones> save(Creaciones creaciones) throws SQLException {

        System.out.println("Insertando creaciones");
        String query = "INSERT INTO creaciones VALUES (?, ?, ?)";
        DataBaseController db = DataBaseController.getInstance();
        db.open();
        db.insert(query, UUID.randomUUID().toString(),
                        creaciones.getIdIssue(),
                        creaciones.getIdProgramador())
                .orElseThrow(() -> new SQLException("Error insertar creaciones"));
        return Optional.of(creaciones);
    }

    @Override
    public Optional<Creaciones> update(Creaciones creaciones) throws SQLException {

        System.out.println("Actualizando creaciones con id: " + creaciones.getIdCreaciones());
        String query = "UPDATE creaciones SET idCreaciones= ?, idIssue = ?, idProgramador = ? WHERE idCreaciones = ?";
        DataBaseController db = DataBaseController.getInstance();
        db.open();
        db.update(query, creaciones.getIdCreaciones(),
                creaciones.getIdIssue(),
                creaciones.getIdProgramador(),creaciones.getIdCreaciones());
        db.close();

        return Optional.of(creaciones);
    }

    @Override
    public Optional<Creaciones> delete(Creaciones creaciones) throws SQLException {
        System.out.println("Eliminando creaciones con id: " + creaciones.getIdCreaciones());
        String query = "DELETE FROM creaciones WHERE idCreaciones = ?";
        DataBaseController db = DataBaseController.getInstance();
        db.open();
        db.delete(query, creaciones.getIdCreaciones());
        db.close();

        return Optional.of(creaciones);
    }
}
