package dao;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor

@Entity
@Table(name = "tecnologia")
@NamedQueries({
        @NamedQuery(name = "Tecnologia.getAll", query = "SELECT c FROM dao.Tecnologia c"),
})
public class Tecnologia {
    private String idTecnologia;
    private String nombre;

    public Tecnologia() {

    }

    @Id
    @Column(name = "idTecnologia", nullable = false)
    public String getIdTecnologia() {
        return idTecnologia;
    }
    public void setIdTecnologia(String idTecnologia) {
        this.idTecnologia = idTecnologia;
    }

    @Basic
    @Column(name="nombre",nullable = false)
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

}
