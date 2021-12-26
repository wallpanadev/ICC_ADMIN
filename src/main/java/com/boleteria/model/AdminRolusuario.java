package com.boleteria.model;

import java.io.Serializable;

public class AdminRolusuario implements Serializable {
    private Integer idRolusuario;
    private AdminRol idRol;
    private AdminUsuario idUsuario;
    private AdminPrivilegio idPrivilegio;
}