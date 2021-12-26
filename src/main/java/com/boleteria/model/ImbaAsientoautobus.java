package com.boleteria.model;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class ImbaAsientoautobus implements Serializable {
    private Integer idAsientoautobus;
    private Integer idAutobus;
    private Integer numAsientoautobus;
    private Boolean ocupadoAsientoautobus;
    private Boolean valueOcupadoAsientoautobus;
    private String iconoAsiento;
    private String anchoAsiento;
}