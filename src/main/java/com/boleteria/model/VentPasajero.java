package com.boleteria.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VentPasajero {
    private int idPasajero;
    private TabPersona idPersona;
    private VentReserva idReserva;

    public VentPasajero(TabPersona idPersona, VentReserva idReserva) {
        this.idPersona = idPersona;
        this.idReserva = idReserva;
    }
}
