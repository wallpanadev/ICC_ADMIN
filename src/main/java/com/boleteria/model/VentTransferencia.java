package com.boleteria.model;

import java.io.Serializable;
import java.sql.Timestamp;

public class VentTransferencia implements Serializable {
    private Integer idTransferencia;
    private TabBanco idBancos;
    private VentReserva idReserva;
    private String numcomprobanteTransferencia;
    private Double valorTransferencia;
    private Timestamp fecharegistroTransferencia;
    private Boolean estadoTransferencia;
}