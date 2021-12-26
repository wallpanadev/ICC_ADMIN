package com.boleteria.model;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class ImbaTipoviaje implements Serializable {
    private Integer idTipoviaje;
    private String nombreTipoviaje;
    private String codigoTipoviaje;
    private Boolean estadoTipoviaje;
}