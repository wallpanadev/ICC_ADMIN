package com.boleteria.model;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
public class BltHorario implements Serializable {
    private Integer idHorario;
    private Integer idRuta;
    private ImbaAutobus idAutobus;
    private String asientosHorario;
    private Integer asientosdisponiblesHorario;
    private LocalDate fechaHorario;
    private LocalTime horaHorario;
    private Boolean estadoHorario;
}