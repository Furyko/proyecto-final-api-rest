package com.informatorio.proyectofinal.controller;

import java.time.LocalDate;
import java.util.List;
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
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fechaDesde) {
        if (fechaDesde != null) {
            List<Usuario> usuarios = usuarioRepository.findByfechaDeCreacionAfter(fechaDesde.atStartOfDay());
            return new ResponseEntity<>(usuarios, HttpStatus.OK);
        }
        return new ResponseEntity<>(usuarioRepository.findAll(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> crearUsuario(@Valid @RequestBody Usuario usuario) {
        return new ResponseEntity<>(usuarioRepository.save(usuario), HttpStatus.CREATED);
    }

    //@DeleteMapping("{id}")
    //public ResponseEntity<?> eliminarUsuario(@PathVariable Long id) {}

    //@GetMapping
    //public @ResponseBody Iterable<Usuario> obtenerUsuariosDespuesDeFechaDada(
    //        @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fechaDesde) {
    //            List<Emprendimientos emprendimientos 
    //   return usuarioRepository.findAll();
    //}

    //@PostMapping
    //public Usuario crearUsuario(@RequestBody Usuario usuario) {
    //    return usuarioRepository.save(usuario);
    //}
}
