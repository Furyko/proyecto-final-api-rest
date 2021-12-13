package com.informatorio.proyectofinal.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import com.fasterxml.jackson.annotation.JsonProperty;

public class EmprendimientoOperacion {
    @NotEmpty(message = "El nombre no puede estar vacio")
    private String nombre;
    
    private String descripcion;
    
    private String contenido;
    
    private double objetivo;
    
    private boolean publicado;
    
    private String urlCapturas;
    
    private String tags;
    
    @NotNull
    @Positive
    @JsonProperty(value = "usuario_id")
    private Long usuario_id;

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

    public Long getUsuario_id() {
        return usuario_id;
    }

    public void setUsuario_id(Long usuario_id) {
        this.usuario_id = usuario_id;
    }
}
