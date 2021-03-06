package dto;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.Builder;
import lombok.Data;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@Data
@Builder

public class DepartamentoDTO {
    private String idDepartamento;
    private String nombre;
    private String idJefe;
    private List<String> trabajadores;
    private Double presupuesto;
    private List<String> proyFinalizados;
    private List<String> proyDesarrollo;
    private Double presupuestoAnual;
    private List<String> historialJefes;

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
