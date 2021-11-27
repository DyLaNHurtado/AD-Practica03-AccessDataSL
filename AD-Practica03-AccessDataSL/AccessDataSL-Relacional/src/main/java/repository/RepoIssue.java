package repository;

import database.DataBaseController;
import model.Commit;
import model.Issue;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class RepoIssue implements CrudRepository<Issue, String>{
    @Override
    public Optional<List<Issue>> getAll() throws SQLException {
        System.out.println("Obteniendo todos los issues");
        String query = "SELECT * FROM issue";
        DataBaseController db = DataBaseController.getInstance();
        ArrayList<Issue> list = null;
        db.open();
        ResultSet result = db.select(query).orElseThrow(() -> new SQLException("Error al consultar issues"));
        list = new ArrayList<>();
        while (result.next()) {
            list.add(
                    new Issue(
                            result.getString("idIssue"),
                            result.getString("titulo"),
                            result.getString("texto"),
                            result.getDate("fecha"),
                            List.of(result.getString(String.join(";","programadores"))),
                            result.getString("proyecto"),
                            result.getString("repositorio"),
                            result.getString("estado")
                    ));
        }
        db.close();
        return Optional.of(list);
    }

    @Override
    public Optional<Issue> getById(String id) throws SQLException {
        System.out.println("Obteniendo issues con id: " + id);
        String query = "SELECT * FROM issues WHERE idIssue = ?";
        DataBaseController db = DataBaseController.getInstance();
        Issue issue = null;
        db.open();
        ResultSet result = db.select(query, id).orElseThrow(() -> new SQLException("Error al consultar departamento con ID " + id));
        if (result.first()) {
            issue = new Issue(
                    result.getString("idIssue"),
                    result.getString("titulo"),
                    result.getString("texto"),
                    result.getDate("fecha"),
                    List.of(result.getString(String.join(";","programadores"))),
                    result.getString("proyecto"),
                    result.getString("repositorio"),
                    result.getString("estado")
            );}
        db.close();
        return Optional.ofNullable(issue);
    }

    @Override
    public Optional<Issue> save(Issue issue) throws SQLException {
        System.out.println("Insertando commit");
        String query = "INSERT INTO commit VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        DataBaseController db = DataBaseController.getInstance();
        db.open();
        db.insert(query, UUID.randomUUID(),
                        issue.getTitulo(), issue.getTexto(), issue.getFecha(),
                        issue.getProgramadores(),issue.getProyecto(),issue.getRepositorio(),
                        issue.getEstado())
                .orElseThrow(() -> new SQLException("Error insertar commit"));


        return Optional.of(issue);
    }

    @Override
    public Optional<Issue> update(Issue issue) throws SQLException {
        System.out.println("Actualizando issue con id: " + issue.getIdIssue());
        String query = "UPDATE commit SET idIssue= ?, titulo = ?, texto = ?, fecha = ?, programadores = ?, proyecto = ? ,repositorio = ?, estado = ? WHERE idIssue = ?";
        DataBaseController db = DataBaseController.getInstance();
        db.open();
        db.update(query, issue.getIdIssue(),
                issue.getTitulo(), issue.getTexto(), issue.getFecha(),
                issue.getProgramadores(),issue.getProyecto(),issue.getRepositorio(),
                issue.getEstado());
        db.close();

        return Optional.of(issue);
    }

    @Override
    public Optional<Issue> delete(Issue issue) throws SQLException {
        System.out.println("Eliminando issue con id: " + issue.getIdIssue());
        String query = "DELETE FROM issue WHERE idIssue = ?";
        DataBaseController db = DataBaseController.getInstance();
        db.open();
        db.delete(query, issue.getIdIssue());
        db.close();

        return Optional.of(issue);
    }
}
