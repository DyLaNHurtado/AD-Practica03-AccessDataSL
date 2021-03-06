package dto;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.Builder;
import lombok.Data;

import javax.xml.bind.annotation.*;
import java.time.LocalDate;
import java.util.Date;

@Data
@Builder

public class CommitDTO {
    private String idCommit;
    private String titulo;
    private String texto;
    private LocalDate fecha;
    private String repositorio;
    private String proyecto;
    private String autor;
    private String issue;

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
