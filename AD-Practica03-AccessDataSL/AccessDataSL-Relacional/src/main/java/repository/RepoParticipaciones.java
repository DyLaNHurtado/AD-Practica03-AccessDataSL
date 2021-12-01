package repository;

import database.DataBaseController;
import model.Participaciones;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class RepoParticipaciones implements CrudRepository<Participaciones, String> {
    @Override
    public Optional<List<Participaciones>> getAll() throws SQLException {
        System.out.println("Obteniendo todas las participaciones");
        String query = "SELECT * FROM participaciones";
        DataBaseController db = DataBaseController.getInstance();
        ArrayList<Participaciones> list = null;
        db.open();
        ResultSet result = db.select(query).orElseThrow(() -> new SQLException("Error al consultar creaciones"));
        list = new ArrayList<>();
        while (result.next()) {
            list.add(
                    new Participaciones(
                            result.getString("idParticipaciones"),
                            result.getString("idProyecto"),
                            List.of(result.getString("programadores").split(";"))));
        }
        db.close();
        return Optional.of(list);
    }

    @Override
    public Optional<Participaciones> getById(String id) throws SQLException {
        System.out.println("Obteniendo participaciones con id: " + id);
        String query = "SELECT * FROM participaciones WHERE idParticipaciones = ?";
        DataBaseController db = DataBaseController.getInstance();
        Participaciones participaciones = null;
        db.open();
        ResultSet result = db.select(query, id).orElseThrow(() -> new SQLException("Error al consultar creaciones con ID " + id));
        if (result.first()) {
            participaciones = new Participaciones(
                    result.getString("idParticipaciones"),
                    result.getString("idProyecto"),
                    List.of(result.getString("programadores").split(";"))
            );
        }
        db.close();
        return Optional.ofNullable(participaciones);
    }

    @Override
    public Optional<Participaciones> save(Participaciones participaciones) throws SQLException {

        System.out.println("Insertando participaciones");
        String query = "INSERT INTO participaciones VALUES (?, ?, ?)";
        DataBaseController db = DataBaseController.getInstance();
        db.open();
        db.insert(query, UUID.randomUUID().toString(),
                        participaciones.getIdProyecto(),
                        String.join(";", participaciones.getProgramadores()))
                .orElseThrow(() -> new SQLException("Error insertar creaciones"));
        return Optional.of(participaciones);
    }

    @Override
    public Optional<Participaciones> update(Participaciones participaciones) throws SQLException {

        System.out.println("Actualizando participaciones con id: " + participaciones.getIdParticipaciones());
        String query = "UPDATE participaciones SET idParticipaciones= ?, idProyecto = ?, programadores = ? WHERE idParticipaciones = ?";
        DataBaseController db = DataBaseController.getInstance();
        db.open();
        db.update(query, participaciones.getIdParticipaciones(),
                participaciones.getIdProyecto(),
                String.join(";", participaciones.getProgramadores()),
                participaciones.getIdParticipaciones());
        db.close();

        return Optional.of(participaciones);
    }

    @Override
    public Optional<Participaciones> delete(Participaciones participaciones) throws SQLException {
        System.out.println("Eliminando participaciones con id: " + participaciones.getIdParticipaciones());
        String query = "DELETE FROM participaciones WHERE idParticipaciones = ?";
        DataBaseController db = DataBaseController.getInstance();
        db.open();
        db.delete(query, participaciones.getIdParticipaciones());
        db.close();

        return Optional.of(participaciones);
    }
}
