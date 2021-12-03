package repository;

import dao.Commit;
import database.DataBaseController;
import dao.Departamento;
import dao.Programador;
import dao.Proyecto;
import manager.HibernateController;

import javax.persistence.TypedQuery;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class RepoDepartamento implements CrudRepository<Departamento, String> {
    @Override
    public Optional<List<Departamento>> getAll() throws SQLException {
        HibernateController hc = HibernateController.getInstance();
        hc.open();
        TypedQuery<Departamento> query = hc.getManager().createNamedQuery("Departamento.getAll",Departamento.class);
        List<Departamento> list = query.getResultList();
        hc.close();
        return Optional.of(list);

    }

    @Override
    public Optional<Departamento> getById(String id) throws SQLException {
        HibernateController hc = HibernateController.getInstance();
        hc.open();
        Departamento departamento = hc.getManager().find(Departamento.class, id);
        hc.close();
        if (departamento != null) {
            return Optional.of(departamento);
        }
        throw new SQLException("Error RepoDepartamento no existe Departamento con ID: " + id);
    }

    @Override
    public Optional<Departamento> save(Departamento departamento) throws SQLException {
        HibernateController hc = HibernateController.getInstance();
        hc.open();
        try {
            hc.getTransaction().begin();
            departamento.setIdDepartamento(UUID.randomUUID().toString());
            hc.getManager().persist(departamento);
            hc.getTransaction().commit();
            hc.close();
            return Optional.of(departamento);

        } catch (Exception e) {
            throw new SQLException("Error RepoDepartamento al insertar Departamento en BD");
        } finally {
            if (hc.getTransaction().isActive()) {
                hc.getTransaction().rollback();
            }
            hc.close();
        }
    }

    @Override
    public Optional<Departamento> update(Departamento departamento) throws SQLException {
        HibernateController hc = HibernateController.getInstance();
        hc.open();
        try {
            hc.getTransaction().begin();
            hc.getManager().merge(departamento);
            hc.getTransaction().commit();
            hc.close();
            return Optional.of(departamento);
        } catch (Exception e) {
            throw new SQLException("Error RepoCommit al actualizar commit con id: " + departamento.getIdDepartamento());
        } finally {
            if (hc.getTransaction().isActive()) {
                hc.getTransaction().rollback();
            }
            hc.close();
        }
    }

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
        }
            HibernateController hc = HibernateController.getInstance();
            hc.open();
            try {
                hc.getTransaction().begin();
                // Ojo que borrar implica que estemos en la misma sesión y nos puede dar problemas, por eso lo recuperamos otra vez
                departamento = hc.getManager().find(Departamento.class, departamento.getIdDepartamento());
                hc.getManager().remove(departamento);
                hc.getTransaction().commit();
                hc.close();
                return Optional.of(departamento);
            } catch (Exception e) {
                throw new SQLException("Error DepartamentoRepository al eliminar Departamento con id: " + departamento.getIdDepartamento());
            } finally {
                if (hc.getTransaction().isActive()) {
                    hc.getTransaction().rollback();
                }
                hc.close();
            }
        }

    public Optional<Departamento> getByIdJefe(String id) throws SQLException {
            HibernateController hc = HibernateController.getInstance();
            hc.open();
            TypedQuery<Departamento> query = hc.getManager().createNamedQuery("Departamento.getByIdJefe",Departamento.class).setParameter("idJefe",id);
            Departamento list = query.getSingleResult();
            hc.close();
            return Optional.of(list);
        }

    // Operacion 1:
    //Obtener de un departamento, los proyectos (información completa) y trabajadores
    //asociados con sus datos completos
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
