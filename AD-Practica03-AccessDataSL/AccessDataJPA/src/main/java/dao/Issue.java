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
@Table(name = "issue")
@NamedQueries({
        @NamedQuery(name = "Issue.getAll", query = "SELECT c FROM dao.Issue c"),
        @NamedQuery(name = "Issue.getByProyecto", query = "SELECT c FROM dao.Issue c WHERE c.proyecto=:proyecto"),
        @NamedQuery(name = "Issue.getAllByRepositorio", query = "SELECT c FROM dao.Issue c WHERE c.repositorio=:repositorio"),
})

public class Issue{
    private String idIssue;
    private String titulo;
    private String texto;
    private LocalDate fecha;
    private List<String> programadores;
    private String proyecto;
    private String repositorio;
    private String estado;

    public Issue() {

    }

    @Id
    @Column(name = "idIssue", nullable = false)
    public String getIdIssue() {
        return idIssue;
    }

    public void setIdIssue(String idIssue) {
        this.idIssue = idIssue;
    }

    @Basic
    @Column(name="titulo",nullable = false)
    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    @Basic
    @Column(name="texto",nullable = false)
    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }
    @Basic
    @Column(name="fecha",nullable = false)
    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }
    @Basic
    @Column(name="programadores",nullable = false,length = -1)

    public List<String> getProgramadores() {
        return programadores;
    }

    public void setProgramadores(List<String> programadores) {
        this.programadores = programadores;
    }
    @Basic
    @Column(name="proyecto",nullable = false,length = 36)
    public String getProyecto() {
        return proyecto;
    }

    public void setProyecto(String proyecto) {
        this.proyecto = proyecto;
    }
    @Basic
    @Column(name="repositorio",nullable = false,length = 36)
    public String getRepositorio() {
        return repositorio;
    }

    public void setRepositorio(String repositorio) {
        this.repositorio = repositorio;
    }
    @Basic
    @Column(name="estado",nullable = false)
    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
