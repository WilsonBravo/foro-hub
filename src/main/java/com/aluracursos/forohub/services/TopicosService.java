package com.aluracursos.forohub.services;

import com.aluracursos.forohub.domain.curso.Curso;
import com.aluracursos.forohub.domain.curso.CursoRepository;
import com.aluracursos.forohub.domain.topico.DatosRegistroTopico;
import com.aluracursos.forohub.domain.topico.DatosRespuestaTopico;
import com.aluracursos.forohub.domain.topico.Topico;
import com.aluracursos.forohub.domain.topico.TopicoRepository;
import com.aluracursos.forohub.domain.topico.validaciones.ValidacionesTopicos;
import com.aluracursos.forohub.domain.usuario.Usuario;
import com.aluracursos.forohub.domain.usuario.UsuarioRepository;
import com.aluracursos.forohub.infra.errores.ValidacionDeIntegridad;
import org.springframework.beans.factory.annotation.Autowired;
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
}
