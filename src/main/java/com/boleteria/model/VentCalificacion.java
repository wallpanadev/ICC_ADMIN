package com.boleteria.model;

import java.io.Serializable;
import java.util.Set;

public class VentCalificacion implements Serializable {
    private Integer idCalificacion;
    private VentFactura idFactura;
    private Double valorCalificacion;
    private Double baseCalificacion;
    private String comentarioCalificacion;
    private Boolean estadoCalificacion;
    private Set<ImbaEmblema> imbaEmblemas;
}