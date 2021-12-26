package com.boleteria.model;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class TabParametro implements Serializable {
    private Integer idParametro;
    private String nombreParametro;
    private String valorParametro;
    private String fileParametro;
    private Boolean estadoParametro;
}