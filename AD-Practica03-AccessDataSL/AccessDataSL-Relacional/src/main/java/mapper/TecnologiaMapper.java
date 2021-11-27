package mapper;

import dto.TecnologiaDTO;
import model.Commit;
import model.Tecnologia;

import java.util.List;
import java.util.Optional;

public class TecnologiaMapper extends BaseMapper<Tecnologia, TecnologiaDTO> {

    @Override
    public Optional<Tecnologia> fromDTO(TecnologiaDTO item) {
        return Optional.ofNullable(Tecnologia.builder()
                .idTecnologia(item.getIdTecnologia())
                .nombre(item.getNombre())
                .build());
    }

    @Override
    public Optional<TecnologiaDTO> toDTO(Tecnologia item) {
        return Optional.ofNullable(TecnologiaDTO.builder()
                .idTecnologia(item.getIdTecnologia())
                .nombre(item.getNombre())
                .build());
    }
}