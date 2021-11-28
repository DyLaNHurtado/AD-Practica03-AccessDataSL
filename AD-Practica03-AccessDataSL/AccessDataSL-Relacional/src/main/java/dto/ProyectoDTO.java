package dto;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.Builder;
import lombok.Data;

import java.util.Date;
import java.util.List;
@Data
@Builder
public class ProyectoDTO {
    private String idProyecto;
    private String nombre;
    private String idJefe;
    private Double presupuesto;
    private Date fechaInicio;
    private Date fechaFin;
    private List<String> tecnologias;
    private String idRepositorio;
    private String idDepartamento;

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
