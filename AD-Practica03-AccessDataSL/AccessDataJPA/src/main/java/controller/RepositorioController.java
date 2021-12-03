package controller;



import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dto.ProgramadorDTO;
import dto.ProyectoDTO;
import dto.RepositorioDTO;
import repository.RepoRepositorio;
import service.RepositorioService;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.sql.SQLException;

public class RepositorioController {
    private static RepositorioController controller = null;
    private Marshaller marshaller;

    // Mi Servicio unido al repositorio
    private final RepositorioService repositorioService;

    // Implementamos nuestro Singleton para el controlador
    private RepositorioController(RepositorioService repositorioService) {
        this.repositorioService = repositorioService;
    }

    public static RepositorioController getInstance() {
        if (controller == null) {
            controller = new RepositorioController(new RepositorioService(new RepoRepositorio()));
        }
        return controller;
    }

    public String getAllRepositoriosJSON() {
        try {
            // Vamos a devolver el JSON de los repositorios
            final Gson prettyGson = new GsonBuilder().setPrettyPrinting().create();
            return prettyGson.toJson(repositorioService.getAllRepositorios());
        } catch (SQLException e) {
            System.err.println("Error RepositorioController en getAll: " + e.getMessage());
            return "Error RepositorioController en getAll: " + e.getMessage();
        }
    }

    public String getRepositorioByIdJSON(String id) {
        try {
            // Vamos a devolver el JSON de las categorías
            final Gson prettyGson = new GsonBuilder().setPrettyPrinting().create();
            return prettyGson.toJson(repositorioService.getRepositorioById(id));
        } catch (SQLException e) {
            System.err.println("Error RepositorioController en getRepositorioById: " + e.getMessage());
            return "Error RepositorioController en getRepositorioById: " + e.getMessage();
        }
    }

    public String postRepositorioJSON(RepositorioDTO repositorioDTO) {
        try {
            final Gson prettyGson = new GsonBuilder().setPrettyPrinting().create();
            return prettyGson.toJson(repositorioService.postRepositorio(repositorioDTO));
        } catch (SQLException e) {
            System.err.println("Error RepositorioController en postRepositorio: " + e.getMessage());
            return "Error RepositorioController en postRepositorio: " + e.getMessage();
        }
    }

    public String updateRepositorioJSON(RepositorioDTO comitDTO) {
        try {
            final Gson prettyGson = new GsonBuilder().setPrettyPrinting().create();
            return prettyGson.toJson(repositorioService.updateRepositorio(comitDTO));
        } catch (SQLException e) {
            System.err.println("Error RepositorioController en updateRepositorio: " + e.getMessage());
            return "Error RepositorioController en updateRepositorio: " + e.getMessage();
        }
    }

    public String deleteRepositorioJSON(RepositorioDTO repositorioDTO) {
        try {
            final Gson prettyGson = new GsonBuilder().setPrettyPrinting().create();
            return prettyGson.toJson(repositorioService.deleteRepositorio(repositorioDTO));
        } catch (SQLException e) {
            System.err.println("Error RepositorioController en deleteRepositorio: " + e.getMessage());
            return "Error RepositorioController en deleteRepositorio: " + e.getMessage();
        }
    }

    //XML
    public void getAllRepositoriosXML() {
        try {
            // Vamos a devolver el XML de los commits
            JAXBContext jaxbContext = JAXBContext.newInstance(RepositorioDTO.class);
            marshaller = jaxbContext.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.marshal(repositorioService.getAllRepositorios(), System.out);
        } catch (SQLException | JAXBException e) {
            System.err.println("Error RepositorioController en getAllRepositoriosXML: " + e.getMessage());
        }
    }

    public void getRepositorioByIdXML(String id) {
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(RepositorioDTO.class);
            marshaller = jaxbContext.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.marshal(repositorioService.getRepositorioById(id), System.out);
        } catch (SQLException | JAXBException e) {
            System.err.println("Error RepositorioController en getRepositorioByIdXML: " + e.getMessage());
        }
    }

    public void postRepositorioXML(RepositorioDTO repositorioDTO) {
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(RepositorioDTO.class);
            marshaller = jaxbContext.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.marshal(repositorioService.postRepositorio(repositorioDTO), System.out);
        } catch (SQLException | JAXBException e) {
            System.err.println("Error RepositorioController en postRepositorioXML: " + e.getMessage());
        }
    }

    public void updateRepositorioXML(RepositorioDTO repositorioDTO) {
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(RepositorioDTO.class);
            marshaller = jaxbContext.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.marshal(repositorioService.updateRepositorio(repositorioDTO), System.out);
        } catch (SQLException | JAXBException e) {
            System.err.println("Error RepositorioController en updateRepositorioXML: " + e.getMessage());
        }
    }

    public void deleteRepositorioXML(RepositorioDTO repositorioDTO) {
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(RepositorioDTO.class);
            marshaller = jaxbContext.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.marshal(repositorioService.deleteRepositorio(repositorioDTO), System.out);
        } catch (SQLException | JAXBException e) {
            System.err.println("Error RepositorioController en deleteRepositorioXML: " + e.getMessage());
        }
    }
}