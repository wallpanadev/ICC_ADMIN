package com.boleteria.model;

import java.io.Serializable;

public class VentEncomienda implements Serializable {
    private Integer idEncomienda;
    private VentRecibeenc idRecibeenc;
    private VentReserva idReserva;
    private Boolean recibidoEncomienda;
    private Boolean entregadoEncomienda;
}