package com.boleteria.model;

import java.io.Serializable;
import java.sql.Timestamp;

public class ImbaSocio implements Serializable {
    private Integer idSocio;
    private TabPersona idPersona;
    private String correoSocio;
    private String celularSocio;
    private Timestamp fecharegistroSocio;
    private Boolean estadoSocio;
}