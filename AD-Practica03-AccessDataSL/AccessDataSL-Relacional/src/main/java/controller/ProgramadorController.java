package controller;



import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dto.ProgramadorDTO;
import repository.RepoProgramador;
import service.ProgramadorService;

import java.sql.SQLException;

public class ProgramadorController {
    private static ProgramadorController controller = null;

    // Mi Servicio unido al repositorio
    private final ProgramadorService programadorService;

    // Implementamos nuestro Singleton para el controlador
    private ProgramadorController(ProgramadorService programadorService) {
        this.programadorService = programadorService;
    }

    public static ProgramadorController getInstance() {
        if (controller == null) {
            controller = new ProgramadorController(new ProgramadorService(new RepoProgramador()));
        }
        return controller;
    }

    public String getAllProgramadorsJSON() {
        try {
            // Vamos a devolver el JSON de los programadors
            final Gson prettyGson = new GsonBuilder().setPrettyPrinting().create();
            return prettyGson.toJson(programadorService.getAllProgramadores());
        } catch (SQLException e) {
            System.err.println("Error ProgramadorController en getAll: " + e.getMessage());
            return "Error ProgramadorController en getAll: " + e.getMessage();
        }
    }

    public String getProgramadorByIdJSON(String id) {
        try {
            // Vamos a devolver el JSON de las categorías
            final Gson prettyGson = new GsonBuilder().setPrettyPrinting().create();
            return prettyGson.toJson(programadorService.getProgramadorById(id));
        } catch (SQLException e) {
            System.err.println("Error ProgramadorController en getProgramadorById: " + e.getMessage());
            return "Error ProgramadorController en getProgramadorById: " + e.getMessage();
        }
    }

    public String postProgramadorJSON(ProgramadorDTO programadorDTO) {
        try {
            final Gson prettyGson = new GsonBuilder().setPrettyPrinting().create();
            return prettyGson.toJson(programadorService.postProgramador(programadorDTO));
        } catch (SQLException e) {
            System.err.println("Error ProgramadorController en postProgramador: " + e.getMessage());
            return "Error ProgramadorController en postProgramador: " + e.getMessage();
        }
    }

    public String updateProgramadorJSON(ProgramadorDTO comitDTO) {
        try {
            final Gson prettyGson = new GsonBuilder().setPrettyPrinting().create();
            return prettyGson.toJson(programadorService.updateProgramador(comitDTO));
        } catch (SQLException e) {
            System.err.println("Error ProgramadorController en updateProgramador: " + e.getMessage());
            return "Error ProgramadorController en updateProgramador: " + e.getMessage();
        }
    }

    public String deleteProgramadorJSON(ProgramadorDTO programadorDTO) {
        try {
            final Gson prettyGson = new GsonBuilder().setPrettyPrinting().create();
            return prettyGson.toJson(programadorService.deleteProgramador(programadorDTO));
        } catch (SQLException e) {
            System.err.println("Error ProgramadorController en deleteProgramador: " + e.getMessage());
            return "Error ProgramadorController en deleteProgramador: " + e.getMessage();
        }
    }
}