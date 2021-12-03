package dao;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Data
@Builder
@AllArgsConstructor

@Entity
@Table(name="repositorio")
@NamedQueries({
@NamedQuery(name = "Repositorio.getAll", query = "SELECT c FROM dao.Repositorio c")
})
public class Repositorio {
    private String idRepositorio;
    private String idProyecto;
    private LocalDate fechaCreacion;
    private List<String> commits;
    private List<String> issues;


    @Id
    @Column(name = "idRepositorio", nullable = false)
    public String getIdRepositorio() {
        return idRepositorio;
    }

    public void setIdRepositorio(String idRepositorio) {
        this.idRepositorio = idRepositorio;
    }

    @Basic
    @Column(name="idProyecto",nullable = false)
    public String getIdProyecto() {
        return idProyecto;
    }

    public void setIdProyecto(String titulo) {
        this.idProyecto = idProyecto;
    }

    @Basic
    @Column(name="fechaCreacion",nullable = false)
    public LocalDate getFechaCreacion() {
        return fechaCreacion;
    }
    public void setFechaCreacion(LocalDate fechaCreacion) {
            this.fechaCreacion = fechaCreacion;
    }
    @Basic
    @Column(name="commits",nullable = false,length = -1)
    public List<String> getCommits() {
        return commits;
    }

    public void setCommits(List<String> commits) {
        this.commits = commits;
    }

    @Basic
    @Column(name="issues",nullable = false,length = -1)
    public List<String> getIssues() {
        return issues;
    }

    public void setIssues(List<String> issues) {
        this.issues = issues;
    }

}
