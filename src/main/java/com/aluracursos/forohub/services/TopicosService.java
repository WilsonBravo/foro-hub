package com.aluracursos.forohub.services;

import com.aluracursos.forohub.domain.curso.Curso;
import com.aluracursos.forohub.domain.curso.CursoRepository;
import com.aluracursos.forohub.domain.topico.*;
import com.aluracursos.forohub.domain.topico.validaciones.ValidacionesTopicos;
import com.aluracursos.forohub.domain.usuario.Usuario;
import com.aluracursos.forohub.domain.usuario.UsuarioRepository;
import com.aluracursos.forohub.infra.errores.ValidacionDeIntegridad;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TopicosService {
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private CursoRepository cursoRepository;
    @Autowired
    private TopicoRepository topicoRepository;
    @Autowired
    private List<ValidacionesTopicos> validadores;

    public DatosRespuestaTopico registrar(DatosRegistroTopico datosRegistroTopico){
        if(!usuarioRepository.findById(datosRegistroTopico.autorId()).isPresent()){
            throw new ValidacionDeIntegridad("no existe un usuario con el id "+datosRegistroTopico.autorId());
        }

        if(!cursoRepository.findById(datosRegistroTopico.cursoId()).isPresent()){
            throw new ValidacionDeIntegridad("no existe un curso con el id "+datosRegistroTopico.cursoId());
        }

        validadores.forEach(v -> v.validar(datosRegistroTopico));

        Optional<Usuario> usuario = usuarioRepository.findById(datosRegistroTopico.autorId());
        Optional<Curso> curso = cursoRepository.findById(datosRegistroTopico.cursoId());

        Topico topico = topicoRepository.save(
                new Topico(datosRegistroTopico, usuario.get(), curso.get())
        );
        DatosRespuestaTopico datosRespuestaTopico = new DatosRespuestaTopico(
                topico.getId(), topico.getTitulo(), topico.getMensaje(), topico.getFechaCreacion()
        );

        return datosRespuestaTopico;
    }

    public Page<DatosListadoTopico> listarTopicos(Pageable paginacion) {
        return topicoRepository.findAll(paginacion).map(DatosListadoTopico::new);
    }

    public DatosListadoTopico detallesTopico(Long id) {
        Optional<Topico> topico = topicoRepository.findById(id);
        DatosListadoTopico datosTopico = new DatosListadoTopico(
          topico.get()
        );
        return datosTopico;
    }

    public DatosRespuestaTopico actualizarTopico(Long id, DatosRegistroTopico datosTopico) {
        if(!usuarioRepository.findById(datosTopico.autorId()).isPresent()){
            throw new ValidacionDeIntegridad("no existe un usuario con el id "+datosTopico.autorId());
        }

        if(!cursoRepository.findById(datosTopico.cursoId()).isPresent()){
            throw new ValidacionDeIntegridad("no existe un curso con el id "+datosTopico.cursoId());
        }

        validadores.forEach(v -> v.validar(datosTopico));

        Optional<Topico> topico = topicoRepository.findById(id);
        Optional<Usuario> usuario = usuarioRepository.findById(datosTopico.autorId());
        Optional<Curso> curso = cursoRepository.findById(datosTopico.cursoId());
        topico.get().actualizarDatos(datosTopico, usuario.get(), curso.get());
        return new DatosRespuestaTopico(topico.get());
    }
}
