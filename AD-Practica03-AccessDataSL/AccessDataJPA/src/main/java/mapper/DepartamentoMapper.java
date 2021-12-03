package mapper;

import dto.DepartamentoDTO;
import dao.Commit;
import dao.Departamento;

import java.util.List;
import java.util.Optional;

public class DepartamentoMapper extends BaseMapper<Departamento, DepartamentoDTO> {
    @Override
    public Optional<Departamento> fromDTO(DepartamentoDTO item) {
        return Optional.ofNullable(Departamento.builder()
                .idDepartamento(item.getIdDepartamento())
                .nombre(item.getNombre())
                .idJefe(item.getIdJefe())
                        .trabajadores(item.getTrabajadores())
                .presupuesto(item.getPresupuesto())
                .proyFinalizados(item.getProyFinalizados())
                .proyDesarrollo(item.getProyDesarrollo())
                .presupuestoAnual(item.getPresupuestoAnual())
                .historialJefes(item.getHistorialJefes())
                .build());
    }

    @Override
    public Optional<DepartamentoDTO> toDTO(Departamento item) {
        return Optional.ofNullable(DepartamentoDTO.builder()
                .idDepartamento(item.getIdDepartamento())
                .nombre(item.getNombre())
                .idJefe(item.getIdJefe())
                        .trabajadores(item.getTrabajadores())
                .presupuesto(item.getPresupuesto())
                .proyFinalizados(item.getProyFinalizados())
                .proyDesarrollo(item.getProyDesarrollo())
                .presupuestoAnual(item.getPresupuestoAnual())
                .historialJefes(item.getHistorialJefes())
                .build());
    }
}

