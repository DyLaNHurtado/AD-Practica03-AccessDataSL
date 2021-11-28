package mapper;

import dto.ProyectoDTO;
import model.Proyecto;

import java.util.Optional;

public class ProyectoMapper extends BaseMapper<Proyecto, ProyectoDTO> {

    @Override
    public Optional<Proyecto> fromDTO(ProyectoDTO item) {
        return Optional.ofNullable(Proyecto.builder()
                .idProyecto(item.getIdProyecto())
                .nombre(item.getNombre())
                .idJefe(item.getIdJefe())
                .presupuesto(item.getPresupuesto())
                .fechaInicio(item.getFechaInicio())
                .fechaFin(item.getFechaFin())
                .tecnologias(item.getTecnologias())
                .idRepositorio(item.getIdRepositorio())
                        .idDepartamento(item.getIdProyecto())
                .build());
    }

    @Override
    public Optional<ProyectoDTO> toDTO(Proyecto item) {
        return Optional.ofNullable(ProyectoDTO.builder()
                .idProyecto(item.getIdProyecto())
                .nombre(item.getNombre())
                .idJefe(item.getIdJefe())
                .presupuesto(item.getPresupuesto())
                .fechaInicio(item.getFechaInicio())
                .fechaFin(item.getFechaFin())
                .tecnologias(item.getTecnologias())
                .idRepositorio(item.getIdRepositorio())
                .build());
    }
}
