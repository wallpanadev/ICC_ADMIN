package com.boleteria.model;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalTime;

@Getter
@Setter
public class BltHorariodetalle implements Serializable {
    private int idHorariodetalle;
    private BltHorario idHorario;
    private BltRutaparada idRutaparada;
    private Integer cantpasajeroHorariodetalle;
    private LocalTime horasalidaHorariodetalle;

    public LocalTime obtenerDuracionViaje() {
        return horasalidaHorariodetalle.plusMinutes(idRutaparada.getDuracionviajeRutapadara());
    }
}