package com.boleteria.model;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class ImbaCargo implements Serializable {
    private Integer idCargo;
    private String nombreCargo;
    private Boolean estadoCargo;
}