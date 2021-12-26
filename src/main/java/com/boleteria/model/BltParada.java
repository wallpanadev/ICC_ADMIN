package com.boleteria.model;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class BltParada implements Serializable {
    private Integer idParada;
    private String nombreParada;
    private Double latitudParada;
    private Double longitudParada;
}