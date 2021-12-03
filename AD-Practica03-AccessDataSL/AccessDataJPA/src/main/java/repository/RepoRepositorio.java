package repository;

import dao.Commit;
import dao.Issue;
import dao.Proyecto;
import dao.Repositorio;
import database.DataBaseController;
import manager.HibernateController;

import javax.persistence.TypedQuery;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class RepoRepositorio implements CrudRepository<Repositorio, String> {
    @Override
    public Optional<List<Repositorio>> getAll() throws SQLException {
        HibernateController hc = HibernateController.getInstance();
        hc.open();
        TypedQuery<Repositorio> query = hc.getManager().createNamedQuery("Repositorio.getAll", Repositorio.class);
        List<Repositorio> list = query.getResultList();
        hc.close();
        return Optional.of(list);
    }

    @Override
    public Optional<Repositorio> getById(String id) throws SQLException {
        HibernateController hc = HibernateController.getInstance();
        hc.open();
        Repositorio repositorio = hc.getManager().find(Repositorio.class, id);
        hc.close();
        if (repositorio != null) {
            return Optional.of(repositorio);
        }
        throw new SQLException("Error RepoIssue no existe Repositorio con ID: " + id);
    }

    @Override
    public Optional<Repositorio> save(Repositorio repositorio) throws SQLException {
        HibernateController hc = HibernateController.getInstance();
        hc.open();
        try {
            hc.getTransaction().begin();
            repositorio.setIdRepositorio(UUID.randomUUID().toString());
            hc.getManager().persist(repositorio);
            hc.getTransaction().commit();
            hc.close();
            return Optional.of(repositorio);

        } catch (Exception e) {
            throw new SQLException("Error RepoCommit al insertar repositorio en BD");
        } finally {
            if (hc.getTransaction().isActive()) {
                hc.getTransaction().rollback();
            }
            hc.close();
        }
    }

    @Override
    public Optional<Repositorio> update(Repositorio repositorio) throws SQLException {
        HibernateController hc = HibernateController.getInstance();
        hc.open();
        try {
            hc.getTransaction().begin();
            hc.getManager().merge(repositorio);
            hc.getTransaction().commit();
            hc.close();
            return Optional.of(repositorio);
        } catch (Exception e) {
            throw new SQLException("Error RepoCommit al actualizar repositorio con id: " + repositorio.getIdProyecto());
        } finally {
            if (hc.getTransaction().isActive()) {
                hc.getTransaction().rollback();
            }
            hc.close();
        }
    }

    @Override
    public Optional<Repositorio> delete(Repositorio repositorio) throws SQLException {

        RepoCommit repoCommit = new RepoCommit();
        RepoIssue repoIssue = new RepoIssue();

        if (repoCommit.getAllByRepositorio(repositorio.getIdRepositorio()).isPresent()
                || repoIssue.getAllByRepositorio(repositorio.getIdRepositorio()).isPresent()) {
            List<Commit> commits = repoCommit.getAllByRepositorio(repositorio.getIdRepositorio()).get();
            List<Issue> issues = repoIssue.getAllByRepositorio(repositorio.getIdRepositorio()).get();
            //Borrar con bucle

            //Recorremos los issues que tiene el repositorio a borrar y los borramos
            issues.forEach(x -> {
                try {
                    repoIssue.delete(x);
                } catch (SQLException e) {
                    System.err.println("No se ha podido borrar los issues de repositorio");
                }
            });
            //Recorremos los commits que tiene el repositorio a borrar y los borramos
            commits.forEach(y -> {
                try {
                    repoCommit.delete(y);
                } catch (SQLException e) {
                    System.err.println("No se ha podido borrar los commits de repositorio");
                }
            });}
//meter delete de isuue
            HibernateController hc = HibernateController.getInstance();
            hc.open();
            try {
                hc.getTransaction().begin();
                // Ojo que borrar implica que estemos en la misma sesión y nos puede dar problemas, por eso lo recuperamos otra vez
                repositorio = hc.getManager().find(Repositorio.class, repositorio.getIdRepositorio());
                hc.getManager().remove(repositorio);
                hc.getTransaction().commit();
                hc.close();
                return Optional.of(repositorio);
            } catch (Exception e) {
                throw new SQLException("Error CategoryRepository al eliminar categoria con id: " + repositorio.getIdProyecto());
            } finally {
                if (hc.getTransaction().isActive()) {
                    hc.getTransaction().rollback();
                }
                hc.close();
            }
        }
    }