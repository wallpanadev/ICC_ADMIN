package com.boleteria.controller;

import com.boleteria.util.Usuario;

import javax.enterprise.context.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.inject.Named;
import java.io.Serializable;

@Named
@SessionScoped
public class SessionUser implements Serializable {
    private static final String tipoUsuario = "userAdmin";

    public void iniciarSesion(ExternalContext externalContext, Usuario u) {
        externalContext.getSessionMap().put(tipoUsuario, u);
    }

    public void cerrarSesion(ExternalContext externalContext) {
        externalContext.getSessionMap().remove(tipoUsuario);
    }

    public Usuario obtenerUsuario(ExternalContext externalContext) {
        return (Usuario) externalContext.getSessionMap().get(tipoUsuario);
    }
}