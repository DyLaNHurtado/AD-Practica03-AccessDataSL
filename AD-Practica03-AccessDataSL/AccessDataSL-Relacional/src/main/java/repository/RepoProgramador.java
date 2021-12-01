package repository;

import database.DataBaseController;
import model.*;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import java.util.stream.Collectors;

public class RepoProgramador implements CrudRepository<Programador, String> {

    @Override
    public Optional<List<Programador>> getAll() throws SQLException {
        System.out.println("Obteniendo todos los programadores");
        String query = "SELECT * FROM programador";
        DataBaseController db = DataBaseController.getInstance();
        ArrayList<Programador> list;
        db.open();
        ResultSet result = db.select(query).orElseThrow(() -> new SQLException("Error al consultar registros de programadores"));
        list = new ArrayList<>();
        while (result.next()) {
            list.add(
                    new Programador(
                            result.getString("idProgramador"),
                            result.getString("nombre"),
                            result.getDate("fechaAlta"),
                            result.getString("idDepartamento"),
                            List.of(result.getString("proyectosParticipa").split(";")),
                            List.of(result.getString("commits").split(";")),
                            List.of(result.getString("issues").split(";")),
                            List.of(result.getString("tecnologias").split(";")),
                            result.getDouble("salario"))
            );
        }
        db.close();
        return Optional.of(list);
    }

    @Override
    public Optional<Programador> getById(String id) throws SQLException {
        System.out.println("Obteniendo programador con id: " + id);
        String query = "SELECT * FROM programador WHERE idProgramador = ?";
        DataBaseController db = DataBaseController.getInstance();
        Programador programador = null;
        db.open();
        ResultSet result = db.select(query, id).orElseThrow(() -> new SQLException("Error al consultar programadores con ID " + id));
        if (result.first()) {
            programador = new Programador(
                    result.getString("idProgramador"),
                    result.getString("nombre"),
                    result.getDate("fechaAlta"),
                    result.getString("idDepartamento"),
                    List.of(result.getString("proyectosParticipa").split(";")),
                    List.of(result.getString("commits").split(";")),
                    List.of(result.getString("issues").split(";")),
                    List.of(result.getString("tecnologias").split(";")),
                    result.getDouble("salario")
            );
        }
        db.close();
        return Optional.ofNullable(programador);
    }


    @Override
    public Optional<Programador> save(Programador programador) throws SQLException {
        System.out.println("Insertando programador");
        String query = "INSERT INTO programador VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        DataBaseController db = DataBaseController.getInstance();
        db.open();
        db.insert(query, UUID.randomUUID().toString(),
                        programador.getNombre(), new Date(programador.getFechaAlta().getTime()), programador.getIdDepartamento(),
                        String.join(";", programador.getProyectosParticipa()),
                        String.join(";", programador.getCommits()),
                        String.join(";", programador.getIssues()),
                        String.join(";", programador.getTecnologias()),
                        programador.getSalario())
                .orElseThrow(() -> new SQLException("Error insertar programador"));


        return Optional.of(programador);
    }

    @Override
    public Optional<Programador> update(Programador programador) throws SQLException {

        //Comprabaciones y restricciones Programador
        RepoDepartamento repoDepartamento = new RepoDepartamento();
        if (repoDepartamento.getByIdJefe(programador.getIdProgramador()).isPresent()){//Comprobar si esta presente el Optional
            Departamento departamento = repoDepartamento.getByIdJefe(programador.getIdProgramador()).get();
            //Si no esta en trabajadores se actualiza el jefe
            if(!departamento.getTrabajadores().get(0).contains(programador.getIdProgramador())) {
                departamento = new Departamento(
                        departamento.getIdDepartamento(), departamento.getNombre(),
                        programador.getIdProgramador(), departamento.getTrabajadores(),
                        departamento.getPresupuesto(), departamento.getProyFinalizados(),
                        departamento.getProyDesarrollo(), departamento.getPresupuestoAnual(),
                        List.of(departamento.getHistorialJefes().get(0)+";"+programador.getIdProgramador())
                );
            }
            repoDepartamento.update(departamento);
    }


        System.out.println("Actualizando programador con id: " + programador.getIdProgramador());
        String query = "UPDATE programador SET idProgramador= ?, nombre = ?, fechaAlta = ?, idDepartamento = ?, proyectosParticipa = ?, commits = ? ,issues = ?, tecnologias = ?, salario = ? WHERE idProgramador = ?";
        DataBaseController db = DataBaseController.getInstance();
        db.open();
        db.update(query, programador.getIdProgramador(), programador.getNombre(),
                new Date(programador.getFechaAlta().getTime()), programador.getIdDepartamento(),
                String.join(";", programador.getProyectosParticipa()),
                String.join(";", programador.getCommits()),
                String.join(";", programador.getIssues()),
                String.join(";", programador.getTecnologias()),
                programador.getSalario(), programador.getIdProgramador());
        db.close();

        return Optional.of(programador);

    }

    @Override

    public Optional<Programador> delete(Programador programador) throws SQLException {

        RepoProyecto repoProyecto = new RepoProyecto();
        RepoDepartamento repoDepartamento = new RepoDepartamento();

        if (repoProyecto.getByIdJefe(programador.getIdProgramador()).isPresent()
                || repoDepartamento.getByIdJefe(programador.getIdProgramador()).isPresent() ) {

            Proyecto proyecto = repoProyecto.getByIdJefe(programador.getIdProgramador()).get();
            Departamento departamento = repoDepartamento.getByIdJefe(programador.getIdProgramador()).get();
            //Actualizamos el proyecto y el departamento cuando borramos
            proyecto.setIdJefe("");
            repoProyecto.update(proyecto);

            departamento.setIdJefe("");
            repoDepartamento.update(departamento);

            System.out.println("Eliminando programador con id: " + programador.getIdProgramador());
            String query = "DELETE FROM programador WHERE idProgramador = ?";
            DataBaseController db = DataBaseController.getInstance();
            db.open();
            db.delete(query, programador.getIdProgramador());
            db.close();
        }
        return Optional.of(programador);
    }

    public Optional<Programador> getByIdDepartamento(String id) throws SQLException {

        System.out.println("Obteniendo programador con idDepartamento: " + id);
        String query = "SELECT * FROM programador WHERE idDepartamento = ?";
        DataBaseController db = DataBaseController.getInstance();
        Programador programador = null;
        db.open();
        ResultSet result = db.select(query, id).orElseThrow(() -> new SQLException("Error al consultar programadores con IdDepartamento " + id));
        if (result.first()) {
            programador = new Programador(
                    result.getString("idProgramador"),
                    result.getString("nombre"),
                    result.getDate("fechaAlta"),
                    result.getString("idDepartamento"),
                    List.of(result.getString("proyectosParticipa").split(";")),
                    List.of(result.getString("commits").split(";")),
                    List.of(result.getString("issues").split(";")),
                    List.of(result.getString("tecnologias").split(";")),
                    result.getDouble("salario")
            );
        }
        db.close();
        return Optional.ofNullable(programador);

    }
    public Optional<List<Programador>> getAllByIdDepartamento(String id) throws SQLException {
        System.out.println("Obteniendo todos los programadores por idDepartamento: "+ id);
        String query = "SELECT * FROM programador WHERE idDepartamento = ?";
        DataBaseController db = DataBaseController.getInstance();
        ArrayList<Programador> list;
        db.open();
        ResultSet result = db.select(query,id).orElseThrow(() -> new SQLException("Error al consultar registros de programadores por idDepartamento"));
        list = new ArrayList<>();
        while (result.next()) {
            list.add(
                    new Programador(
                            result.getString("idProgramador"),
                            result.getString("nombre"),
                            result.getDate("fechaAlta"),
                            result.getString("idDepartamento"),
                            List.of(result.getString("proyectosParticipa").split(";")),
                            List.of(result.getString("commits").split(";")),
                            List.of(result.getString("issues").split(";")),
                            List.of(result.getString("tecnologias").split(";")),
                            result.getDouble("salario"))
            );
        }
        db.close();
        return Optional.of(list);
    }
    //Operacion 3
    //Programadores de un proyecto ordenados por número de commits.
    public Optional<List<Programador>> getAllByProyectoSortByCommits(String idProyecto) throws SQLException {
        if(this.getAll().isPresent()){
        return Optional.of(this.getAll().get().stream().filter(x->x.getProyectosParticipa().contains(idProyecto)).sorted(Comparator.comparingInt(x->x.getCommits().size())).collect(Collectors.toList()));

    }
        System.out.println("No se han encontrado programadores en getAllByProyectoSortByCommits");
        return Optional.empty();
    }

    //Operacion 4
    // Programadores con su productividad completa: proyectos , commits
    //(información completa) e issues asignadas (información completa).
    public Optional<List<Object>> getAllProgramadorInfo() throws SQLException {
        RepoCommit repoCommit = new RepoCommit();
        RepoIssue repoIssue = new RepoIssue();
        RepoProyecto repoProyecto = new RepoProyecto();

        if (this.getAll().isPresent()) {
            List<Programador> programadores = this.getAll().get();
            List<Commit> commits = new ArrayList<>();
            List<Issue> issues = new ArrayList<>();
            List<Proyecto> proyectos= new ArrayList<>();
            programadores.forEach(x->{
                try {
                    if(repoCommit.getAllByAuthor(x.getIdProgramador()).isPresent()
                            && repoIssue.getAllByAuthor(x.getIdProgramador()).isPresent()
                            && repoProyecto.getByIdJefe(x.getIdProgramador()).isPresent()) {

                        commits.addAll(repoCommit.getAllByAuthor(x.getIdProgramador()).get());
                        issues.addAll(repoIssue.getAllByAuthor(x.getIdProgramador()).get());
                        proyectos.add(repoProyecto.getByIdJefe(x.getIdProgramador()).get());
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                    System.err.println("error al añadir todo a la lista en metodo getProgramadorInfo");
                }

            });




            return Optional.of(List.of(programadores, proyectos ,commits, issues));
        }

        System.out.println("No se ha encontrado programadores en getAllProgramadorInfo");
        return Optional.empty();
    }
}
