package com.boleteria.util;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class ListaPasajero implements Serializable {
    private int idReserva;
    private int num;
    private String codigo;
    private String identificacion;
    private String nombres;
    private String destino;
    private String asientos;
    private boolean estadoPago;

    public ListaPasajero() {
    }

    public ListaPasajero(int idReserva, int num, String codigo, String identificacion, String nombres, String destino, String asientos, boolean estadoPago) {
        this.idReserva = idReserva;
        this.num = num;
        this.codigo = codigo;
        this.identificacion = identificacion;
        this.nombres = nombres;
        this.destino = destino;
        this.asientos = asientos;
        this.estadoPago = estadoPago;
    }
}