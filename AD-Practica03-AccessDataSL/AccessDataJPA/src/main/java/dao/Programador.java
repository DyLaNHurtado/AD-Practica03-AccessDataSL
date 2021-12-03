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
@Table(name = "programador")
@NamedQueries({
        @NamedQuery(name = "Programador.getAll", query = "SELECT c FROM dao.Programador c"),
        @NamedQuery(name = "Programador.getByIdDepartamento", query = "SELECT c FROM dao.Programador c WHERE c.idDepartamento=:idDepartamento"),
})
public class Programador {
    private String idProgramador;
    private String nombre;
    private LocalDate fechaAlta;
    private String idDepartamento;
    private List<String> proyectosParticipa;
    private List<String> commits;
    private List<String> issues;
    private List<String> tecnologias;
    private Double salario;

    public Programador() {

    }

    @Id
    @Column(name="idProgramador",nullable = false)
    public String getIdProgramador() {
        return idProgramador;
    }


    public void setIdProgramador(String idProgramador) {
        this.idProgramador = idProgramador;
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
    public LocalDate getFechaAlta() {
        return fechaAlta;
    }

    public void setFechaAlta(LocalDate fechaAlta) {
        this.fechaAlta = fechaAlta;
    }
    @Basic
    @Column(nullable = false)
    public String getIdDepartamento() {
        return idDepartamento;
    }

    public void setIdDepartamento(String idDepartamento) {
        this.idDepartamento = idDepartamento;
    }
    @Basic
    @Column(nullable = false)
    public List<String> getProyectosParticipa() {
        return proyectosParticipa;
    }

    public void setProyectosParticipa(List<String> proyectosParticipa) {
        this.proyectosParticipa = proyectosParticipa;
    }
    @Basic
    @Column(nullable = false)
    public List<String> getCommits() {
        return commits;
    }

    public void setCommits(List<String> commits) {
        this.commits = commits;
    }
    @Basic
    @Column(nullable = false)
    public List<String> getIssues() {
        return issues;
    }

    public void setIssues(List<String> issues) {
        this.issues = issues;
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
    public Double getSalario() {
        return salario;
    }

    public void setSalario(Double salario) {
        this.salario = salario;
    }
}
