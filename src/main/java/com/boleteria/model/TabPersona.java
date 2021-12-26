package com.boleteria.model;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class TabPersona implements Serializable {
    private Integer idPersona;
    private TabCiudad codCiudad;
    private TabTipoidentificacion idTipoidentificacion;
    private String identificacionPersona;
    private String nombresPersona;
    private String apellidosPersona;
    private String direccionPersona;
    private String celularPersona;
    private String telefonoPersona;
    private String correoPersona;
    private Boolean esclientePersona;
    private Boolean essocioPersona;
    private Boolean esempleadoPersona;
    private Boolean espasajeroPersona;
    private Boolean estadoPersona;
}