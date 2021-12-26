package com.boleteria.model;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class TabCiudad implements Serializable {
    private String codCiudad;
    private TabProvincia codProvincia;
    private String nombreCiudad;
}