package com.boleteria.model;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class BltRutaparada implements Serializable {
    private Integer idRutaparada;
    private BltRuta idRuta;
    private BltParada idParadaIni;
    private BltParada idParadaFin;
    private Double tarifanormalRutapadara;
    private Double tarifaespecialRutapadara;
    private Integer tiempollegadaRutapadara;
    private Integer duracionviajeRutapadara;
    private Boolean estadoRutapadara;
}