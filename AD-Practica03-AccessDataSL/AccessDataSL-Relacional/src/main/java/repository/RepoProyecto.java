package repository;

import database.DataBaseController;
import model.Departamento;
import model.Programador;
import model.Proyecto;
import model.Repositorio;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import java.util.stream.Collectors;

public class RepoProyecto implements CrudRepository<Proyecto, String> {
    /**
     * Coger todos los proyectos
     * @author Dylan Hurtado y Javier González
     * @version 02/09/21 - 1.0
     */
    @Override
    public Optional<List<Proyecto>> getAll() throws SQLException {
        System.out.println("Obteniendo todos los proyecto");
        String query = "SELECT * FROM proyecto";
        DataBaseController db = DataBaseController.getInstance();
        ArrayList<Proyecto> list;
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
                            result.getDate("fechaInicio").toLocalDate(),
                            result.getDate("fechaFin").toLocalDate(),
                            List.of(result.getString("tecnologias").split(";")),
                            result.getString("idRepositorio"),
                            result.getString("idDepartamento")));
        }
        db.close();
        return Optional.of(list);
    }
    /**
     * Coger todos por id
     * @author Dylan Hurtado y Javier González
     * @version 02/09/21 - 1.0
     */
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
                    result.getDate("fechaInicio").toLocalDate(),
                    result.getDate("fechaFin").toLocalDate(),
                    List.of(result.getString("tecnologias").split(";")),
                    result.getString("idRepositorio"),
                    result.getString("idDepartamento")
            );}
        db.close();
        return Optional.ofNullable(proyecto);
    }
    /**
     * Guardar proyecto
     * @author Dylan Hurtado y Javier González
     * @version 02/09/21 - 1.0
     */
    @Override
    public Optional<Proyecto> save(Proyecto proyecto) throws SQLException {
        //Comprobacion y restricciones
        RepoDepartamento repoDepartamento = new RepoDepartamento();
        //Si no coinciden los jefes de departamento
        if (repoDepartamento.getByIdJefe(proyecto.getIdJefe()).isEmpty()){
            Departamento departamento = repoDepartamento.getByIdJefe(proyecto.getIdJefe()).get();
            //Si el jefe no esta en trabajadores se inserta el jefe
            if(!(departamento.getTrabajadores().get(0).contains(proyecto.getIdJefe()))) {

                System.out.println("Insertando proyecto");
                String query = "INSERT INTO proyecto VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
                DataBaseController db = DataBaseController.getInstance();
                db.open();
                db.insert(query, UUID.randomUUID().toString(),
                                proyecto.getNombre(), proyecto.getIdJefe(), proyecto.getPresupuesto(),
                                proyecto.getFechaInicio(), proyecto.getFechaFin(),
                                String.join(";", proyecto.getTecnologias()),
                                proyecto.getIdRepositorio())
                        .orElseThrow(() -> new SQLException("Error insertar proyecto"));
            }
        }

        return Optional.of(proyecto);
    }
    /**
     * Updatear proyecto
     * @author Dylan Hurtado y Javier González
     * @version 02/09/21 - 1.0
     */
    @Override
    public Optional<Proyecto> update(Proyecto proyecto) throws SQLException {
        //Comprobacion y restricciones
        RepoDepartamento repoDepartamento = new RepoDepartamento();
        //Si no coinciden los jefes de departamento
        if (repoDepartamento.getByIdJefe(proyecto.getIdJefe()).isEmpty()){
            Departamento departamento = repoDepartamento.getByIdJefe(proyecto.getIdJefe()).get();
            //Si el jefe no esta en trabajadores se actualiza el jefe
            if(!(departamento.getTrabajadores().get(0).contains(proyecto.getIdJefe()))) {

                System.out.println("Actualizando proyecto con id: " + proyecto.getIdProyecto());
                String query = "UPDATE proyecto SET idProyecto = ?, nombre = ?, idJefe = ?, presupuesto = ?, fechaInicio = ?, fechaFin = ? ,tecnologias = ?, idRepositorio = ? WHERE idProyecto = ?";
                DataBaseController db = DataBaseController.getInstance();
                db.open();
                db.update(query, proyecto.getIdProyecto(),
                        proyecto.getNombre(), proyecto.getIdJefe(), proyecto.getPresupuesto(),
                        proyecto.getFechaInicio(), proyecto.getFechaFin(),
                        String.join(";", proyecto.getTecnologias()),
                        proyecto.getIdRepositorio());
                db.close();
            }}
        return Optional.of(proyecto);
    }

    /**
     * Borrar proyecto
     * @author Dylan Hurtado y Javier González
     * @version 02/09/21 - 1.0
     */
    @Override
    public Optional<Proyecto> delete(Proyecto proyecto) throws SQLException {

        RepoRepositorio repoRepositorio = new RepoRepositorio();
        if(repoRepositorio.getById(proyecto.getIdRepositorio()).isPresent()) {
            Repositorio repositorio = repoRepositorio.getById(proyecto.getIdRepositorio()).get();
            //Cuando borras proyecto borras repositorio
            repoRepositorio.delete(repositorio);

            System.out.println("Eliminando proyecto con id: " + proyecto.getIdProyecto());
            String query = "DELETE FROM proyecto WHERE idProyecto = ?";
            DataBaseController db = DataBaseController.getInstance();
            db.open();
            db.delete(query, proyecto.getIdProyecto());
            db.close();
        }
        return Optional.of(proyecto);
    }
    /**
     * Coger proyecto con un id jefe asignado
     * @author Dylan Hurtado y Javier González
     * @version 02/09/21 - 1.0
     */
    public Optional<Proyecto> getByIdJefe(String id) throws SQLException {
        System.out.println("Obteniendo proyecto con idJefe: " + id);
        String query = "SELECT * FROM proyecto WHERE idJefe = ?";
        DataBaseController db = DataBaseController.getInstance();
        Proyecto proyecto = null;
        db.open();
        ResultSet result = db.select(query, id).orElseThrow(() -> new SQLException("Error al consultar departamento con IdJefe " + id));
        if (result.first()) {
            proyecto = new Proyecto(
                    result.getString("idProyecto"),
                    result.getString("nombre"),
                    result.getString("idJefe"),
                    result.getDouble("presupuesto"),
                    result.getDate("fechaInicio").toLocalDate(),
                    result.getDate("fechaFin").toLocalDate(),
                    List.of(result.getString("tecnologias").split(";")),
                    result.getString("idRepositorio"),
                    result.getString("idDepartamento")
            );}
        db.close();
        return Optional.ofNullable(proyecto);
    }
    /**
     * Coger proyectos con un id de departamento encargado
     * @author Dylan Hurtado y Javier González
     * @version 02/09/21 - 1.0
     */
    public Optional<Proyecto> getByIdDepartamento(String id) throws SQLException {
        System.out.println("Obteniendo id de Departamento: " + id);
        String query = "SELECT * FROM proyecto WHERE idDepartamento = ?";
        DataBaseController db = DataBaseController.getInstance();
        Proyecto proyecto = null;
        db.open();
        ResultSet result = db.select(query, id).orElseThrow(() -> new SQLException("Error al consultar departamento con idDepartamento " + id));
        if (result.first()) {
            proyecto = new Proyecto(
                    result.getString("idProyecto"),
                    result.getString("nombre"),
                    result.getString("idJefe"),
                    result.getDouble("presupuesto"),
                    result.getDate("fechaInicio").toLocalDate(),
                    result.getDate("fechaFin").toLocalDate(),
                    List.of(result.getString("tecnologias").split(";")),
                    result.getString("idRepositorio"),
                    result.getString("idDepartamento")//ojo
            );}
        db.close();
        return Optional.ofNullable(proyecto);
    }
    /**
     * Coger TODOS los proyectos por un id de departamento asignado
     * @author Dylan Hurtado y Javier González
     * @version 02/09/21 - 1.0
     */
    public Optional<List<Proyecto>> getAllByIdDepartamento(String id) throws SQLException {
        System.out.println("Obteniendo todos los proyecto");
        String query = "SELECT * FROM proyecto WHERE idDepartamento = ?";
        DataBaseController db = DataBaseController.getInstance();
        ArrayList<Proyecto> list;
        db.open();
        ResultSet result = db.select(query,id).orElseThrow(() -> new SQLException("Error al consultar proyecto"));
        list = new ArrayList<>();
        while (result.next()) {
            list.add(
                    new Proyecto(
                            result.getString("idProyecto"),
                            result.getString("nombre"),
                            result.getString("idJefe"),
                            result.getDouble("presupuesto"),
                            result.getDate("fechaInicio").toLocalDate(),
                            result.getDate("fechaFin").toLocalDate(),
                            List.of(result.getString("tecnologias").split(";")),
                            result.getString("idRepositorio"),
                            result.getString("idDepartamento")));
        }
        db.close();
        return Optional.of(list);
    }
    /**
     * Operacion 5
     * Obtener los tres proyectos más caros en base a su presupuesto asignado y el salario de cada trabajador que participa
     * @author Dylan Hurtado y Javier González
     * @version 02/09/21 - 1.0
     */

    public Optional<List<Object>> getProyectosMasCaros() throws SQLException {

        RepoProgramador repoProgramador = new RepoProgramador();

        List<Double> salarios= new ArrayList<>();
        if (this.getAll().isPresent()) {
            List<Proyecto> proyCaros =this.getAll().get().stream().sorted(Comparator.comparingDouble(Proyecto::getPresupuesto).reversed()).limit(3).collect(Collectors.toList());
            proyCaros.forEach(x->{
                try {
                    if (repoProgramador.getAllByProyectoSortByCommits(x.getIdProyecto()).isPresent()) {
                        List<Programador> programadores = repoProgramador.getAllByProyectoSortByCommits(x.getIdProyecto()).get();

                        programadores.forEach(p -> salarios.add(p.getSalario()));}

                    } catch(SQLException e){
                        e.printStackTrace();
                    }
            });

            return Optional.of(List.of(proyCaros,salarios));
        }
        System.out.println("No se han encontrado los tres proyectos mas caros y salarios getProyectosMasCaros");
        return Optional.empty();
    }

}
