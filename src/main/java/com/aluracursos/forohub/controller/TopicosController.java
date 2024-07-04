package com.aluracursos.forohub.controller;

import com.aluracursos.forohub.domain.topico.DatosRegistroTopico;
import com.aluracursos.forohub.domain.topico.DatosRespuestaTopico;

import com.aluracursos.forohub.services.TopicosService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/topicos")
@Tag(name = "Tópicos", description = "Maneja operaciones CRUD para topicos, incluyendo creación, actualización y eliminación")
public class TopicosController {
    @Autowired
    TopicosService topicosService;

    @PostMapping
    @Transactional
    @Operation(summary = "Registra un nuevo tópico")
    public ResponseEntity<DatosRespuestaTopico> registrarTopico(@RequestBody @Valid DatosRegistroTopico datosRegistroTopico, UriComponentsBuilder uriComponentsBuilder){
        DatosRespuestaTopico datosRespuestaTopico = topicosService.registrar(datosRegistroTopico);
        URI url = uriComponentsBuilder.path("/topicos/{id}").buildAndExpand(datosRespuestaTopico.id()).toUri();
        return ResponseEntity.created(url).body(datosRespuestaTopico);
    }
}
