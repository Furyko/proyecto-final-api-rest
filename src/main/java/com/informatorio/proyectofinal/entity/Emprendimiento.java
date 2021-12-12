package com.informatorio.proyectofinal.entity;

import java.time.LocalDateTime;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import org.hibernate.annotations.CreationTimestamp;

@Entity
public class Emprendimiento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotEmpty(message = "El nombre no puede esta vacio")
    private String nombre;
    
    private String descripcion;
    
    private String contenido;
    
    @CreationTimestamp
    private LocalDateTime fechaDeCreacion;
    
    private double objetivo;
    
    private boolean publicado;
    
    private String urlCapturas;
    
    private String tags;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    public LocalDateTime getFechaDeCreacion() {
        return fechaDeCreacion;
    }

    public void setFechaDeCreacion(LocalDateTime fechaDeCreacion) {
        this.fechaDeCreacion = fechaDeCreacion;
    }

    public double getObjetivo() {
        return objetivo;
    }

    public void setObjetivo(double objetivo) {
        this.objetivo = objetivo;
    }

    public boolean getPublicado() {
        return publicado;
    }

    public void setPublicado(boolean publicado) {
        this.publicado = publicado;
    }

    public String getUrlCapturas() {
        return urlCapturas;
    }

    public void setUrlCapturas(String urlCapturas) {
        this.urlCapturas = urlCapturas;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }
}