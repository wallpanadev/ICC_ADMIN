package com.boleteria.model;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.sql.Timestamp;

@Getter
@Setter
public class ImbaSocioautobus implements Serializable {
    private Integer idSocioautobus;
    private ImbaSocio idSocio;
    private ImbaAutobus idAutobus;
    private ImbaOperadora idOperadora;
    private Timestamp fecharegistroSocioautobus;
    private Boolean estadoSocioautobus;
}