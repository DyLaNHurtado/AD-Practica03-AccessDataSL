package dto;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.Builder;
import lombok.Data;

import javax.xml.bind.annotation.XmlRootElement;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Data
@Builder

@XmlRootElement(name="issue")
public class IssueDTO {
    private String idIssue;
    private String titulo;
    private String texto;
    private LocalDate fecha;
    private List<String> programadores;
    private String proyecto;
    private String repositorio;
    private String estado;

    // From/To JSON IMPLEMENTAR METODOS CUANDO PASEMOS A JSON
    public static CommitDTO fromJSON(String json) {
        final Gson gson = new Gson();
        return gson.fromJson(json, CommitDTO.class);
    }

    public String toJSON() {
        final Gson prettyGson = new GsonBuilder().setPrettyPrinting().create();
        return prettyGson.toJson(this);
    }
}
