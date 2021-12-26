package com.boleteria.model;

import java.io.Serializable;

public class VentEquipaje implements Serializable {
    private Integer idEquipaje;
    private TabTipounidad idTipounidad;
    private VentReserva idReserva;
    private Integer cantidadEquipaje;
    private String descripcionEquipaje;
    private String codigoEquipaje;
    private Double valorEquipaje;
    private Boolean estadoEquipaje;
}