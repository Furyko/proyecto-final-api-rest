package com.informatorio.proyectofinal.controller;

import java.util.List;
import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;
import com.informatorio.proyectofinal.dto.EmprendimientoOperacion;
import com.informatorio.proyectofinal.entity.Emprendimiento;
import com.informatorio.proyectofinal.entity.Usuario;
import com.informatorio.proyectofinal.entity.Tag;
import com.informatorio.proyectofinal.repository.EmprendimientoRepository;
import com.informatorio.proyectofinal.repository.TagRepository;
import com.informatorio.proyectofinal.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/emprendimientos")
public class EmprendimientoController {
    
    private final EmprendimientoRepository emprendimientoRepository;
    private final UsuarioRepository usuarioRepository;
    private final TagRepository tagRepository;

    @Autowired
    public EmprendimientoController(EmprendimientoRepository emprendimientoRepository, 
                                    UsuarioRepository usuarioRepository,
                                    TagRepository tagRepository) {
        this.emprendimientoRepository = emprendimientoRepository;
        this.usuarioRepository = usuarioRepository;
        this.tagRepository = tagRepository;
    }

    @GetMapping
    public @ResponseBody Iterable<Emprendimiento> obtenerUsuarios() {
        return emprendimientoRepository.findAll();
    }

    @PostMapping
    public ResponseEntity<?> crearEmprendimiento(@Valid @RequestBody EmprendimientoOperacion emprendimientoOperacion) {
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
        return new ResponseEntity<>(emprendimientoRepository.save(emprendimiento), HttpStatus.CREATED);
    }
}
