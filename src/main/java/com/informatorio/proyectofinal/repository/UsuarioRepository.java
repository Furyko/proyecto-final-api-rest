package com.informatorio.proyectofinal.repository;

import java.time.LocalDateTime;
import java.util.List;
import com.informatorio.proyectofinal.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    List<Usuario> findByfechaDeCreacionAfter(LocalDateTime fecha);
    List<Usuario> findByCiudad(String ciudad);
}