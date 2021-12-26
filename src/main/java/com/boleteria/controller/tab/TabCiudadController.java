package com.boleteria.controller.tab;

import com.boleteria.controller.MensajeController;
import com.boleteria.controller.TabController;
import com.boleteria.enums.TipoMensaje;
import com.boleteria.exception.IncompleteFormException;
import com.boleteria.model.TabCiudad;
import com.boleteria.model.TabProvincia;
import com.boleteria.util.Cronometro;

import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;

@Named("ciudadController")
@ViewScoped
public class TabCiudadController extends Cronometro implements Serializable {
    private final TabController tabController = new TabController();

    private TabCiudad ciudad;
    private ArrayList<TabCiudad> ciudadList;

    private ArrayList<TabProvincia> provinciaList;

    private String codProvincia;
    private String provinciaView;

    public TabCiudadController() {
        ciudadList = tabController.listarTabCiudades();
        provinciaList = tabController.listarTabProvincias();
    }

    public void agregarCiudad() {
        ciudad = new TabCiudad();
        codProvincia = null;
        provinciaView = null;
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

    public void verCiudad(TabCiudad tabCiudad) {
        ciudad = tabCiudad;
        codProvincia = tabCiudad.getCodProvincia().getCodProvincia();
        provinciaView = tabCiudad.getCodProvincia().getNombreProvincia();
    }

    public void guardarActualizarCiudad() throws IncompleteFormException {
        ciudad.setCodProvincia(tabController.obtenerProvinciaXId(codProvincia));
        if (validarFormulario()) {
            if (tabController.encontraCiudadXId(ciudad.getCodCiudad())) {
                tabController.cudTabCiudad(ciudad, 'u');
                MensajeController.contruirMensaje(TipoMensaje.ACTUALIZAR);
            } else {
                tabController.cudTabCiudad(ciudad, 's');
                MensajeController.contruirMensaje(TipoMensaje.GUARDAR);
            }
            ciudad = new TabCiudad();
            ciudadList = tabController.listarTabCiudades();
        } else {
            throw new IncompleteFormException("Formulario Incompleto");
        }
    }

    private boolean validarFormulario() {
        return ciudad.getCodCiudad() != null && ciudad.getCodCiudad().trim().length() != 0 &&
                ciudad.getNombreCiudad() != null && ciudad.getNombreCiudad().trim().length() != 0 &&
                ciudad.getCodProvincia() != null && ciudad.getCodProvincia().getCodProvincia().trim().length() != 0;
    }

    // --Getter & Setter

    public TabCiudad getCiudad() {
        return ciudad;
    }

    public void setCiudad(TabCiudad ciudad) {
        this.ciudad = ciudad;
    }

    public ArrayList<TabCiudad> getCiudadList() {
        return ciudadList;
    }

    public void setCiudadList(ArrayList<TabCiudad> ciudadList) {
        this.ciudadList = ciudadList;
    }

    public ArrayList<TabProvincia> getProvinciaList() {
        return provinciaList;
    }

    public void setProvinciaList(ArrayList<TabProvincia> provinciaList) {
        this.provinciaList = provinciaList;
    }

    public String getCodProvincia() {
        return codProvincia;
    }

    public void setCodProvincia(String codProvincia) {
        this.codProvincia = codProvincia;
    }

    public String getProvinciaView() {
        return provinciaView;
    }

    public void setProvinciaView(String provinciaView) {
        this.provinciaView = provinciaView;
    }
}