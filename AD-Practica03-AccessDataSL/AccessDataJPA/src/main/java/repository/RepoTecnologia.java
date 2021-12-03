package repository;

import dao.Proyecto;
import dao.Tecnologia;
import database.DataBaseController;
import manager.HibernateController;


import javax.persistence.TypedQuery;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class RepoTecnologia implements CrudRepository<Tecnologia, String> {
    @Override
    public Optional<List<Tecnologia>> getAll() throws SQLException {
        HibernateController hc = HibernateController.getInstance();
        hc.open();
        TypedQuery<Tecnologia> query = hc.getManager().createNamedQuery("Tecnologia.getAll", Tecnologia.class);
        List<Tecnologia> list = query.getResultList();
        hc.close();
        return Optional.of(list);
    }

    @Override
    public Optional<Tecnologia> getById(String id) throws SQLException {
        HibernateController hc = HibernateController.getInstance();
        hc.open();
        Tecnologia tecnologia = hc.getManager().find(Tecnologia.class, id);
        hc.close();
        if (tecnologia != null) {
            return Optional.of(tecnologia);
        }
        throw new SQLException("Error RepoIssue no existe tecnologia con ID: " + id);
    }

    @Override
    public Optional<Tecnologia> save(Tecnologia tecnologia) throws SQLException {
        HibernateController hc = HibernateController.getInstance();
        hc.open();
        try {
            hc.getTransaction().begin();
            tecnologia.setIdTecnologia(UUID.randomUUID().toString());
            hc.getManager().persist(tecnologia);
            hc.getTransaction().commit();
            hc.close();
            return Optional.of(tecnologia);

        } catch (Exception e) {
            throw new SQLException("Error RepoCommit al insertar tecnologia en BD");
        } finally {
            if (hc.getTransaction().isActive()) {
                hc.getTransaction().rollback();
            }
            hc.close();
        }
    }

    @Override
    public Optional<Tecnologia> update(Tecnologia tecnologia) throws SQLException {
        HibernateController hc = HibernateController.getInstance();
        hc.open();
        try {
            hc.getTransaction().begin();
            hc.getManager().merge(tecnologia);
            hc.getTransaction().commit();
            hc.close();
            return Optional.of(tecnologia);
        } catch (Exception e) {
            throw new SQLException("Error RepoCommit al actualizar tecnologia con id: " + tecnologia.getIdTecnologia());
        } finally {
            if (hc.getTransaction().isActive()) {
                hc.getTransaction().rollback();
            }
            hc.close();
        }
    }

    @Override
    public Optional<Tecnologia> delete(Tecnologia tecnologia) throws SQLException {
        HibernateController hc = HibernateController.getInstance();
        hc.open();
        try {
            hc.getTransaction().begin();
            // Ojo que borrar implica que estemos en la misma sesión y nos puede dar problemas, por eso lo recuperamos otra vez
            tecnologia = hc.getManager().find(Tecnologia.class, tecnologia.getIdTecnologia());
            hc.getManager().remove(tecnologia);
            hc.getTransaction().commit();
            hc.close();
            return Optional.of(tecnologia);
        } catch (Exception e) {
            throw new SQLException("Error CategoryRepository al eliminar tecnologia con id: " + tecnologia.getIdTecnologia());
        } finally {
            if (hc.getTransaction().isActive()) {
                hc.getTransaction().rollback();
            }
            hc.close();
        }
    }
}
