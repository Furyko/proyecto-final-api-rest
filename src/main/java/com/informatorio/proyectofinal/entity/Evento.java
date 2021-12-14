package com.informatorio.proyectofinal.entity;

import java.time.LocalDateTime;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.annotations.CreationTimestamp;

@Entity
public class Evento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String detalles;
    
    @CreationTimestamp
    private LocalDateTime fechaDeCreacion;
    
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime fechaDeCierre;
    
    @Enumerated(EnumType.STRING)
    private EstadoEvento estado;
    
    private String suscriptores;
    
    private double premio;

    public Long getId() {
        return id;
    }

    public String getDetalles() {
        return detalles;
    }

    public void setDetalles(String detalles) {
        this.detalles = detalles;
    }

    public LocalDateTime getFechaDeCreacion() {
        return fechaDeCreacion;
    }

    public void setFechaDeCreacion(LocalDateTime fechaDeCreacion) {
        this.fechaDeCreacion = fechaDeCreacion;
    }

    public LocalDateTime getFechaDeCierre() {
        return fechaDeCierre;
    }

    public void setFechaDeCierre(LocalDateTime fechaDeCierre) {
        this.fechaDeCierre = fechaDeCierre;
    }

    public String getSuscriptores() {
        return suscriptores;
    }

    public void setSuscriptores(String suscriptores) {
        this.suscriptores = suscriptores;
    }

    public double getPremio() {
        return premio;
    }

    public void setPremio(double premio) {
        this.premio = premio;
    }
}

