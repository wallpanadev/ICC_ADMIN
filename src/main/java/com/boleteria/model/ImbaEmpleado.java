package com.boleteria.model;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class ImbaEmpleado implements Serializable {
    private Integer idEmpleado;
    private TabPersona idPersona;
    private ImbaCargo idCargo;
    private String correoEmpleado;
    private String celularEmpleado;
    private Boolean estadoEmpleado;
}