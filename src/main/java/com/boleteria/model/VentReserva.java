package com.boleteria.model;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class VentReserva implements Serializable {
    private Integer idReserva;
    private VentCliente idCliente;
    private BltHorariodetalle idHorariodetalle;
    private VentFormapago idFormapago;
    private ImbaTipoviaje idTipoviaje;
    private ImbaEmpleado idEmpleado;
    private String asientosReserva;
    private String codalfaReserva;
    private String codqrReserva;
    private Boolean estadopagoReserva;
}