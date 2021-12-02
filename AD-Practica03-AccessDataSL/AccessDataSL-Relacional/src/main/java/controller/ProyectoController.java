package controller;



import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dto.ProgramadorDTO;
import dto.ProyectoDTO;
import repository.RepoProyecto;
import service.ProyectoService;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.sql.SQLException;

public class ProyectoController {
    private static ProyectoController controller = null;
    private Marshaller marshaller;

    // Mi Servicio unido al repositorio
    private final ProyectoService proyectoService;

    // Implementamos nuestro Singleton para el controlador
    private ProyectoController(ProyectoService proyectoService) {
        this.proyectoService = proyectoService;
    }

    public static ProyectoController getInstance() {
        if (controller == null) {
            controller = new ProyectoController(new ProyectoService(new RepoProyecto()));
        }
        return controller;
    }

    public String getAllProyectosJSON() {
        try {
            // Vamos a devolver el JSON de los proyectos
            final Gson prettyGson = new GsonBuilder().setPrettyPrinting().create();
            return prettyGson.toJson(proyectoService.getAllProyectos());
        } catch (SQLException e) {
            System.err.println("Error ProyectoController en getAll: " + e.getMessage());
            return "Error ProyectoController en getAll: " + e.getMessage();
        }
    }

    public String getProyectoByIdJSON(String id) {
        try {
            // Vamos a devolver el JSON de las categorías
            final Gson prettyGson = new GsonBuilder().setPrettyPrinting().create();
            return prettyGson.toJson(proyectoService.getProyectoById(id));
        } catch (SQLException e) {
            System.err.println("Error ProyectoController en getProyectoById: " + e.getMessage());
            return "Error ProyectoController en getProyectoById: " + e.getMessage();
        }
    }

    public String postProyectoJSON(ProyectoDTO proyectoDTO) {
        try {
            final Gson prettyGson = new GsonBuilder().setPrettyPrinting().create();
            return prettyGson.toJson(proyectoService.postProyecto(proyectoDTO));
        } catch (SQLException e) {
            System.err.println("Error ProyectoController en postProyecto: " + e.getMessage());
            return "Error ProyectoController en postProyecto: " + e.getMessage();
        }
    }

    public String updateProyectoJSON(ProyectoDTO comitDTO) {
        try {
            final Gson prettyGson = new GsonBuilder().setPrettyPrinting().create();
            return prettyGson.toJson(proyectoService.updateProyecto(comitDTO));
        } catch (SQLException e) {
            System.err.println("Error ProyectoController en updateProyecto: " + e.getMessage());
            return "Error ProyectoController en updateProyecto: " + e.getMessage();
        }
    }

    public String deleteProyectoJSON(ProyectoDTO proyectoDTO) {
        try {
            final Gson prettyGson = new GsonBuilder().setPrettyPrinting().create();
            return prettyGson.toJson(proyectoService.deleteProyecto(proyectoDTO));
        } catch (SQLException e) {
            System.err.println("Error ProyectoController en deleteProyecto: " + e.getMessage());
            return "Error ProyectoController en deleteProyecto: " + e.getMessage();
        }
    }

    public String getProyectosMasCarosJSON() {
        try {
            final Gson prettyGson = new GsonBuilder().setPrettyPrinting().create();
            return prettyGson.toJson(proyectoService.getProyectosMasCaros());
        } catch (SQLException e) {
            System.err.println("Error ProyectoController en getProyectosMasCaros: " + e.getMessage());
            return "Error ProyectoController en getProyectosMasCaros: " + e.getMessage();
        }
    }

    //XML
    public void getAllProyectosXML() {
        try {
            // Vamos a devolver el XML de los commits
            JAXBContext jaxbContext = JAXBContext.newInstance(ProyectoDTO.class);
            marshaller = jaxbContext.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.marshal(proyectoService.getAllProyectos(), System.out);
        } catch (SQLException | JAXBException e) {
            System.err.println("Error ProyectoController en getAllProyectosXML: " + e.getMessage());
        }
    }

    public void getProyectoByIdXML(String id) {
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(ProyectoDTO.class);
            marshaller = jaxbContext.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.marshal(proyectoService.getProyectoById(id), System.out);
        } catch (SQLException | JAXBException e) {
            System.err.println("Error ProyectoController en getProyectoByIdXML: " + e.getMessage());
        }
    }

    public void postProyectoXML(ProyectoDTO proyectoDTO) {
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(ProyectoDTO.class);
            marshaller = jaxbContext.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.marshal(proyectoService.postProyecto(proyectoDTO), System.out);
        } catch (SQLException | JAXBException e) {
            System.err.println("Error ProyectoController en postProyectoXML: " + e.getMessage());
        }
    }

    public void updateProyectoXML(ProyectoDTO proyectoDTO) {
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(ProyectoDTO.class);
            marshaller = jaxbContext.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.marshal(proyectoService.updateProyecto(proyectoDTO), System.out);
        } catch (SQLException | JAXBException e) {
            System.err.println("Error ProyectoController en updateProyectoXML: " + e.getMessage());
        }
    }

    public void deleteProyectoXML(ProyectoDTO proyectoDTO) {
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(ProyectoDTO.class);
            marshaller = jaxbContext.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.marshal(proyectoService.deleteProyecto(proyectoDTO), System.out);
        } catch (SQLException | JAXBException e) {
            System.err.println("Error ProyectoController en deleteProyectoXML: " + e.getMessage());
        }
    }

    public void getProyectosMasCarosXML() {
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(ProyectoDTO.class);
            marshaller = jaxbContext.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.marshal(proyectoService.getProyectosMasCaros(), System.out);
        } catch (SQLException | JAXBException e) {
            System.err.println("Error ProyectoController en deleteProyectoXML: " + e.getMessage());
        }
    }
}