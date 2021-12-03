package dao;

import lombok.AllArgsConstructor;
import lombok.Builder;

import javax.persistence.*;
import java.time.LocalDate;


@Builder
@AllArgsConstructor


@Entity
@Table(name = "commit")
@NamedQueries({
        @NamedQuery(name = "Commit.getAll", query = "SELECT c FROM dao.Commit c"),
        @NamedQuery(name = "Commit.getAllByRepositorio", query = "SELECT c FROM dao.Commit c WHERE c.repositorio=:repositorio"),
        @NamedQuery(name = "Commit.getAllByAuthor", query = "SELECT c FROM dao.Commit c WHERE c.autor=:author"),
})
public class Commit {
    private String idCommit;
    private String titulo;
    private String texto;
    private LocalDate fecha;
    private String repositorio;
    private String proyecto;
    private String autor;
    private String issue;

    public Commit() {

    }

    @Id
    @Column(name = "idDepartamento", nullable = false)
    public String getIdCommit() {
        return idCommit;
    }

    public void setIdCommit(String idCommit) {
        this.idCommit = idCommit;
    }

    @Basic
    @Column(name = "titulo", nullable = false, length = 255)
    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    @Basic
    @Column(name = "texto", nullable = false, length = 255)
    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    @Basic
    @Column(name = "fecha", nullable = false)
    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    @Basic
    @Column(name = "repositorio", nullable = false)
    public String getRepositorio() {
        return repositorio;
    }

    public void setRepositorio(String repositorio) {
        this.repositorio = repositorio;
    }

    @Basic
    @Column(name = "proyecto", nullable = false, length = 36)
    public String getProyecto() {
        return proyecto;
    }

    public void setProyecto(String proyecto) {
        this.proyecto = proyecto;
    }

    @Basic
    @Column(name = "proyecto", nullable = false, length = 36)
    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    @Basic
    @Column(name = "proyecto", nullable = false, length = 36)
    public String getIssue() {
        return issue;
    }

    public void setIssue(String issue) {
        this.issue = issue;
    }
}
