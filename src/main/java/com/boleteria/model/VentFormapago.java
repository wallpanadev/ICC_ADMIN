package com.boleteria.model;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class VentFormapago implements Serializable {
    private Integer idFormapago;
    private String nombreFormapago;
    private Boolean estadoFormapago;
}