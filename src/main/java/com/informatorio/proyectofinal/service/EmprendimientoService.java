package com.informatorio.proyectofinal.service;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import com.informatorio.proyectofinal.dto.EmprendimientoOperacion;
import com.informatorio.proyectofinal.entity.Emprendimiento;
import com.informatorio.proyectofinal.entity.Tag;
import com.informatorio.proyectofinal.entity.Usuario;
import com.informatorio.proyectofinal.repository.EmprendimientoRepository;
import com.informatorio.proyectofinal.repository.TagRepository;
import com.informatorio.proyectofinal.repository.UsuarioRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class EmprendimientoService {
    private final EmprendimientoRepository emprendimientoRepository;
    private final UsuarioRepository usuarioRepository;
    private final TagRepository tagRepository;

    @Autowired
    public EmprendimientoService(EmprendimientoRepository emprendimientoRepository, 
                                    UsuarioRepository usuarioRepository,
                                    TagRepository tagRepository) {
        this.emprendimientoRepository = emprendimientoRepository;
        this.usuarioRepository = usuarioRepository;
        this.tagRepository = tagRepository;
    }
    
    public List<Emprendimiento> obtenerTodos() {
        return emprendimientoRepository.findAll();
    }

    public Emprendimiento crearEmprendimiento(EmprendimientoOperacion emprendimientoOperacion) {
        Usuario usuario = usuarioRepository.findById(emprendimientoOperacion.getUsuario_id())
                .orElseThrow(() -> new EntityNotFoundException("No se encontró el usuario"));
        List<Tag> tags = tagRepository.findAllById(emprendimientoOperacion.getTags());
        Emprendimiento emprendimiento = new Emprendimiento();
        emprendimiento.setNombre(emprendimientoOperacion.getNombre());
        emprendimiento.setDescripcion(emprendimientoOperacion.getDescripcion());
        emprendimiento.setContenido(emprendimientoOperacion.getContenido());
        emprendimiento.setObjetivo(emprendimientoOperacion.getObjetivo());
        emprendimiento.setPublicado(emprendimientoOperacion.getPublicado());
        emprendimiento.setUrlCapturas(emprendimientoOperacion.getUrlCapturas());
        emprendimiento.setUsuario(usuario);
        emprendimiento.getTags().addAll(tags);

        return emprendimientoRepository.save(emprendimiento);
    }

    public ResponseEntity<Long> eliminarEmprendimiento(Long id) {
        emprendimientoRepository.deleteById(id);
        return new ResponseEntity<>(id, HttpStatus.OK);
    }

    public ResponseEntity<Emprendimiento> actualizarEmprendimiento(Long id, Emprendimiento detallesEmprendimiento){
        Emprendimiento emprendimiento = emprendimientoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("No se encontró emprendimiento con el id " + "'" + id + "'"));
        emprendimiento.setNombre(detallesEmprendimiento.getNombre());
        emprendimiento.setDescripcion(detallesEmprendimiento.getDescripcion());
        emprendimiento.setContenido(detallesEmprendimiento.getContenido());
        emprendimiento.setObjetivo(detallesEmprendimiento.getObjetivo());
        emprendimiento.setPublicado(detallesEmprendimiento.getPublicado());
        emprendimiento.setUrlCapturas(detallesEmprendimiento.getUrlCapturas());
        //emprendimiento.setTags(detallesEmprendimiento.getTags());
        final Emprendimiento emprendimientoActualizado = emprendimientoRepository.save(emprendimiento);
        return ResponseEntity.ok(emprendimientoActualizado);
    }
}