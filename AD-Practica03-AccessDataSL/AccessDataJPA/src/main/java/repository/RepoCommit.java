package repository;

import dao.Commit;
import manager.HibernateController;

import javax.persistence.TypedQuery;
import java.sql.SQLException;
import java.util.*;

public class RepoCommit implements CrudRepository<Commit, String> {
    @Override
    public Optional<List<Commit>> getAll() throws SQLException {
        HibernateController hc = HibernateController.getInstance();
        hc.open();
        TypedQuery<Commit> query = hc.getManager().createNamedQuery("Commit.getAll",Commit.class);
        List<Commit> list = query.getResultList();
        hc.close();
        return Optional.of(list);
    }

    @Override
    public Optional<Commit> getById(String id) throws SQLException {
        HibernateController hc = HibernateController.getInstance();
        hc.open();
        Commit commit = hc.getManager().find(Commit.class, id);
        hc.close();
        if (commit != null) {
            return Optional.of(commit);
        }
        throw new SQLException("Error RepoCommit no existe commit con ID: " + id);
    }

    @Override
    public Optional<Commit> save(Commit commit) throws SQLException {
        HibernateController hc = HibernateController.getInstance();
        hc.open();
        try {
            hc.getTransaction().begin();
            commit.setIdCommit(UUID.randomUUID().toString());
            hc.getManager().persist(commit);
            hc.getTransaction().commit();
            hc.close();
            return Optional.of(commit);

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
    public Optional<Commit> update(Commit commit) throws SQLException {

        HibernateController hc = HibernateController.getInstance();
        hc.open();
        try {
            hc.getTransaction().begin();
            hc.getManager().merge(commit);
            hc.getTransaction().commit();
            hc.close();
            return Optional.of(commit);
        } catch (Exception e) {
            throw new SQLException("Error RepoCommit al actualizar commit con id: " + commit.getIdCommit());
        } finally {
            if (hc.getTransaction().isActive()) {
                hc.getTransaction().rollback();
            }
            hc.close();
        }
    }

    @Override
    public Optional<Commit> delete(Commit commit) throws SQLException {
        HibernateController hc = HibernateController.getInstance();
        hc.open();
        try {
            hc.getTransaction().begin();
            // Ojo que borrar implica que estemos en la misma sesión y nos puede dar problemas, por eso lo recuperamos otra vez
            commit = hc.getManager().find(Commit.class, commit.getIdCommit());
            hc.getManager().remove(commit);
            hc.getTransaction().commit();
            hc.close();
            return Optional.of(commit);
        } catch (Exception e) {
            throw new SQLException("Error CategoryRepository al eliminar categoria con id: " + commit.getIdCommit());
        } finally {
            if (hc.getTransaction().isActive()) {
                hc.getTransaction().rollback();
            }
            hc.close();
        }
    }

    public Optional<List<Commit>> getAllByRepositorio(String idRepositorio) throws SQLException {
        HibernateController hc = HibernateController.getInstance();
        hc.open();
        TypedQuery<Commit> query = hc.getManager().createNamedQuery("Commit.getAllByRepositorio", Commit.class).setParameter("repositorio",idRepositorio);
        List<Commit> list = query.getResultList();
        hc.close();
        return Optional.of(list);
    }

    public Optional<List<Commit>> getAllByAuthor(String autor) throws SQLException {
        HibernateController hc = HibernateController.getInstance();
        hc.open();
        TypedQuery<Commit> query = hc.getManager().createNamedQuery("Commit.getAllByAuthor", Commit.class).setParameter("author",autor);
        List<Commit> list = query.getResultList();
        hc.close();
        return Optional.of(list);
    }
}
