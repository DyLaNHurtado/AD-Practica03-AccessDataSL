package repository;

import database.DataBaseController;
import model.Departamento;
import model.Proyecto;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class RepoProyecto implements CrudRepository<Proyecto, String>{
    @Override
    public Optional<List<Proyecto>> getAll() throws SQLException {
        System.out.println("Obteniendo todos los proyecto");
        String query = "SELECT * FROM proyecto";
        DataBaseController db = DataBaseController.getInstance();
        ArrayList<Proyecto> list = null;
        db.open();
        ResultSet result = db.select(query).orElseThrow(() -> new SQLException("Error al consultar proyecto"));
        list = new ArrayList<>();
        while (result.next()) {
            list.add(
                    new Proyecto(
                            result.getString("idProyecto"),
                            result.getString("nombre"),
                            result.getString("idJefe"),
                            result.getDouble("presupuesto"),
                            result.getDate("fechaInicio"),
                            result.getDate("fechaFin"),
                            List.of(result.getString("tecnologias").split(";")),
                            result.getString("idRepositorio")
                    ));
        }
        db.close();
        return Optional.of(list);
    }

    @Override
    public Optional<Proyecto> getById(String id) throws SQLException {
        System.out.println("Obteniendo proyecto con id: " + id);
        String query = "SELECT * FROM proyecto WHERE idProyecto = ?";
        DataBaseController db = DataBaseController.getInstance();
        Proyecto proyecto = null;
        db.open();
        ResultSet result = db.select(query, id).orElseThrow(() -> new SQLException("Error al consultar departamento con ID " + id));
        if (result.first()) {
            proyecto = new Proyecto(
                    result.getString("idProyecto"),
                    result.getString("nombre"),
                    result.getString("idJefe"),
                    result.getDouble("presupuesto"),
                    result.getDate("fechaInicio"),
                    result.getDate("fechaFin"),
                    List.of(result.getString("tecnologias").split(";")),
                    result.getString("idRepositorio")
            );}
        db.close();
        return Optional.ofNullable(proyecto);
    }

    @Override
    public Optional<Proyecto> save(Proyecto proyecto) throws SQLException {
        System.out.println("Insertando proyecto");
        String query = "INSERT INTO proyecto VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        DataBaseController db = DataBaseController.getInstance();
        db.open();
        db.insert(query, UUID.randomUUID().toString(),
                        proyecto.getNombre(), proyecto.getIdJefe(), proyecto.getPresupuesto(),
                        proyecto.getFechaInicio(),proyecto.getFechaFin(),
                        String.join(";", proyecto.getTecnologias()),
                        proyecto.getIdRepositorio())
                .orElseThrow(() -> new SQLException("Error insertar proyecto"));


        return Optional.of(proyecto);
    }

    @Override
    public Optional<Proyecto> update(Proyecto proyecto) throws SQLException {
        System.out.println("Actualizando proyecto con id: " + proyecto.getIdProyecto());
        String query = "UPDATE proyecto SET idProyecto = ?, nombre = ?, idJefe = ?, presupuesto = ?, fechaInicio = ?, fechaFin = ? ,tecnologias = ?, idRepositorio = ? WHERE idProyecto = ?";
        DataBaseController db = DataBaseController.getInstance();
        db.open();
        db.update(query, proyecto.getIdProyecto(),
                proyecto.getNombre(), proyecto.getIdJefe(), proyecto.getPresupuesto(),
                proyecto.getFechaInicio(),proyecto.getFechaFin(),
                String.join(";", proyecto.getTecnologias()),
                proyecto.getIdRepositorio());
        db.close();

        return Optional.of(proyecto);
    }

    @Override
    public Optional<Proyecto> delete(Proyecto proyecto) throws SQLException {
        System.out.println("Eliminando proyecto con id: " + proyecto.getIdProyecto());
        String query = "DELETE FROM proyecto WHERE idProyecto = ?";
        DataBaseController db = DataBaseController.getInstance();
        db.open();
        db.delete(query, proyecto.getIdProyecto());
        db.close();

        return Optional.of(proyecto);
    }
}
