package com.informatorio.proyectofinal.controller;

import javax.validation.Valid;
import javax.persistence.EntityNotFoundException;
import com.informatorio.proyectofinal.entity.Voto;
import com.informatorio.proyectofinal.dto.VotoOperacion;
import com.informatorio.proyectofinal.repository.VotoRepository;
import com.informatorio.proyectofinal.entity.Usuario;
import com.informatorio.proyectofinal.repository.UsuarioRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/votos")
public class VotoController {

    private final VotoRepository votoRepository;
    private final UsuarioRepository usuarioRepository;

    @Autowired
    public VotoController(VotoRepository votoRepository, UsuarioRepository usuarioRepository) {
        this.votoRepository = votoRepository;
        this.usuarioRepository = usuarioRepository;
    }

    @GetMapping
    public @ResponseBody Iterable<Voto> obtenerVotos() {
        return votoRepository.findAll();
    }

    @PostMapping
    public ResponseEntity<?> crearVoto(@Valid @RequestBody VotoOperacion votoOperacion) {
        Usuario usuario = usuarioRepository.findById(votoOperacion.getUsuario_id())
                .orElseThrow(() -> new EntityNotFoundException("No se encontró el usuario"));
            Voto voto = new Voto();
            voto.setGeneradoPor(votoOperacion.getGeneradoPor());
            voto.setUsuario(usuario);
        return new ResponseEntity<>(votoRepository.save(voto), HttpStatus.CREATED);
    }

}