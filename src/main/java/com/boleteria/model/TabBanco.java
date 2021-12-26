package com.boleteria.model;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class TabBanco implements Serializable {
    private Integer idBanco;
    private String nombreBanco;
    private Boolean estadoBanco;
}