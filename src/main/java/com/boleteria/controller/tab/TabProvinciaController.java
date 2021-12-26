package com.boleteria.controller.tab;

import com.boleteria.controller.MensajeController;
import com.boleteria.controller.TabController;
import com.boleteria.enums.TipoMensaje;
import com.boleteria.exception.IncompleteFormException;
import com.boleteria.model.TabProvincia;
import com.boleteria.util.Cronometro;

import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;

@Named("provinciaController")
@ViewScoped
public class TabProvinciaController extends Cronometro implements Serializable {
    private final TabController tabController = new TabController();

    private TabProvincia provincia;
    private ArrayList<TabProvincia> provinciaList;

    public TabProvinciaController() {
        provinciaList = tabController.listarTabProvincias();
    }

    public void agregarProvincia() {
        provincia = new TabProvincia();
    }

    public void recargarTabla() {
        if (segundo == 0) {
            ejecutar(); // Incia el cronometro
            provinciaList = tabController.listarTabProvincias();
            MensajeController.contruirMensaje(TipoMensaje.RECARGA);
        } else if (segundo < tiempoEspera){
            MensajeController.contruirMensaje(TipoMensaje.ESPERA, tiempoEspera, segundo);
        }
    }

    public void verProvincia(TabProvincia tabProvincia) {
        provincia = tabProvincia;
    }

    public void guardarActualizarProvincia() throws IncompleteFormException {
        if (validarFormulario()) {
            if (provincia.getCodProvincia() == null) {
                tabController.cudTabProvincia(provincia, 's');
                MensajeController.contruirMensaje(TipoMensaje.GUARDAR);
            } else {
                tabController.cudTabProvincia(provincia, 'u');
                MensajeController.contruirMensaje(TipoMensaje.ACTUALIZAR);
            }
            provincia = new TabProvincia();
            provinciaList = tabController.listarTabProvincias();
        } else {
            throw new IncompleteFormException("Formulario Incompleto");
        }
    }

    private boolean validarFormulario() {
        return provincia.getCodProvincia() != null && provincia.getCodProvincia().trim().length() != 0 &&
                provincia.getNombreProvincia() != null && provincia.getNombreProvincia().trim().length() != 0 &&
                provincia.getCodtlfProvincia() != null && provincia.getCodtlfProvincia() != 0;
    }

    // --Getter & Setter
    public TabProvincia getProvincia() {
        return provincia;
    }

    public void setProvincia(TabProvincia provincia) {
        this.provincia = provincia;
    }

    public ArrayList<TabProvincia> getProvinciaList() {
        return provinciaList;
    }

    public void setProvinciaList(ArrayList<TabProvincia> provinciaList) {
        this.provinciaList = provinciaList;
    }
}