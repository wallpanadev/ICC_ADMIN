package com.boleteria.controller.tab;

import com.boleteria.controller.MensajeController;
import com.boleteria.controller.TabController;
import com.boleteria.enums.TipoMensaje;
import com.boleteria.exception.IncompleteFormException;
import com.boleteria.model.TabDomcorreo;
import com.boleteria.util.Cronometro;

import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;

@Named("domcorreoController")
@ViewScoped
public class TabDomcorreoController extends Cronometro implements Serializable {
    private final TabController tabController = new TabController();

    private TabDomcorreo domcorreo;
    private ArrayList<TabDomcorreo> tabDomcorreoList;

    public TabDomcorreoController() {
        tabDomcorreoList = tabController.listarTabDomcorreos();
    }

    public void agregarDomcorreo() {
        domcorreo = new TabDomcorreo();
    }

    public void recargarTabla() {
        if (segundo == 0) {
            ejecutar(); // Incia el cronometro
            tabDomcorreoList = tabController.listarTabDomcorreos();
            MensajeController.contruirMensaje(TipoMensaje.RECARGA);
        } else if (segundo < tiempoEspera){
            MensajeController.contruirMensaje(TipoMensaje.ESPERA, tiempoEspera, segundo);
        }
    }

    public void verDomcorreo(TabDomcorreo tabDomcorreo) {
        domcorreo = tabDomcorreo;
    }

    public void guardarActualizarDomcorreo() throws IncompleteFormException {
        if (validarFormulario()) {
            if (domcorreo.getIdDomcorreo() == null) {
                domcorreo.setEstadoDomcorreo(true);
                tabController.cudTabDomcorreo(domcorreo, 's');
                MensajeController.contruirMensaje(TipoMensaje.GUARDAR);
            } else {
                tabController.cudTabDomcorreo(domcorreo, 'u');
                MensajeController.contruirMensaje(TipoMensaje.ACTUALIZAR);
            }
            domcorreo = new TabDomcorreo();
            tabDomcorreoList = tabController.listarTabDomcorreos();
        } else {
            throw new IncompleteFormException("Formulario Incompleto");
        }
    }

    public void eliminarDomcorreo() {
        tabController.cudTabDomcorreo(domcorreo, 'd');
        MensajeController.contruirMensaje(TipoMensaje.ELIMINAR);
        tabDomcorreoList = tabController.listarTabDomcorreos();
    }

    private boolean validarFormulario() {
        return domcorreo.getNombreDomcorreo() != null && domcorreo.getNombreDomcorreo().trim().length() != 0;
    }

    // --Getter & Setter
    public TabDomcorreo getDomcorreo() {
        return domcorreo;
    }

    public void setDomcorreo(TabDomcorreo domcorreo) {
        this.domcorreo = domcorreo;
    }

    public ArrayList<TabDomcorreo> getTabDomcorreoList() {
        return tabDomcorreoList;
    }

    public void setTabDomcorreoList(ArrayList<TabDomcorreo> tabDomcorreoList) {
        this.tabDomcorreoList = tabDomcorreoList;
    }
}