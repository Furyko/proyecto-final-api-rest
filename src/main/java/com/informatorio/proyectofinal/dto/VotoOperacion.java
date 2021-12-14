package com.informatorio.proyectofinal.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.informatorio.proyectofinal.entity.GeneradoPor;

public class VotoOperacion {

    private GeneradoPor generadoPor;

    @NotNull
    @Positive
    @JsonProperty(value = "usuario_id")
    private Long usuario_id;

    public GeneradoPor getGeneradoPor() {
        return generadoPor;
    }

    public void setGeneadoPor(GeneradoPor generadoPor) {
        this.generadoPor = generadoPor;
    }

    public Long getUsuario_id() {
        return usuario_id;
    }

    public void setUsuario_id(Long usuario_id) {
        this.usuario_id = usuario_id;
    }
}