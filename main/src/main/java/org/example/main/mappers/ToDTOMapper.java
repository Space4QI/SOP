package org.example.main.mappers;

public interface ToDTOMapper<R, D> {
    D toDto(R request);
}
