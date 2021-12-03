package repository;

import dao.*;
import manager.HibernateController;

import javax.persistence.TypedQuery;

import java.sql.SQLException;
import java.util.*;
import java.util.stream.Collectors;

public class RepoProgramador implements CrudRepository<Programador, String> {

    @Override
    public Optional<List<Programador>> getAll() throws SQLException {
        HibernateController hc = HibernateController.getInstance();
        hc.open();
        TypedQuery<Programador> query = hc.getManager().createNamedQuery("Programador.getAll",Programador.class);
        List<Programador> list = query.getResultList();
        hc.close();
        return Optional.of(list);
    }

    @Override
    public Optional<Programador> getById(String id) throws SQLException {
        HibernateController hc = HibernateController.getInstance();
        hc.open();
        Programador programador = hc.getManager().find(Programador.class, id);
        hc.close();
        if (programador != null) {
            return Optional.of(programador);
        }
        throw new SQLException("Error RepoCommit no existe commit con ID: " + id);
    }


    @Override
    public Optional<Programador> save(Programador programador) throws SQLException {
        HibernateController hc = HibernateController.getInstance();
        hc.open();
        try {
            hc.getTransaction().begin();
            programador.setIdProgramador(UUID.randomUUID().toString());
            hc.getManager().persist(programador);
            hc.getTransaction().commit();
            hc.close();
            return Optional.of(programador);

        } catch (Exception e) {
            throw new SQLException("Error RepoCommit al insertar commit en BD");
        } finally {
            if (hc.getTransaction().isActive()) {
                hc.getTransaction().rollback();
            }
            hc.close();
        }
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


        HibernateController hc = HibernateController.getInstance();
        hc.open();
        try {
            hc.getTransaction().begin();
            hc.getManager().merge(programador);
            hc.getTransaction().commit();
            hc.close();
            return Optional.of(programador);
        } catch (Exception e) {
            throw new SQLException("Error RepoCommit al actualizar commit con id: " + programador.getIdProgramador());
        } finally {
            if (hc.getTransaction().isActive()) {
                hc.getTransaction().rollback();
            }
            hc.close();
        }
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
        }
            HibernateController hc = HibernateController.getInstance();
            hc.open();
            try {
                hc.getTransaction().begin();
                // Ojo que borrar implica que estemos en la misma sesión y nos puede dar problemas, por eso lo recuperamos otra vez
                programador = hc.getManager().find(Programador.class, programador.getIdProgramador());
                hc.getManager().remove(programador);
                hc.getTransaction().commit();
                hc.close();
                return Optional.of(programador);
            } catch (Exception e) {
                throw new SQLException("Error CategoryRepository al eliminar categoria con id: " + programador.getIdProgramador());
            } finally {
                if (hc.getTransaction().isActive()) {
                    hc.getTransaction().rollback();
                }
                hc.close();
            }
        }

    public Optional<Programador> getByIdDepartamento(String id) throws SQLException {

                HibernateController hc = HibernateController.getInstance();
                hc.open();
                TypedQuery<Programador> query = hc.getManager().createNamedQuery("Programador.getByIdDepartamento",Programador.class).setParameter("idDepartamento",id);
                Programador list = query.getSingleResult();
                hc.close();
                return Optional.of(list);
    }
    public Optional<List<Programador>> getAllByIdDepartamento(String id) throws SQLException {
        HibernateController hc = HibernateController.getInstance();
        hc.open();
        TypedQuery<Programador> query = hc.getManager().createNamedQuery("Programador.getByIdDepartamento",Programador.class).setParameter("idDepartamento",id);
        List<Programador> list = query.getResultList();
        hc.close();
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
    public Optional<List<Object>> getAllProgramadoresInfo() throws SQLException {
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
