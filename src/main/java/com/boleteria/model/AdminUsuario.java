package com.boleteria.model;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Set;

public class AdminUsuario implements Serializable {
    private Integer idUsuario;
    private TabPersona idPersona;
    private String nickUsuario;
    private byte[] passwordUsuario;
    private Timestamp fecharegistroUsuario;
    private Boolean estadoUsuario;
    private Set<AdminRolusuario> adminRolUsuarioList;
}