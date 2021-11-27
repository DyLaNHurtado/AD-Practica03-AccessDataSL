package repository;

import database.DataBaseController;
import model.Programador;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class RepoProgramador implements CrudRepository<Programador, String> {

    @Override
    public Optional<List<Programador>> getAll() throws SQLException {
        System.out.println("Obteniendo todos los programadores");
        String query = "SELECT * FROM programador";
        DataBaseController db = DataBaseController.getInstance();
        ArrayList<Programador> list = null;
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
                );}
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
                        programador.getNombre(), programador.getFechaAlta(), programador.getIdDepartamento(),
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
        System.out.println("Actualizando programador con id: " + programador.getIdProgramador());
        String query = "UPDATE programador SET idProgramador= ?, nombre = ?, fechaAlta = ?, departamento = ?, proyectosParticipa = ?, commits = ? ,issues = ?, tecnologias = ?, salario = ? WHERE idProgramador = ?";
        DataBaseController db = DataBaseController.getInstance();
        db.open();
        db.update(query, programador.getIdProgramador(), programador.getNombre(),
                programador.getFechaAlta(), programador.getIdProgramador(),
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
        System.out.println("Eliminando programador con id: " + programador.getIdProgramador());
        String query = "DELETE FROM programador WHERE idProgramador = ?";
        DataBaseController db = DataBaseController.getInstance();
        db.open();
        db.delete(query, programador.getIdProgramador());
        db.close();

        return Optional.of(programador);
    }
}
