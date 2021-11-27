package mapper;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public abstract class BaseMapper<T, DTO> {
    public Optional<List<Optional<T>>> fromDTO(List<DTO> items) {
        // hace falta meter cosas al dto
        return Optional.of(items.stream().map(this::fromDTO).collect(Collectors.toList()));
    }

    public abstract Optional<T> fromDTO(DTO item);

    public Optional<List<Optional<DTO>>> toDTO(Optional<List<T>> items) {
        //falta dto
        return Optional.of(items.get().stream().map(this::toDTO).collect(Collectors.toList()));
    }

    public abstract Optional<DTO> toDTO(T item);
}

