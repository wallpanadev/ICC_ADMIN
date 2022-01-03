package com.boleteria.controller;

import com.boleteria.model.Usuario;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.enterprise.context.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import java.io.IOException;
import java.io.Serializable;

@Named
@SessionScoped
public class LoginController implements Serializable {
    private static final Logger LOGGER = LogManager.getLogger(LoginController.class);

    private final AdminController adminController = new AdminController();
    private final SessionUser sessionUser = new SessionUser();

    private Usuario usuario;

    public LoginController() {
        this.usuario = new Usuario();
    }

    public void verificarUsuarioAdmin() throws IOException {
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        if (sessionUser.obtenerUsuario(externalContext) == null) {
            usuario = new Usuario();
            externalContext.redirect(externalContext.getRequestContextPath() + "/inicio");
        } else {
            if (usuario.getIdAdminUsuario() <= 0 || !usuario.isLogin()) {
                usuario = new Usuario();
                externalContext.redirect(externalContext.getRequestContextPath() + "/inicio");
            }
        }
    }

    public void ingresarLogin() throws IOException {
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        if (sessionUser.obtenerUsuario(externalContext) != null) {
            usuario = sessionUser.obtenerUsuario(externalContext);
            if (usuario.getIdAdminUsuario() > 0 || usuario.isLogin()) {
                externalContext.redirect(externalContext.getRequestContextPath() + "/panel/inicio");
            }
        }
    }

    public void iniciarSesion() throws IOException {
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();//kcUv886-9Aqhw69
        Integer id = adminController.verificarUsuario(usuario.getUser(), usuario.getPassword());
        if (id != null && id > 0) {
            usuario.setIdAdminUsuario(id);
            usuario.setLogin(true);
            sessionUser.iniciarSesion(externalContext, usuario);
            externalContext.redirect(externalContext.getRequestContextPath() + "/panel/inicio");
        } else {
            usuario = new Usuario();
            LOGGER.error("El usuario o la contraseña son incorrectas");
        }
    }

    public void cerrarSesiones() throws IOException {
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        usuario = new Usuario();
        sessionUser.cerrarSesion(externalContext);
        externalContext.redirect(externalContext.getRequestContextPath() + "/inicio");
    }

    /*public void guardarUsuario() throws IOException {
        FacesContext fc = FacesContext.getCurrentInstance();

        usuario.setIdCliente(read.obtenerClienteXCedula(cliente.getIdentificacionCliente()));
        usuario.setPasswordUser(password.getBytes());
        create.guardarUsuario(usuario);

        sessionUser.iniciarSesion(globalParameters.getUserClient(), fc, usuario);

        if (sessionUser.obtenerUsuario(fc, globalParameters.getPageSession()) != null) {
            fc.getExternalContext().redirect("./confirmaVuelo.xhtml");
        } else {
            fc.getExternalContext().redirect("./../index.xhtml?faces-redirect=true");
        }
    }*/

    public Usuario getUsuario() {
        return usuario;
    }
}