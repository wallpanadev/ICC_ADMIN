package com.boleteria.model;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class ImbaOperadora implements Serializable {
    private Integer idOperadora;
    private String nombreOperadora;
    private String codigoOperadora;
    private Boolean estadoOperadora;
}