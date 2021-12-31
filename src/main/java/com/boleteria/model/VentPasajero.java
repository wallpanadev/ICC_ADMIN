package com.boleteria.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VentPasajero {
    private int idPasajero;
    private TabPersona idPersona;
    private VentReserva idReserva;
}
