package com.boleteria.model;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class ImbaTarifa implements Serializable {
    private Integer idTarifa;
    private String nombreTarifa;
}