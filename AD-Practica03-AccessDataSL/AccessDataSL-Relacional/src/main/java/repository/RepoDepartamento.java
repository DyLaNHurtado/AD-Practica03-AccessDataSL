package repository;

import database.DataBaseController;
import model.Departamento;
import model.Programador;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class RepoDepartamento implements CrudRepository<Departamento, String> {
    @Override
    public Optional<List<Departamento>> getAll() throws SQLException {
        System.out.println("Obteniendo todos los departamentos");
        String query = "SELECT * FROM departamento";
        DataBaseController db = DataBaseController.getInstance();
        ArrayList<Departamento> list = null;
        db.open();
        ResultSet result = db.select(query).orElseThrow(() -> new SQLException("Error al consultar departamentos de programadores"));
        list = new ArrayList<>();
        while (result.next()) {
            list.add(
                    new Departamento(
                            result.getString("idDepartamento"),
                            result.getString("nombre"),
                            result.getString("idJefe"),
                            result.getDouble("presupuesto"),
                            List.of(result.getString("proyFinalizados").split(";")),
                            List.of(result.getString("proyDesarrollo").split(";")),
                            result.getDouble("presupuestoAnual"),
                            List.of(result.getString("historialJefes").split(";"))

                    ));
        }
        db.close();
        return Optional.of(list);
    }

    @Override
    public Optional<Departamento> getById(String id) throws SQLException {
        System.out.println("Obteniendo departamento con id: " + id);
        String query = "SELECT * FROM departamento WHERE idDepartamento = ?";
        DataBaseController db = DataBaseController.getInstance();
        Departamento departamento = null;
        db.open();
        ResultSet result = db.select(query, id).orElseThrow(() -> new SQLException("Error al consultar departamento con ID " + id));
        if (result.first()) {
            departamento = new Departamento(
                    result.getString("idDepartamento"),
                    result.getString("nombre"),
                    result.getString("idJefe"),
                    result.getDouble("presupuesto"),
                    List.of(result.getString("proyFinalizados").split(";")),
                    List.of(result.getString("proyDesarrollo").split(";")),
                    result.getDouble("presupuestoAnual"),
                    List.of(result.getString("historialJefes").split(";"))
            );}
        db.close();
        return Optional.ofNullable(departamento);
    }

    @Override
    public Optional<Departamento> save(Departamento departamento) throws SQLException {
        System.out.println("Insertando departamento");
        String query = "INSERT INTO departamento VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        DataBaseController db = DataBaseController.getInstance();
        db.open();
        db.insert(query, UUID.randomUUID().toString(),
                        departamento.getNombre(), departamento.getIdJefe(), departamento.getPresupuesto(),
                        String.join(";", departamento.getProyFinalizados()),
                        String.join(";", departamento.getProyDesarrollo()),
                        departamento.getPresupuestoAnual(),
                        String.join(";", departamento.getHistorialJefes()))
                .orElseThrow(() -> new SQLException("Error insertar departamento"));


        return Optional.of(departamento);
    }

    @Override
    public Optional<Departamento> update(Departamento departamento) throws SQLException {
        System.out.println("Actualizando departamento con id: " + departamento.getIdDepartamento());
        String query = "UPDATE departamento SET idDepartamento= ?, nombre = ?, idJefe = ?, presupuesto = ?, proyFinalizados = ?, proyDesarrollo = ? ,presupuestoAnual = ?, historialJefes = ? WHERE idDepartamento = ?";
        DataBaseController db = DataBaseController.getInstance();
        db.open();
        db.update(query, departamento.getIdDepartamento(),
                departamento.getNombre(), departamento.getIdJefe(), departamento.getPresupuesto(),
                String.join(";", departamento.getProyFinalizados()),
                String.join(";", departamento.getProyDesarrollo()),
                departamento.getPresupuestoAnual(),
                String.join(";", departamento.getHistorialJefes()));
        db.close();

        return Optional.of(departamento);
    }

    @Override
    public Optional<Departamento> delete(Departamento departamento) throws SQLException {
        System.out.println("Eliminando departamento con id: " + departamento.getIdDepartamento());
        String query = "DELETE FROM departamento WHERE idDepartamento = ?";
        DataBaseController db = DataBaseController.getInstance();
        db.open();
        db.delete(query, departamento.getIdDepartamento());
        db.close();

        return Optional.of(departamento);
    }
}
