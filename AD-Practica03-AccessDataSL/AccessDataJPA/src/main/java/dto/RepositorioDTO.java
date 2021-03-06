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

@XmlRootElement(name="repositorio")
public class RepositorioDTO {
    private String idRepositorio;
    private String idProyecto;
    private LocalDate fechaCreacion;
    private List<String> commits;
    private List<String> issues;

    // From/To JSON IMPLEMENTAR METODOS CUANDO PASEMOS A JSON
    public static RepositorioDTO fromJSON(String json) {
        final Gson gson = new Gson();
        return gson.fromJson(json, RepositorioDTO.class);
    }

    public String toJSON() {
        final Gson prettyGson = new GsonBuilder().setPrettyPrinting().create();
        return prettyGson.toJson(this);
    }



}
