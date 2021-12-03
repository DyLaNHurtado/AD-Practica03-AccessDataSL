package controller;



import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dto.ProgramadorDTO;
import dto.ProyectoDTO;
import dto.RepositorioDTO;
import dto.TecnologiaDTO;
import repository.RepoTecnologia;
import service.TecnologiaService;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.sql.SQLException;

public class TecnologiaController {
    private static TecnologiaController controller = null;
    private Marshaller marshaller;

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

    //XML
    public void getAllTecnologiasXML() {
        try {
            // Vamos a devolver el XML de los commits
            JAXBContext jaxbContext = JAXBContext.newInstance(TecnologiaDTO.class);
            marshaller = jaxbContext.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.marshal(tecnologiaService.getAllTecnologias(), System.out);
        } catch (SQLException | JAXBException e) {
            System.err.println("Error TecnologiaController en getAllTecnologiasXML: " + e.getMessage());
        }
    }

    public void getTecnologiaByIdXML(String id) {
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(TecnologiaDTO.class);
            marshaller = jaxbContext.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.marshal(tecnologiaService.getTecnologiaById(id), System.out);
        } catch (SQLException | JAXBException e) {
            System.err.println("Error TecnologiaController en getTecnologiaByIdXML: " + e.getMessage());
        }
    }

    public void postTecnologiaXML(TecnologiaDTO tecnologiaDTO) {
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(TecnologiaDTO.class);
            marshaller = jaxbContext.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.marshal(tecnologiaService.postTecnologia(tecnologiaDTO), System.out);
        } catch (SQLException | JAXBException e) {
            System.err.println("Error TecnologiaController en postTecnologiaXML: " + e.getMessage());
        }
    }

    public void updateTecnologiaXML(TecnologiaDTO tecnologiaDTO) {
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(TecnologiaDTO.class);
            marshaller = jaxbContext.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.marshal(tecnologiaService.updateTecnologia(tecnologiaDTO), System.out);
        } catch (SQLException | JAXBException e) {
            System.err.println("Error TecnologiaController en updateTecnologiaXML: " + e.getMessage());
        }
    }

    public void deleteTecnologiaXML(TecnologiaDTO tecnologiaDTO) {
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(TecnologiaDTO.class);
            marshaller = jaxbContext.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.marshal(tecnologiaService.deleteTecnologia(tecnologiaDTO), System.out);
        } catch (SQLException | JAXBException e) {
            System.err.println("Error TecnologiaController en deleteTecnologiaXML: " + e.getMessage());
        }
    }
}