package com.informatorio.proyectofinal.controller;

import javax.validation.Valid;
import com.informatorio.proyectofinal.dto.EmprendimientoOperacion;
import com.informatorio.proyectofinal.entity.Emprendimiento;
import com.informatorio.proyectofinal.service.EmprendimientoService;
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
    
    private final EmprendimientoService emprendimientoService;

    @Autowired
    public EmprendimientoController(EmprendimientoService emprendimientoService) {
        this.emprendimientoService = emprendimientoService;
    }

    @GetMapping
    public @ResponseBody Iterable<Emprendimiento> obtenerUsuarios() {
        return emprendimientoService.obtenerTodos();
    }

    @PostMapping
    public ResponseEntity<?> crearEmprendimiento(@Valid @RequestBody EmprendimientoOperacion emprendimientoOperacion) {
        return new ResponseEntity<>(emprendimientoService.crearEmprendimiento(emprendimientoOperacion), HttpStatus.CREATED);
    }
}
