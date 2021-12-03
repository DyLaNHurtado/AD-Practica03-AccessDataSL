package controller;



import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dto.IssueDTO;
import repository.RepoIssue;
import service.IssueService;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.sql.SQLException;

public class IssueController {
    private static IssueController controller = null;
    private Marshaller marshaller;

    // Mi Servicio unido al repositorio
    private final IssueService issueService;

    // Implementamos nuestro Singleton para el controlador
    private IssueController(IssueService issueService) {
        this.issueService = issueService;
    }

    public static IssueController getInstance() {
        if (controller == null) {
            controller = new IssueController(new IssueService(new RepoIssue()));
        }
        return controller;
    }

    public String getAllIssuesJSON() {
        try {
            // Vamos a devolver el JSON de los issues
            final Gson prettyGson = new GsonBuilder().setPrettyPrinting().create();
            return prettyGson.toJson(issueService.getAllIssues());
        } catch (SQLException e) {
            System.err.println("Error IssueController en getAll: " + e.getMessage());
            return "Error IssueController en getAll: " + e.getMessage();
        }
    }

    public String getIssueByIdJSON(String id) {
        try {
            // Vamos a devolver el JSON de las categorías
            final Gson prettyGson = new GsonBuilder().setPrettyPrinting().create();
            return prettyGson.toJson(issueService.getIssueById(id));
        } catch (SQLException e) {
            System.err.println("Error IssueController en getIssueById: " + e.getMessage());
            return "Error IssueController en getIssueById: " + e.getMessage();
        }
    }

    public String postIssueJSON(IssueDTO issueDTO) {
        try {
            final Gson prettyGson = new GsonBuilder().setPrettyPrinting().create();
            return prettyGson.toJson(issueService.postIssue(issueDTO));
        } catch (SQLException e) {
            System.err.println("Error IssueController en postIssue: " + e.getMessage());
            return "Error IssueController en postIssue: " + e.getMessage();
        }
    }

    public String updateIssueJSON(IssueDTO comitDTO) {
        try {
            final Gson prettyGson = new GsonBuilder().setPrettyPrinting().create();
            return prettyGson.toJson(issueService.updateIssue(comitDTO));
        } catch (SQLException e) {
            System.err.println("Error IssueController en updateIssue: " + e.getMessage());
            return "Error IssueController en updateIssue: " + e.getMessage();
        }
    }

    public String deleteIssueJSON(IssueDTO issueDTO) {
        try {
            final Gson prettyGson = new GsonBuilder().setPrettyPrinting().create();
            return prettyGson.toJson(issueService.deleteIssue(issueDTO));
        } catch (SQLException e) {
            System.err.println("Error IssueController en deleteIssue: " + e.getMessage());
            return "Error IssueController en deleteIssue: " + e.getMessage();
        }
    }

    public String getAllAbiertasByProyectoJSON(IssueDTO issueDTO) {
        try {
            final Gson prettyGson = new GsonBuilder().setPrettyPrinting().create();
            return prettyGson.toJson(issueService.getAllAbiertasByProyecto(issueDTO.getProyecto()));
        } catch (SQLException e) {
            System.err.println("Error DepartamentoController en getDepartamentoFullInfo: " + e.getMessage());
            return "Error DepartamentoController en getDepartamentoFullInfo: " + e.getMessage();
        }
    }

    //XML
    public void getAllIssueXML() {
        try {
            // Vamos a devolver el XML de los commits
            JAXBContext jaxbContext = JAXBContext.newInstance(IssueDTO.class);
            marshaller = jaxbContext.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.marshal(issueService.getAllIssues(), System.out);
        } catch (SQLException | JAXBException e) {
            System.err.println("Error IssueController en getAllIssueXML: " + e.getMessage());
        }
    }

    public void getIssueByIdXML(String id) {
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(IssueDTO.class);
            marshaller = jaxbContext.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.marshal(issueService.getIssueById(id), System.out);
        } catch (SQLException | JAXBException e) {
            System.err.println("Error IssueController en getIssueByIdXML: " + e.getMessage());
        }
    }

    public void postIssueXML(IssueDTO issueDTO) {
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(IssueDTO.class);
            marshaller = jaxbContext.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.marshal(issueService.postIssue(issueDTO), System.out);
        } catch (SQLException | JAXBException e) {
            System.err.println("Error IssueController en postIssueXML: " + e.getMessage());
        }
    }

    public void updateIssueXML(IssueDTO issueDTO) {
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(IssueDTO.class);
            marshaller = jaxbContext.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.marshal(issueService.updateIssue(issueDTO), System.out);
        } catch (SQLException | JAXBException e) {
            System.err.println("Error IssueController en updateIssueXML: " + e.getMessage());
        }
    }

    public void deleteIssueXML(IssueDTO issueDTO) {
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(IssueDTO.class);
            marshaller = jaxbContext.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.marshal(issueService.deleteIssue(issueDTO), System.out);
        } catch (SQLException | JAXBException e) {
            System.err.println("Error IssueController en deleteIssueXML: " + e.getMessage());
        }
    }


    public void getAllAbiertasByProyectoXML(IssueDTO issueDTO) {
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(IssueDTO.class);
            marshaller = jaxbContext.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.marshal(issueService.getAllAbiertasByProyecto(issueDTO.getProyecto()), System.out);
        } catch (SQLException | JAXBException e) {
            System.err.println("Error IssueController en getAllAbiertasByProyectoXML: " + e.getMessage());
        }
    }
}