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

@XmlRootElement(name="programador")
public class ProgramadorDTO {
    private String idProgramador;
    private String nombre;
    private LocalDate fechaAlta;
    private String idDepartamento;
    private List<String> proyectosParticipa;
    private List<String> commits;
    private List<String> issues;
    private List<String> tecnologias;
    private Double salario;

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
