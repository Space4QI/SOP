package org.example.main.mappers;

public interface ToResponseMapper<R, D> {
    R toResponse(D dto);
}

