package com.boleteria.model;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class TabTipocuenta implements Serializable {
    private Integer idTipocuenta;
    private String nombreTipocuenta;
    private Boolean estadoTipocuenta;
}