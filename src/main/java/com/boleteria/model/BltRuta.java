package com.boleteria.model;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class BltRuta implements Serializable {
    private Integer idRuta;
    private TabCiudad codCiudadIni;
    private TabCiudad codCiudadFin;
    private ImbaOperadora idOperadora;
    private String nombreRuta;
    private Double precioRuta;
    private Integer duracionviajeRuta;
    private String diaslaborRuta;
    private Boolean estadoRuta;
}