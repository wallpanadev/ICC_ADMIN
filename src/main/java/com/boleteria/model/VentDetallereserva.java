package com.boleteria.model;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class VentDetallereserva implements Serializable {
    private Integer idDetallereserva;
    private VentReserva idReserva;
    private ImbaTarifa idTarifa;
    private Integer cantidadDetallereserva;
    private Double valorunitarioDetallereserva;
    private Double valortotalDetallereserva;

    public VentDetallereserva(VentReserva idReserva, ImbaTarifa idTarifa, Integer cantidadDetallereserva, Double valorunitarioDetallereserva, Double valortotalDetallereserva) {
        this.idReserva = idReserva;
        this.idTarifa = idTarifa;
        this.cantidadDetallereserva = cantidadDetallereserva;
        this.valorunitarioDetallereserva = valorunitarioDetallereserva;
        this.valortotalDetallereserva = valortotalDetallereserva;
    }
}