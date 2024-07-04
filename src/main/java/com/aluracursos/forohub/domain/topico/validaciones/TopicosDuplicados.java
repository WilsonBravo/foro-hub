package com.aluracursos.forohub.domain.topico.validaciones;

import com.aluracursos.forohub.domain.topico.DatosRegistroTopico;
import com.aluracursos.forohub.domain.topico.Topico;
import com.aluracursos.forohub.domain.topico.TopicoRepository;
import com.aluracursos.forohub.infra.validaciones.ValidadorReglasDeNegocio;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class TopicosDuplicados implements ValidacionesTopicos {
    @Autowired
    private TopicoRepository topicoRepository;

    @Override
    public void validar(DatosRegistroTopico datosRegistroTopico) {
        Optional<Topico> topico = topicoRepository.findByTituloAndMensaje(datosRegistroTopico.titulo(), datosRegistroTopico.mensaje());
        if (topico.isPresent()){
            throw new ValidationException("El tópico ya existe. No se permite duplicar topicos.");
        }
    }
}
