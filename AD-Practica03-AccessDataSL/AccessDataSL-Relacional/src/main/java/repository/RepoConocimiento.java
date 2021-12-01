package repository;

import database.DataBaseController;
import model.Conocimiento;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class RepoConocimiento implements CrudRepository<Conocimiento, String> {
    @Override
    public Optional<List<Conocimiento>> getAll() throws SQLException {
        System.out.println("Obteniendo todos los conocimientos");
        String query = "SELECT * FROM conocimiento";
        DataBaseController db = DataBaseController.getInstance();
        ArrayList<Conocimiento> list = null;
        db.open();
        ResultSet result = db.select(query).orElseThrow(() -> new SQLException("Error al consultar conocimientos"));
        list = new ArrayList<>();
        while (result.next()) {
            list.add(
                    new Conocimiento(
                            result.getString("idConocimiento"),
                            result.getString("idProgramador"),
                            List.of(result.getString("tecnologias").split(";"))
                    ));
        }
        db.close();
        return Optional.of(list);
    }

    @Override
    public Optional<Conocimiento> getById(String id) throws SQLException {
        System.out.println("Obteniendo conocimiento con id: " + id);
        String query = "SELECT * FROM conocimiento WHERE idConocimiento = ?";
        DataBaseController db = DataBaseController.getInstance();
        Conocimiento conocimiento = null;
        db.open();
        ResultSet result = db.select(query, id).orElseThrow(() -> new SQLException("Error al consultar conocimiento con ID " + id));
        if (result.first()) {
            conocimiento = new Conocimiento(
                    result.getString("idConocimiento"),
                    result.getString("idProgramador"),
                    List.of(result.getString("tecnologias").split(";"))
            );
        }
        db.close();
        return Optional.ofNullable(conocimiento);
    }

    @Override
    public Optional<Conocimiento> save(Conocimiento conocimiento) throws SQLException {

        System.out.println("Insertando conocimiento");
        String query = "INSERT INTO conocimiento VALUES (?, ?, ?)";
        DataBaseController db = DataBaseController.getInstance();
        db.open();
        db.insert(query, UUID.randomUUID().toString(),
                        conocimiento.getIdProgramador(),
                        String.join(";", conocimiento.getTecnologias()))
                .orElseThrow(() -> new SQLException("Error insertar conocimiento"));
        return Optional.of(conocimiento);
    }

    @Override
    public Optional<Conocimiento> update(Conocimiento conocimiento) throws SQLException {

        System.out.println("Actualizando conocimiento con id: " + conocimiento.getIdConocimiento());
        String query = "UPDATE conocimiento SET idConocimiento= ?, idProgramador = ?, tecnologias = ? WHERE idConocimiento = ?";
        DataBaseController db = DataBaseController.getInstance();
        db.open();
        db.update(query, conocimiento.getIdConocimiento(),
                conocimiento.getIdProgramador(),
                String.join(";", conocimiento.getTecnologias()),conocimiento.getIdConocimiento());
        db.close();

        return Optional.of(conocimiento);
    }

    @Override
    public Optional<Conocimiento> delete(Conocimiento conocimiento) throws SQLException {
        System.out.println("Eliminando conocimiento con id: " + conocimiento.getIdConocimiento());
        String query = "DELETE FROM conocimiento WHERE idConocimiento = ?";
        DataBaseController db = DataBaseController.getInstance();
        db.open();
        db.delete(query, conocimiento.getIdConocimiento());
        db.close();

        return Optional.of(conocimiento);
    }
}
