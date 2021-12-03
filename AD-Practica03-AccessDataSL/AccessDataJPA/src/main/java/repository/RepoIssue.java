package repository;

import dao.Commit;
import database.DataBaseController;
import dao.Issue;
import manager.HibernateController;

import javax.persistence.TypedQuery;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

public class RepoIssue implements CrudRepository<Issue, String> {
    @Override
    public Optional<List<Issue>> getAll() throws SQLException {
        HibernateController hc = HibernateController.getInstance();
        hc.open();
        TypedQuery<Issue> query = hc.getManager().createNamedQuery("Issue.getAll",Issue.class);
        List<Issue> list = query.getResultList();
        hc.close();
        return Optional.of(list);
    }

    @Override
    public Optional<Issue> getById(String id) throws SQLException {
        HibernateController hc = HibernateController.getInstance();
        hc.open();
        Issue issue = hc.getManager().find(Issue.class, id);
        hc.close();
        if (issue != null) {
            return Optional.of(issue);
        }
        throw new SQLException("Error RepoIssue no existe issue con ID: " + id);
    }

    @Override
    public Optional<Issue> save(Issue issue) throws SQLException {
        HibernateController hc = HibernateController.getInstance();
        hc.open();
        try {
            hc.getTransaction().begin();
            issue.setIdIssue(UUID.randomUUID().toString());
            hc.getManager().persist(issue);
            hc.getTransaction().commit();
            hc.close();
            return Optional.of(issue);

        } catch (Exception e) {
            throw new SQLException("Error RepoCommit al insertar issue en BD");
        } finally {
            if (hc.getTransaction().isActive()) {
                hc.getTransaction().rollback();
            }
            hc.close();
        }
    }

    @Override
    public Optional<Issue> update(Issue issue) throws SQLException {
        HibernateController hc = HibernateController.getInstance();
        hc.open();
        try {
            hc.getTransaction().begin();
            hc.getManager().merge(issue);
            hc.getTransaction().commit();
            hc.close();
            return Optional.of(issue);
        } catch (Exception e) {
            throw new SQLException("Error RepoCommit al actualizar commit con id: " + issue.getIdIssue());
        } finally {
            if (hc.getTransaction().isActive()) {
                hc.getTransaction().rollback();
            }
            hc.close();
        }
    }

    @Override
    public Optional<Issue> delete(Issue issue) throws SQLException {
        HibernateController hc = HibernateController.getInstance();
        hc.open();
        try {
            hc.getTransaction().begin();
            // Ojo que borrar implica que estemos en la misma sesión y nos puede dar problemas, por eso lo recuperamos otra vez
            issue = hc.getManager().find(Issue.class, issue.getIdIssue());
            hc.getManager().remove(issue);
            hc.getTransaction().commit();
            hc.close();
            return Optional.of(issue);
        } catch (Exception e) {
            throw new SQLException("Error CategoryRepository al eliminar categoria con id: " + issue.getIdIssue());
        } finally {
            if (hc.getTransaction().isActive()) {
                hc.getTransaction().rollback();
            }
            hc.close();
        }
    }

    public Optional<Issue> getByProyecto(String idProyecto) throws SQLException {
        HibernateController hc = HibernateController.getInstance();
        hc.open();
        TypedQuery<Issue> query = hc.getManager().createNamedQuery("Issue.getByProyecto",Issue.class).setParameter("proyecto",idProyecto);
        Issue list = query.getSingleResult();
        hc.close();
        return Optional.of(list);
    }

    public Optional<List<Issue>> getAllByRepositorio(String idRepositorio) throws SQLException {
        HibernateController hc = HibernateController.getInstance();
        hc.open();
        TypedQuery<Issue> query = hc.getManager().createNamedQuery("Issue.getAllByRepositorio",Issue.class).setParameter("repositorio",idRepositorio);
        List<Issue> list = query.getResultList();
        hc.close();
        return Optional.of(list);
    }
    public Optional<List<Issue>> getAllByAuthor(String id) throws SQLException {
        if(this.getAll().isPresent()) {
            return Optional.of(this.getAll().get().stream().filter(x -> x.getProgramadores().contains(id)).collect(Collectors.toList()));
        }
        System.out.println("No se han encontrado issues en getAllByAuthor");
        return Optional.empty();
    }

    //Operacion 2
    //Listado de issues abiertas por proyecto que no se hayan consolidado en commits

        public Optional<List<Issue>> getAllAbiertasByProyecto(String idProyecto) throws SQLException {
            if(this.getAll().isPresent()) {
                return Optional.of(this.getAll().get().stream().filter(x -> x.getProyecto().equals(idProyecto) && x.getEstado().equals("pendiente")).collect(Collectors.toList()));
            }
            System.out.println("No se han encontrado issues en getAllAbiertasByProyecto");
            return Optional.empty();
        }

}

