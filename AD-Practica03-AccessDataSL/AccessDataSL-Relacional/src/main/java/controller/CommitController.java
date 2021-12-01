package controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dto.CommitDTO;
import repository.RepoCommit;
import service.CommitService;

import java.sql.SQLException;

public class CommitController {
        private static CommitController controller = null;

        // Mi Servicio unido al repositorio
        private final CommitService commitService;

        // Implementamos nuestro Singleton para el controlador
        private CommitController(CommitService commitService) {
            this.commitService = commitService;
        }

        public static CommitController getInstance() {
            if (controller == null) {
                controller = new CommitController(new CommitService(new RepoCommit()));
            }
            return controller;
        }

        // Ejemplo de operaciones
        // Usamos DTO para implementar este patrón en represantación y trasporte de la información
//    public List<CategoryDTO> getAllCategories() {
//        return categoryService.getAllCategories();
//    }

        public String getAllCommitsJSON() {
            try {
                // Vamos a devolver el JSON de los commits
                final Gson prettyGson = new GsonBuilder().setPrettyPrinting().create();
                return prettyGson.toJson(commitService.getAllCommits());
            } catch (SQLException e) {
                System.err.println("Error CommitController en getAll: " + e.getMessage());
                return "Error CommitController en getAll: " + e.getMessage();
            }
        }

        public String getCommitByIdJSON(String id) {
            try {
                // Vamos a devolver el JSON de las categorías
                final Gson prettyGson = new GsonBuilder().setPrettyPrinting().create();
                return prettyGson.toJson(commitService.getCommitById(id));
            } catch (SQLException e) {
                System.err.println("Error CommitController en getCommitById: " + e.getMessage());
                return "Error CommitController en getCommitById: " + e.getMessage();
            }
        }

        public String postCommitJSON(CommitDTO commitDTO) {
            try {
                final Gson prettyGson = new GsonBuilder().setPrettyPrinting().create();
                return prettyGson.toJson(commitService.postCommit(commitDTO));
            } catch (SQLException e) {
                System.err.println("Error CommitController en postCommit: " + e.getMessage());
                return "Error CommitController en postCommit: " + e.getMessage();
            }
        }

        public String updateCommitJSON(CommitDTO comitDTO) {
            try {
                final Gson prettyGson = new GsonBuilder().setPrettyPrinting().create();
                return prettyGson.toJson(commitService.updateCommit(comitDTO));
            } catch (SQLException e) {
                System.err.println("Error CommitController en updateCommit: " + e.getMessage());
                return "Error CommitController en updateCommit: " + e.getMessage();
            }
        }

        public String deleteCommitJSON(CommitDTO commitDTO) {
            try {
                final Gson prettyGson = new GsonBuilder().setPrettyPrinting().create();
                return prettyGson.toJson(commitService.deleteCommit(commitDTO));
            } catch (SQLException e) {
                System.err.println("Error CommitController en deleteCommit: " + e.getMessage());
                return "Error CommitController en deleteCommit: " + e.getMessage();
            }
        }
    }

