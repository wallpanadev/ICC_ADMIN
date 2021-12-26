package com.boleteria.model;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class ImbaAutobus implements Serializable {
    private Integer idAutobus;
    private Integer numeroAutobus;
    private String placaAutobus;
    private Integer modeloAutobus;
    private String marcaAutobus;
    private Integer asientosAutobus;
    private String ordenasientoAutobus;
    private Boolean activoAutobus;
}