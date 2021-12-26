package com.boleteria.controller.tab;

import com.boleteria.controller.MensajeController;
import com.boleteria.controller.TabController;
import com.boleteria.enums.TipoMensaje;
import com.boleteria.exception.IncompleteFormException;
import com.boleteria.model.TabCiudad;
import com.boleteria.model.TabParroquia;
import com.boleteria.util.Cronometro;

import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;

@Named("parroquiaController")
@ViewScoped
public class TabParroquiaController extends Cronometro implements Serializable {
    private final TabController tabController = new TabController();

    private TabParroquia parroquia;
    private ArrayList<TabParroquia> parroquiaList;

    private ArrayList<TabCiudad> ciudadList;

    private String codCiudad;
    private String ciudadView;

    public TabParroquiaController() {
        parroquiaList = tabController.listarTabParroquias();
        ciudadList = tabController.listarTabCiudades();
    }

    public void agregarParroquia() {
        parroquia = new TabParroquia();
        codCiudad = null;
        ciudadView = null;
    }

    public void recargarTabla() {
        if (segundo == 0) {
            ejecutar(); // Incia el cronometro
            ciudadList = tabController.listarTabCiudades();
            MensajeController.contruirMensaje(TipoMensaje.RECARGA);
        } else if (segundo < tiempoEspera){
            MensajeController.contruirMensaje(TipoMensaje.ESPERA, tiempoEspera, segundo);
        }
    }

    public void verParroquia(TabParroquia tabParroquia) {
        parroquia = tabParroquia;
        codCiudad = tabParroquia.getCodCiudad().getCodCiudad();
        ciudadView = tabParroquia.getCodCiudad().getNombreCiudad();
    }

    public void guardarActualizarParroquia() throws IncompleteFormException {
        parroquia.setCodCiudad(tabController.obtenerCiudadXId(codCiudad));
        if (validarFormulario()) {
            if (tabController.encontraParroquiaXId(parroquia.getCodParroquia())) {
                tabController.cudTabParroquia(parroquia, 'u');
                MensajeController.contruirMensaje(TipoMensaje.ACTUALIZAR);
            } else {
                tabController.cudTabParroquia(parroquia, 's');
                MensajeController.contruirMensaje(TipoMensaje.GUARDAR);
            }
            parroquia = new TabParroquia();
            parroquiaList = tabController.listarTabParroquias();
        } else {
            throw new IncompleteFormException("Formulario Incompleto");
        }
    }

    private boolean validarFormulario() {
        return parroquia.getCodParroquia() != null && parroquia.getCodParroquia().trim().length() != 0 &&
                parroquia.getNombreParroquia() != null && parroquia.getNombreParroquia().trim().length() != 0 &&
                parroquia.getCodCiudad() != null && parroquia.getCodCiudad().getCodCiudad().trim().length() != 0;
    }

    // --Getter & Setter
    public TabParroquia getParroquia() {
        return parroquia;
    }

    public void setParroquia(TabParroquia parroquia) {
        this.parroquia = parroquia;
    }

    public ArrayList<TabParroquia> getParroquiaList() {
        return parroquiaList;
    }

    public void setParroquiaList(ArrayList<TabParroquia> parroquiaList) {
        this.parroquiaList = parroquiaList;
    }

    public ArrayList<TabCiudad> getCiudadList() {
        return ciudadList;
    }

    public void setCiudadList(ArrayList<TabCiudad> ciudadList) {
        this.ciudadList = ciudadList;
    }

    public String getCodCiudad() {
        return codCiudad;
    }

    public void setCodCiudad(String codCiudad) {
        this.codCiudad = codCiudad;
    }

    public String getCiudadView() {
        return ciudadView;
    }

    public void setCiudadView(String ciudadView) {
        this.ciudadView = ciudadView;
    }
}