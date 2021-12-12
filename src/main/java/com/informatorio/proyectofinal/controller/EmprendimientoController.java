package com.informatorio.proyectofinal.controller;

import javax.validation.Valid;
import com.informatorio.proyectofinal.entity.Emprendimiento;
import com.informatorio.proyectofinal.repository.EmprendimientoRepository;
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

    @Autowired
    public EmprendimientoController(EmprendimientoRepository emprendimientoRepository) {
        this.emprendimientoRepository = emprendimientoRepository;
    }

    @GetMapping
    public @ResponseBody Iterable<Emprendimiento> obtenerUsuarios() {
        return emprendimientoRepository.findAll();
    }

    @PostMapping
    public ResponseEntity<?> crearEmprendimiento(@Valid @RequestBody Emprendimiento emprendimiento) {
        return new ResponseEntity<>(emprendimientoRepository.save(emprendimiento), HttpStatus.CREATED);
    }
}
