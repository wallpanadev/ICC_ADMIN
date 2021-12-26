package com.boleteria.model;

import java.io.Serializable;
import java.sql.Timestamp;

public class VentFactura implements Serializable {
    private Integer idFactura;
    private VentReserva idReserva;
    private String numFactura;
    private Double subtotalFactura;
    private Double ivaFactura;
    private Double descuentoFactura;
    private Double totalFactura;
    private Boolean anulaFactura;
    private Timestamp fecharegistroFactura;
    private Timestamp fechaanulacionFactura;
}