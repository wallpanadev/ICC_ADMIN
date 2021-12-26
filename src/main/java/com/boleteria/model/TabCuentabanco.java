package com.boleteria.model;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class TabCuentabanco implements Serializable {
    private Integer idCuentabanco;
    private TabBanco idBanco;
    private TabTipocuenta idTipocuenta;
    private String numcuentaBanco;
    private Boolean estadoCuentabanco;
}