package repository;

import database.DataBaseController;
import model.Departamento;
import model.Programador;
import model.Proyecto;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class RepoDepartamento implements CrudRepository<Departamento, String> {
    /**
     * Coger todos los departamentos
     * @author Dylan Hurtado y Javier González
     * @version 02/09/21 - 1.0
     */
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
                            List.of(result.getString("trabajadores")),
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
    /**
     * Coger los departamentos con una id
     * @author Dylan Hurtado y Javier González
     * @version 02/09/21 - 1.0
     */
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
                    List.of(result.getString("trabajadores")),
                    result.getDouble("presupuesto"),
                    List.of(result.getString("proyFinalizados").split(";")),
                    List.of(result.getString("proyDesarrollo").split(";")),
                    result.getDouble("presupuestoAnual"),
                    List.of(result.getString("historialJefes").split(";"))
            );
        }
        db.close();
        return Optional.ofNullable(departamento);
    }
    /**
     * Guardar un deoartamento
     * @author Dylan Hurtado y Javier González
     * @version 02/09/21 - 1.0
     */
    @Override
    public Optional<Departamento> save(Departamento departamento) throws SQLException {
        System.out.println("Insertando departamento");
        String query = "INSERT INTO departamento VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        DataBaseController db = DataBaseController.getInstance();
        db.open();
        db.insert(query, UUID.randomUUID().toString(),
                        departamento.getNombre(), departamento.getIdJefe(),
                        String.join(";", departamento.getTrabajadores()), departamento.getPresupuesto(),
                        String.join(";", departamento.getProyFinalizados()),
                        String.join(";", departamento.getProyDesarrollo()),
                        departamento.getPresupuestoAnual(),
                        String.join(";", departamento.getHistorialJefes()))
                .orElseThrow(() -> new SQLException("Error insertar departamento"));


        return Optional.of(departamento);
    }
    /**
     * Updatear un departamento
     * @author Dylan Hurtado y Javier González
     * @version 02/09/21 - 1.0
     */
    @Override
    public Optional<Departamento> update(Departamento departamento) throws SQLException {
        System.out.println("Actualizando departamento con id: " + departamento.getIdDepartamento());
        String query = "UPDATE departamento SET idDepartamento= ?, nombre = ?, idJefe = ?, presupuesto = ?, proyFinalizados = ?, proyDesarrollo = ? ,presupuestoAnual = ?, historialJefes = ? WHERE idDepartamento = ?";
        DataBaseController db = DataBaseController.getInstance();
        db.open();
        db.update(query, departamento.getIdDepartamento(),
                departamento.getNombre(), departamento.getIdJefe(),
                String.join(";", departamento.getTrabajadores()), departamento.getPresupuesto(),
                String.join(";", departamento.getProyFinalizados()),
                String.join(";", departamento.getProyDesarrollo()),
                departamento.getPresupuestoAnual(),
                String.join(";", departamento.getHistorialJefes()));
        db.close();

        return Optional.of(departamento);
    }
    /**
     * Borrar un departamento
     * @author Dylan Hurtado y Javier González
     * @version 02/09/21 - 1.0
     */
    @Override
    public Optional<Departamento> delete(Departamento departamento) throws SQLException {
        RepoProgramador repoProgramador = new RepoProgramador();
        RepoProyecto repoProyecto = new RepoProyecto();
        if (repoProgramador.getByIdDepartamento(departamento.getIdDepartamento()).isPresent()
                || repoProyecto.getByIdDepartamento(departamento.getIdDepartamento()).isPresent()) {
            //meter condiciones de si tienen una id departamento asignada para programador asi
            Programador programador = repoProgramador.getByIdDepartamento(departamento.getIdDepartamento()).get();
            Proyecto proyecto = repoProyecto.getByIdDepartamento(departamento.getIdDepartamento()).get();
            //cogemos su id actual de departamento
            // (el que vamos a borrar)
            programador.setIdDepartamento("");
            repoProgramador.update(programador);
            proyecto.setIdDepartamento("");
            repoProyecto.update(proyecto);
            //seteamos en (vacio) y lo updateamos

            System.out.println("Eliminando departamento con id: " + departamento.getIdDepartamento());
            String query = "DELETE FROM departamento WHERE idDepartamento = ?";
            DataBaseController db = DataBaseController.getInstance();
            db.open();
            db.delete(query, departamento.getIdDepartamento());
            db.close();
        }
        return Optional.of(departamento);
    }
    /**
     * Obtener departamento segun idJefe
     * @author Dylan Hurtado y Javier González
     * @version 02/09/21 - 1.0
     */
    public Optional<Departamento> getByIdJefe(String id) throws SQLException {
        System.out.println("Obteniendo departamento con idJefe: " + id);
        //buscar id con % delante y % detras para coger solo id ya que está separada con ;
        String query = "SELECT * FROM departamento WHERE idJefe = ? ";
        DataBaseController db = DataBaseController.getInstance();
        Departamento departamento = null;
        db.open();
        ResultSet result = db.select(query, id).orElseThrow(() -> new SQLException("Error al consultar departamento con ID " + id));
        if (result.first()) {
            departamento = new Departamento(
                    result.getString("idDepartamento"),
                    result.getString("nombre"),
                    result.getString("idJefe"),
                    List.of(result.getString("trabajadores")),
                    result.getDouble("presupuesto"),
                    List.of(result.getString("proyFinalizados").split(";")),
                    List.of(result.getString("proyDesarrollo").split(";")),
                    result.getDouble("presupuestoAnual"),
                    List.of(result.getString("historialJefes").split(";"))
            );
        }
        db.close();
        return Optional.ofNullable(departamento);
    }
    /**
     * Operacion 1:
     * Obtener de un departamento, los proyectos (información completa) y trabajadores asociados con sus datos completos
     * @author Dylan Hurtado y Javier González
     * @version 02/09/21 - 1.0
     */

    public Optional<List<Object>> getDepartamentoInfo(String id) throws SQLException {
        RepoProyecto repoProyecto = new RepoProyecto();
        RepoProgramador repoProgramador = new RepoProgramador();

        if (repoProyecto.getAllByIdDepartamento(id).isPresent()
                && repoProgramador.getAllByIdDepartamento(id).isPresent()
                && this.getById(id).isPresent()) {

            List<Proyecto> proyectos = repoProyecto.getAllByIdDepartamento(id).get();
            List<Programador> programadores = repoProgramador.getAllByIdDepartamento(id).get();

            Departamento departamento = this.getById(id).get();

            return Optional.of(List.of(departamento, proyectos, programadores));
        }

        System.out.println("No se ha encontrado departamento en getDepartamentoInfo");
        return Optional.empty();
    }

}
