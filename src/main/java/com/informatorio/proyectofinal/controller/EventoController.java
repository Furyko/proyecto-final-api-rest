package com.informatorio.proyectofinal.controller;

import javax.validation.Valid;
import com.informatorio.proyectofinal.entity.Evento;
import com.informatorio.proyectofinal.repository.EventoRepository;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping(value = "/eventos")
public class EventoController {

    private final EventoRepository eventoRepository;
    
    @Autowired
    public EventoController(EventoRepository eventoRepository) {
        this.eventoRepository = eventoRepository;
    }

    @GetMapping
    public @ResponseBody Iterable<Evento> obtenerEventos() {
        return eventoRepository.findAll();
    }

    @PostMapping
    public ResponseEntity<?> crearEvento(@Valid @RequestBody Evento evento) {
        return new ResponseEntity<>(eventoRepository.save(evento), HttpStatus.CREATED);
    }
}
