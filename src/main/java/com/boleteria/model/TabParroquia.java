package com.boleteria.model;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class TabParroquia implements Serializable {
    private String codParroquia;
    private TabCiudad codCiudad;
    private String nombreParroquia;
}