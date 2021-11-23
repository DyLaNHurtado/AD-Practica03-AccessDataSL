package repository;

import database.DataBaseController;
import model.Departamento;
import model.Proyecto;
import model.Repositorio;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class RepoRepositorio implements CrudRepository<Repositorio, UUID>{
    @Override
    public Optional<List<Repositorio>> getAll() throws SQLException {
        System.out.println("Obteniendo todos los repositorios");
        String query = "SELECT * FROM repositorio";
        DataBaseController db = DataBaseController.getInstance();
        ArrayList<Repositorio> list = null;
        db.open();
        ResultSet result = db.select(query).orElseThrow(() -> new SQLException("Error al consultar repositorios"));
        list = new ArrayList<>();
        while (result.next()) {
            list.add(
                    new Repositorio(
                            result.getObject("idRepositorio",java.util.UUID.class),
                            result.getDate("fechaCreacion"),
                            result.getObject("idProyecto",java.util.UUID.class),
                            List.of(result.getString("commits").split(";")),
                            List.of(result.getString("issues").split(";"))

                    ));
        }
        db.close();
        return Optional.of(list);
    }

    @Override
    public Optional<Repositorio> getById(UUID id) throws SQLException {
        System.out.println("Obteniendo repositorio con id: " + id);
        String query = "SELECT * FROM repositorio WHERE idRepositorio = ?";
        DataBaseController db = DataBaseController.getInstance();
        Repositorio repositorio = null;
        db.open();
        ResultSet result = db.select(query, id).orElseThrow(() -> new SQLException("Error al consultar repositorio con ID " + id));
        if (result.first()) {
            repositorio = new Repositorio(
                    result.getObject("idRepositorio",java.util.UUID.class),
                    result.getDate("fechaCreacion"),
                    result.getObject("idProyecto",java.util.UUID.class),
                    List.of(result.getString("commits").split(";")),
                    List.of(result.getString("issues").split(";"))
            );}
        db.close();
        return Optional.ofNullable(repositorio);
    }

    @Override
    public Optional<Repositorio> save(Repositorio repositorio) throws SQLException {
        System.out.println("Insertando repositorio");
        String query = "INSERT INTO repositorio VALUES (?, ?, ?, ?, ?)";
        DataBaseController db = DataBaseController.getInstance();
        db.open();
        db.insert(query, UUID.randomUUID(),
                        repositorio.getFechaCreacion(), repositorio.getIdProyecto(),
                        String.join(";", repositorio.getCommits()),
                        String.join(";", repositorio.getIssues()))
                .orElseThrow(() -> new SQLException("Error insertar repositorio"));


        return Optional.of(repositorio);
    }

    @Override
    public Optional<Repositorio> update(Repositorio repositorio) throws SQLException {
        System.out.println("Actualizando repositorio con id: " + repositorio.getIdRepositorio());
        String query = "UPDATE repositorio SET idRepositorio= ?, fechaCreacion = ?, idProyecto = ?, commits = ?, issues = ? WHERE idRepositorio = ?";
        DataBaseController db = DataBaseController.getInstance();
        db.open();
        db.update(query, repositorio.getIdRepositorio(),
                repositorio.getFechaCreacion(), repositorio.getIdProyecto(),
                String.join(";", repositorio.getCommits()),
                String.join(";", repositorio.getIssues()));
        db.close();

        return Optional.of(repositorio);
    }

    @Override
    public Optional<Repositorio> delete(Repositorio repositorio) throws SQLException {
        System.out.println("Eliminando repositorio con id: " + repositorio.getIdRepositorio());
        String query = "DELETE FROM repositorio WHERE idRepositorio = ?";
        DataBaseController db = DataBaseController.getInstance();
        db.open();
        db.delete(query, repositorio.getIdRepositorio());
        db.close();

        return Optional.of(repositorio);
    }
}
