package com.informatorio.proyectofinal.controller;

import java.time.LocalDate;
import java.util.List;
import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;
import com.informatorio.proyectofinal.entity.Usuario;
import com.informatorio.proyectofinal.repository.UsuarioRepository;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping(path = "/usuarios")
public class UsuarioController {

    private final UsuarioRepository usuarioRepository;
    
    @Autowired
    public UsuarioController(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @GetMapping
    public ResponseEntity<?> obtenerUsuarios(
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fechaDesde,
            @RequestParam(name = "ciudadDe", required = false) String ciudadDe) {
        if (fechaDesde != null) {
            List<Usuario> usuarios = usuarioRepository.findByfechaDeCreacionAfter(fechaDesde.atStartOfDay());
            return new ResponseEntity<>(usuarios, HttpStatus.OK);
        } else if (ciudadDe != null) {
            List<Usuario> usuarios = usuarioRepository.findByCiudad(ciudadDe);
            return new ResponseEntity<>(usuarios, HttpStatus.OK);
        }
        return new ResponseEntity<>(usuarioRepository.findAll(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> crearUsuario(@Valid @RequestBody Usuario usuario) {
        return new ResponseEntity<>(usuarioRepository.save(usuario), HttpStatus.CREATED);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Long> eliminarUsuario(@PathVariable("id") Long id) {
        usuarioRepository.deleteById(id);
        return new ResponseEntity<>(id, HttpStatus.OK);
    }

    @PutMapping("{id}")
    public ResponseEntity<Usuario> actualizarUsuario(@PathVariable("id") Long id, @Valid @RequestBody Usuario detallesUsuario) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("No se encontró usuario con el id " + "'" + id + "'"));
        usuario.setNombre(detallesUsuario.getNombre());
        usuario.setApellido(detallesUsuario.getApellido());
        usuario.setEmail(detallesUsuario.getEmail());
        usuario.setPassword(detallesUsuario.getPassword());
        usuario.setCiudad(detallesUsuario.getCiudad());
        usuario.setProvincia(detallesUsuario.getProvincia());
        usuario.setPais(detallesUsuario.getPais());
        usuario.setTipo(detallesUsuario.getTipo());
        final Usuario usuarioActualizado = usuarioRepository.save(usuario);
        return ResponseEntity.ok(usuarioActualizado);
    }

    //@PostMapping
    //public Usuario crearUsuario(@RequestBody Usuario usuario) {
    //    return usuarioRepository.save(usuario);
    //}
}
