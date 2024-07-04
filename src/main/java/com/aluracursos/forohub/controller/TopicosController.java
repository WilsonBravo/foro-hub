package com.aluracursos.forohub.controller;

import com.aluracursos.forohub.domain.topico.DatosListadoTopico;
import com.aluracursos.forohub.domain.topico.DatosRegistroTopico;
import com.aluracursos.forohub.domain.topico.DatosRespuestaTopico;

import com.aluracursos.forohub.domain.topico.Topico;
import com.aluracursos.forohub.services.TopicosService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
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
    @Operation(
            summary = "Registra un nuevo tópico",
            description = "",
            tags = {"topico", "post"}
    )
    public ResponseEntity<DatosRespuestaTopico> registrarTopico(@RequestBody @Valid DatosRegistroTopico datosRegistroTopico, UriComponentsBuilder uriComponentsBuilder) {
        DatosRespuestaTopico datosRespuestaTopico = topicosService.registrar(datosRegistroTopico);
        URI url = uriComponentsBuilder.path("/topicos/{id}").buildAndExpand(datosRespuestaTopico.id()).toUri();
        return ResponseEntity.created(url).body(datosRespuestaTopico);
    }

    @GetMapping
    @Operation(
            summary = "Obtener listado de tópicos",
            description = "",
            tags = {"topico", "get"}
    )
    public ResponseEntity<Page<DatosListadoTopico>> listadoTopicos(@PageableDefault Pageable paginacion){
        return ResponseEntity.ok(topicosService.listarTopicos(paginacion));
    }

    @GetMapping("/{id}")
    @Operation(
            summary = "Obtener detalles de un tópico",
            description = "",
            tags = {"topico", "get"}
    )
    public ResponseEntity<DatosListadoTopico> detallesTopico(@PathVariable Long id){
        DatosListadoTopico datosTopico = topicosService.detallesTopico(id);
        return ResponseEntity.ok(datosTopico);
    }

    @PutMapping("/{id}")
    @Transactional
    @Operation(
            summary = "Actualizar tópico",
            description = "",
            tags = {"topico", "put"}
    )
    public ResponseEntity<DatosRespuestaTopico> actualizarTopico(@PathVariable Long id, @RequestBody @Valid DatosRegistroTopico datosRegistroTopico){
        DatosRespuestaTopico datosRespuestaTopico = topicosService.actualizarTopico(id, datosRegistroTopico);
        return ResponseEntity.ok(datosRespuestaTopico);
    }

    @DeleteMapping("/{id}")
    @Transactional
    @Operation(
            summary = "Eliminar tópico",
            description = "",
            tags = {"topico", "delete"}
    )
    public ResponseEntity eliminarTopico(@PathVariable Long id){
        topicosService.eliminarTopico(id);
        return ResponseEntity.noContent().build();
    }

}
