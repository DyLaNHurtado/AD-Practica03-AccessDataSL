package dao;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Builder
@AllArgsConstructor

@Entity
@Table(name = "proyecto")
@NamedQueries({
        @NamedQuery(name = "Proyecto.getAll", query = "SELECT c FROM dao.Proyecto c"),
        @NamedQuery(name = "Proyecto.getByIdJefe", query = "SELECT c FROM dao.Proyecto c WHERE c.idJefe=:idJefe"),
        @NamedQuery(name = "Proyecto.getByIdDepartamento", query = "SELECT c FROM dao.Proyecto c WHERE c.idDepartamento=:idDepartamento"),
})

public class Proyecto {
    private String idProyecto;
    private String nombre;
    private String idJefe;
    private Double presupuesto;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private List<String> tecnologias;
    private String idRepositorio;
    private String idDepartamento;

    public Proyecto() {

    }

    @Id
    @Column(nullable = false)
    public String getIdProyecto() {
        return idProyecto;
    }


    public void setIdProyecto(String idProyecto) {
        this.idProyecto = idProyecto;
    }
    @Basic
    @Column(nullable = false)
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    @Basic
    @Column(nullable = false)
    public String getIdJefe() {
        return idJefe;
    }

    public void setIdJefe(String idJefe) {
        this.idJefe = idJefe;
    }
    @Basic
    @Column(nullable = false)
    public Double getPresupuesto() {
        return presupuesto;
    }

    public void setPresupuesto(Double presupuesto) {
        this.presupuesto = presupuesto;
    }
    @Basic
    @Column(nullable = false)
    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(LocalDate fechaInicio) {
        this.fechaInicio = fechaInicio;
    }
    @Basic
    @Column(nullable = false)
    public LocalDate getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(LocalDate fechaFin) {
        this.fechaFin = fechaFin;
    }
    @Basic
    @Column(nullable = false)
    public List<String> getTecnologias() {
        return tecnologias;
    }

    public void setTecnologias(List<String> tecnologias) {
        this.tecnologias = tecnologias;
    }
    @Basic
    @Column(nullable = false)
    public String getIdRepositorio() {
        return idRepositorio;
    }

    public void setIdRepositorio(String idRepositorio) {
        this.idRepositorio = idRepositorio;
    }
    @Basic
    @Column(nullable = false)
    public String getIdDepartamento() {
        return idDepartamento;
    }

    public void setIdDepartamento(String idDepartamento) {
        this.idDepartamento = idDepartamento;
    }
}
