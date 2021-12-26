package com.boleteria.model;

import java.io.Serializable;

public class VentPagopendiente implements Serializable {
    private Integer idPagopendiente;
    private VentReserva idReserva;
    private VentFormapago idFormapago;
    private ImbaEmpleado idEmpleado;
    private Double valorpagadoPagopendiente;
    private Double valordebePagopendiente;
    private Boolean pagadoPagopendiente;
}