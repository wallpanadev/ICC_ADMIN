package com.boleteria.model;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class VentCliente implements Serializable {
    private Integer idCliente;
    private TabPersona idPersona;
    private Boolean estadoCliente;
}