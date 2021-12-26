package com.boleteria.model;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class TabDomcorreo implements Serializable {
    private Integer idDomcorreo;
    private String nombreDomcorreo;
    private Boolean estadoDomcorreo;
}