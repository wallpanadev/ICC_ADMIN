package com.boleteria.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Usuario {
    private int idAdminUsuario;
    private String user;
    private String password;
    private boolean login;
}