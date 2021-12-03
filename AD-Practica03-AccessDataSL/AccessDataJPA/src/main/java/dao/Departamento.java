package dao;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@Builder

@Entity
@Table(name = "departamento")
@NamedQueries({
        @NamedQuery(name = "Departamento.getAll", query = "SELECT c FROM dao.Departamento c"),
        @NamedQuery(name = "Departamento.getByIdJefe", query = "SELECT c FROM dao.Departamento c WHERE c.idJefe=:idJefe"),
})
public class Departamento {

    private String idDepartamento;
    private String nombre;
    private String idJefe;
    private List<String> trabajadores;
    private Double presupuesto;
    private List<String> proyFinalizados;
    private List<String> proyDesarrollo;
    private Double presupuestoAnual;
    private List<String> historialJefes;

    @Id
    @Column(name = "idDepartamento", nullable = false)
    public String getIdDepartamento() {
        return idDepartamento;
    }

    public void setIdDepartamento(String idDepartamento) {
        this.idDepartamento = idDepartamento;
    }
    @Basic
    @Column(name = "nombre", nullable = false)
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    @Basic
    @Column(name = "idJefe", nullable = false, length = 36)
    public String getIdJefe() {
        return idJefe;
    }

    public void setIdJefe(String idJefe) {
        this.idJefe = idJefe;
    }

    @Basic
    @Column(name = "trabajadores", nullable = false, length = -1)
    public List<String> getTrabajadores() {
        return trabajadores;
    }

    public void setTrabajadores(List<String> trabajadores) {
        this.trabajadores = trabajadores;
    }
    @Basic
    @Column(name = "presupuesto", nullable = false)
    public Double getPresupuesto() {
        return presupuesto;
    }

    public void setPresupuesto(Double presupuesto) {
        this.presupuesto = presupuesto;
    }

    @Basic
    @Column(name = "proyFinalizados", nullable = false)
    public List<String> getProyFinalizados() {
        return proyFinalizados;
    }

    public void setProyFinalizados(List<String> proyFinalizados) {
        this.proyFinalizados = proyFinalizados;
    }
    @Basic
    @Column(name = "proyDesarrollo", nullable = false)
    public List<String> getProyDesarrollo() {
        return proyDesarrollo;
    }

    public void setProyDesarrollo(List<String> proyDesarrollo) {
        this.proyDesarrollo = proyDesarrollo;
    }
    @Basic
    @Column(name = "presupuestoAnual", nullable = false)
    public Double getPresupuestoAnual() {
        return presupuestoAnual;
    }

    public void setPresupuestoAnual(Double presupuestoAnual) {
        this.presupuestoAnual = presupuestoAnual;
    }
    @Basic
    @Column(name = "historialJefes", nullable = false)
    public List<String> getHistorialJefes() {
        return historialJefes;
    }

    public void setHistorialJefes(List<String> historialJefes) {
        this.historialJefes = historialJefes;
    }
}
