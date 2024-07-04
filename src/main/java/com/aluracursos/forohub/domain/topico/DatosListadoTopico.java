package com.aluracursos.forohub.domain.topico;

import com.aluracursos.forohub.domain.curso.DatosListadoCurso;
import com.aluracursos.forohub.domain.usuario.DatosListadoUsuario;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;

public record DatosListadoTopico(
        Long id,
        String titulo,
        String mensaje,
        LocalDateTime fechaCreacion,
        @JsonProperty("autor")
        DatosListadoUsuario datosListadoUsuario,
        @JsonProperty("curso")
        DatosListadoCurso datosListadoCurso
) {
    public DatosListadoTopico(Topico topico) {
        this(
                topico.getId(),
                topico.getTitulo(),
                topico.getMensaje(),
                topico.getFechaCreacion(),
                new DatosListadoUsuario(topico.getUsuario()),
                new DatosListadoCurso(topico.getCurso()));
    }
}
