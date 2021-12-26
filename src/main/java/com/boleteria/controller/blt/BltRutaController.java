package com.boleteria.controller.blt;

import com.boleteria.controller.BltController;
import com.boleteria.controller.MensajeController;
import com.boleteria.controller.TabController;
import com.boleteria.enums.TipoMensaje;
import com.boleteria.exception.IncompleteFormException;
import com.boleteria.model.BltRuta;
import com.boleteria.model.ImbaOperadora;
import com.boleteria.model.TabCiudad;
import com.boleteria.util.Cronometro;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;

@Named("rutaController")
@ViewScoped
public class BltRutaController extends Cronometro implements Serializable {
    private final BltController bltController = new BltController();
    private final TabController tabController = new TabController();

    private BltRuta ruta;
    private ArrayList<BltRuta> rutaList;

    private ArrayList<TabCiudad> ciudadList;
    private ArrayList<ImbaOperadora> operadoraList;

    private TabCiudad ciudadIni;
    private TabCiudad ciudadFin;
    private ImbaOperadora operadora;

    public BltRutaController() {
        rutaList = bltController.listarRutas();
        ciudadList = tabController.listarTabCiudades();
        operadoraList = new ArrayList<>();
    }

    public void agregarRuta() {
        ruta = new BltRuta();
        ciudadIni = new TabCiudad();
        ciudadFin = new TabCiudad();
        operadora = new ImbaOperadora();
        if (ciudadList.isEmpty() || operadoraList.isEmpty()) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_WARN,"Lista vacia", null));
        }
    }

    public void recargarTabla() {
        if (segundo == 0) {
            ejecutar(); // Incia el cronometro
            rutaList = bltController.listarRutas();
            MensajeController.contruirMensaje(TipoMensaje.RECARGA);
        } else if (segundo < tiempoEspera){
            MensajeController.contruirMensaje(TipoMensaje.ESPERA, tiempoEspera, segundo);
        }
    }

    public void verRuta(BltRuta tabRuta) {
        ruta = tabRuta;
        ciudadIni = tabRuta.getCodCiudadIni();
        ciudadFin = tabRuta.getCodCiudadFin();
        operadora = tabRuta.getIdOperadora();
    }

    public void guardarActualizarRuta() throws IncompleteFormException {
        ruta.setCodCiudadIni(tabController.obtenerCiudadXId(ciudadIni.getCodCiudad()));
        ruta.setCodCiudadFin(tabController.obtenerCiudadXId(ciudadFin.getCodCiudad()));
        if (validarFormulario()) {
            if (ruta.getIdRuta() != null) {
                bltController.cudRuta(ruta, 'u');
                MensajeController.contruirMensaje(TipoMensaje.ACTUALIZAR);
            } else {
                bltController.cudRuta(ruta, 's');
                MensajeController.contruirMensaje(TipoMensaje.GUARDAR);
            }
            ruta = new BltRuta();
            rutaList = bltController.listarRutas();
        } else {
            throw new IncompleteFormException("Formulario Incompleto");
        }
    }

    public void eliminarRuta() {
        bltController.cudRuta(ruta, 'd');
        MensajeController.contruirMensaje(TipoMensaje.ELIMINAR);
        rutaList = bltController.listarRutas();
    }

    private boolean validarFormulario() {
        return ruta.getCodCiudadIni() != null && ruta.getCodCiudadIni().getCodCiudad().trim().length() != 0 &&
                ruta.getCodCiudadFin() != null && ruta.getCodCiudadFin().getCodCiudad().trim().length() != 0 &&
                ruta.getIdOperadora() != null && ruta.getIdOperadora().getIdOperadora() != 0 &&
                ruta.getNombreRuta() != null && ruta.getNombreRuta().trim().length() != 0 &&
                ruta.getPrecioRuta() != null && ruta.getPrecioRuta() != 0;
    }

    // --Getter & Setter
    public BltRuta getRuta() {
        return ruta;
    }

    public void setRuta(BltRuta ruta) {
        this.ruta = ruta;
    }

    public ArrayList<BltRuta> getRutaList() {
        return rutaList;
    }

    public void setRutaList(ArrayList<BltRuta> rutaList) {
        this.rutaList = rutaList;
    }

    public ArrayList<TabCiudad> getCiudadList() {
        return ciudadList;
    }

    public void setCiudadList(ArrayList<TabCiudad> ciudadList) {
        this.ciudadList = ciudadList;
    }

    public ArrayList<ImbaOperadora> getOperadoraList() {
        return operadoraList;
    }

    public void setOperadoraList(ArrayList<ImbaOperadora> operadoraList) {
        this.operadoraList = operadoraList;
    }

    public TabCiudad getCiudadIni() {
        return ciudadIni;
    }

    public void setCiudadIni(TabCiudad ciudadIni) {
        this.ciudadIni = ciudadIni;
    }

    public TabCiudad getCiudadFin() {
        return ciudadFin;
    }

    public void setCiudadFin(TabCiudad ciudadFin) {
        this.ciudadFin = ciudadFin;
    }

    public ImbaOperadora getOperadora() {
        return operadora;
    }

    public void setOperadora(ImbaOperadora operadora) {
        this.operadora = operadora;
    }
}