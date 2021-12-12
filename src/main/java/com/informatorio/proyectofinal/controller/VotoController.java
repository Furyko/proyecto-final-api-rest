package com.informatorio.proyectofinal.controller;

import javax.validation.Valid;
import com.informatorio.proyectofinal.entity.Voto;
import com.informatorio.proyectofinal.repository.VotoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    @Autowired
    public VotoController(VotoRepository votoRepository) {
        this.votoRepository = votoRepository;
    }

    @GetMapping
    public @ResponseBody Iterable<Voto> obtenerVotos() {
        return votoRepository.findAll();
    }

    @PostMapping
    public ResponseEntity<?> crearVoto(@Valid @RequestBody Voto voto) {
        return new ResponseEntity<>(votoRepository.save(voto), HttpStatus.CREATED);
    }

}