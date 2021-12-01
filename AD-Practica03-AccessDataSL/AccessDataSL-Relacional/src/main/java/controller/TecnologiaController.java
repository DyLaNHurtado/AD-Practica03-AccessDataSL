package controller;



import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dto.TecnologiaDTO;
import repository.RepoTecnologia;
import service.TecnologiaService;

import java.sql.SQLException;

public class TecnologiaController {
    private static TecnologiaController controller = null;

    // Mi Servicio unido al repositorio
    private final TecnologiaService tecnologiaService;

    // Implementamos nuestro Singleton para el controlador
    private TecnologiaController(TecnologiaService tecnologiaService) {
        this.tecnologiaService = tecnologiaService;
    }

    public static TecnologiaController getInstance() {
        if (controller == null) {
            controller = new TecnologiaController(new TecnologiaService(new RepoTecnologia()));
        }
        return controller;
    }

    public String getAllTecnologiasJSON() {
        try {
            // Vamos a devolver el JSON de los tecnologias
            final Gson prettyGson = new GsonBuilder().setPrettyPrinting().create();
            return prettyGson.toJson(tecnologiaService.getAllTecnologias());
        } catch (SQLException e) {
            System.err.println("Error TecnologiaController en getAll: " + e.getMessage());
            return "Error TecnologiaController en getAll: " + e.getMessage();
        }
    }

    public String getTecnologiaByIdJSON(String id) {
        try {
            // Vamos a devolver el JSON de las categorías
            final Gson prettyGson = new GsonBuilder().setPrettyPrinting().create();
            return prettyGson.toJson(tecnologiaService.getTecnologiaById(id));
        } catch (SQLException e) {
            System.err.println("Error TecnologiaController en getTecnologiaById: " + e.getMessage());
            return "Error TecnologiaController en getTecnologiaById: " + e.getMessage();
        }
    }

    public String postTecnologiaJSON(TecnologiaDTO tecnologiaDTO) {
        try {
            final Gson prettyGson = new GsonBuilder().setPrettyPrinting().create();
            return prettyGson.toJson(tecnologiaService.postTecnologia(tecnologiaDTO));
        } catch (SQLException e) {
            System.err.println("Error TecnologiaController en postTecnologia: " + e.getMessage());
            return "Error TecnologiaController en postTecnologia: " + e.getMessage();
        }
    }

    public String updateTecnologiaJSON(TecnologiaDTO comitDTO) {
        try {
            final Gson prettyGson = new GsonBuilder().setPrettyPrinting().create();
            return prettyGson.toJson(tecnologiaService.updateTecnologia(comitDTO));
        } catch (SQLException e) {
            System.err.println("Error TecnologiaController en updateTecnologia: " + e.getMessage());
            return "Error TecnologiaController en updateTecnologia: " + e.getMessage();
        }
    }

    public String deleteTecnologiaJSON(TecnologiaDTO tecnologiaDTO) {
        try {
            final Gson prettyGson = new GsonBuilder().setPrettyPrinting().create();
            return prettyGson.toJson(tecnologiaService.deleteTecnologia(tecnologiaDTO));
        } catch (SQLException e) {
            System.err.println("Error TecnologiaController en deleteTecnologia: " + e.getMessage());
            return "Error TecnologiaController en deleteTecnologia: " + e.getMessage();
        }
    }
}