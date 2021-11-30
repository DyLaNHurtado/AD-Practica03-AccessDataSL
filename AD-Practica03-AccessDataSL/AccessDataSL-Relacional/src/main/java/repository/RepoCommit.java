package repository;

import database.DataBaseController;
import model.Commit;
import model.Departamento;
import model.Issue;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import java.util.stream.Collectors;

public class RepoCommit implements CrudRepository<Commit, String> {
    @Override
    public Optional<List<Commit>> getAll() throws SQLException {
        System.out.println("Obteniendo todos los commits");
        String query = "SELECT * FROM commit";
        DataBaseController db = DataBaseController.getInstance();
        ArrayList<Commit> list = null;
        db.open();
        ResultSet result = db.select(query).orElseThrow(() -> new SQLException("Error al consultar Commits"));
        list = new ArrayList<>();
        while (result.next()) {
            list.add(
                    new Commit(
                            result.getString("idCommit"),
                            result.getString("titulo"),
                            result.getString("texto"),
                            result.getDate("fecha"),
                            result.getString("repositorio"),
                            result.getString("proyecto"),
                            result.getString("autor"),
                            result.getString("issue")
                    ));
        }
        db.close();
        return Optional.of(list);
    }

    @Override
    public Optional<Commit> getById(String id) throws SQLException {
        System.out.println("Obteniendo commit con id: " + id);
        String query = "SELECT * FROM commit WHERE idCommit = ?";
        DataBaseController db = DataBaseController.getInstance();
        Commit commit = null;
        db.open();
        ResultSet result = db.select(query, id).orElseThrow(() -> new SQLException("Error al consultar departamento con ID " + id));
        if (result.first()) {
            commit = new Commit(
                    result.getString("idCommit"),
                    result.getString("titulo"),
                    result.getString("texto"),
                    result.getDate("fecha"),
                    result.getString("repositorio"),
                    result.getString("proyecto"),
                    result.getString("autor"),
                    result.getString("issue")
            );
        }
        db.close();
        return Optional.ofNullable(commit);
    }

    @Override
    public Optional<Commit> save(Commit commit) throws SQLException {

        //Restricciones
        RepoIssue repoIssue = new RepoIssue();
        if (repoIssue.getByProyecto(commit.getProyecto()).isPresent()) {
            Issue issue = repoIssue.getByProyecto(commit.getProyecto()).get();
            if (issue.getProgramadores().get(0).contains(commit.getAutor()) && repoIssue.getById(commit.getIssue()).isPresent()) {
                issue.setEstado("pendiente");
                System.out.println("Insertando commit");
                String query = "INSERT INTO commit VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
                DataBaseController db = DataBaseController.getInstance();
                db.open();
                db.insert(query, UUID.randomUUID().toString(),
                                commit.getTitulo(), commit.getTexto(), commit.getFecha(),
                                commit.getRepositorio(), commit.getProyecto(), commit.getAutor(),
                                commit.getIssue())
                        .orElseThrow(() -> new SQLException("Error insertar commit"));
                issue.setEstado("terminada");
            }
        }
        return Optional.of(commit);
    }

    @Override
    public Optional<Commit> update(Commit commit) throws SQLException {

        System.out.println("Actualizando commit con id: " + commit.getIdCommit());
        String query = "UPDATE commit SET idCommit= ?, titulo = ?, texto = ?, fecha = ?, repositorio = ?, proyecto = ? ,autor = ?, issue = ? WHERE idCommit = ?";
        DataBaseController db = DataBaseController.getInstance();
        db.open();
        db.update(query, commit.getIdCommit(),
                commit.getTitulo(), commit.getTexto(), commit.getFecha(),
                commit.getRepositorio(), commit.getProyecto(), commit.getAutor(),
                commit.getIssue());
        db.close();

        return Optional.of(commit);
    }

    @Override
    public Optional<Commit> delete(Commit commit) throws SQLException {
        System.out.println("Eliminando commit con id: " + commit.getIdCommit());
        String query = "DELETE FROM commit WHERE idCommit = ?";
        DataBaseController db = DataBaseController.getInstance();
        db.open();
        db.delete(query, commit.getIdCommit());
        db.close();

        return Optional.of(commit);
    }

    public Optional<List<Commit>> getAllByRepositorio(String id) throws SQLException {
        System.out.println("Obteniendo todos los commits");
        String query = "SELECT * FROM commit WHERE repositorio = ?";
        DataBaseController db = DataBaseController.getInstance();
        ArrayList<Commit> list = null;
        db.open();
        ResultSet result = db.select(query, id).orElseThrow(() -> new SQLException("Error al consultar Commits"));
        list = new ArrayList<>();
        while (result.next()) {
            list.add(
                    new Commit(
                            result.getString("idCommit"),
                            result.getString("titulo"),
                            result.getString("texto"),
                            result.getDate("fecha"),
                            result.getString("repositorio"),
                            result.getString("proyecto"),
                            result.getString("autor"),
                            result.getString("issue")
                    ));
        }
        db.close();
        return Optional.of(list);
    }

    public Optional<List<Commit>> getAllByAuthor(String id) throws SQLException {
        if(this.getAll().isPresent()) {
            return Optional.of(this.getAll().get().stream().filter(x -> Objects.equals(x.getAutor(), id)).collect(Collectors.toList()));
        }
        System.out.println("No se han encontrado commits en getAllByAuthor");
        return Optional.empty();
    }

}
