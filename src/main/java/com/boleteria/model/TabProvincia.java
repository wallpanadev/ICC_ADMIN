package com.boleteria.model;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class TabProvincia implements Serializable {
    private String codProvincia;
    private String nombreProvincia;
    private Integer codtlfProvincia;
}