package com.boleteria.controller.tab;

import com.boleteria.controller.MensajeController;
import com.boleteria.controller.TabController;
import com.boleteria.enums.TipoMensaje;
import com.boleteria.exception.IncompleteFormException;
import com.boleteria.model.TabBanco;
import com.boleteria.model.TabCuentabanco;
import com.boleteria.model.TabTipocuenta;
import com.boleteria.util.Cronometro;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;

@Named("cuentaBancoController")
@ViewScoped
public class TabCuentaBancoController extends Cronometro implements Serializable {
    private final TabController tabController = new TabController();

    private TabCuentabanco cuentaBanco;
    private ArrayList<TabCuentabanco> cuentaBancoList;

    private ArrayList<TabBanco> bancoList;
    private ArrayList<TabTipocuenta> tipocuentaList;

    private Integer idBanco;
    private Integer idTipocuenta;

    private String nombreBanco;
    private String nombreTipocuenta;

    public TabCuentaBancoController() {
        cuentaBancoList = tabController.listarTabCuentaBancos();
        bancoList = tabController.listarTabBancos();
        tipocuentaList = tabController.listarTabTipocuentas();
    }

    public void agregarCuentaBanco() {
        cuentaBanco = new TabCuentabanco();
        idBanco = null;
        idTipocuenta = null;
        nombreBanco = null;
        nombreTipocuenta = null;
        if (tipocuentaList.isEmpty() || bancoList.isEmpty()) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_WARN,"Lista vacia", null));
        }
    }

    public void recargarTabla() {
        if (segundo == 0) {
            ejecutar(); // Incia el cronometro
            cuentaBancoList = tabController.listarTabCuentaBancos();
            MensajeController.contruirMensaje(TipoMensaje.RECARGA);
        } else if (segundo < tiempoEspera){
            MensajeController.contruirMensaje(TipoMensaje.ESPERA, tiempoEspera, segundo);
        }
    }

    public void verCuentaBanco(TabCuentabanco tabCuentabanco) {
        cuentaBanco = tabCuentabanco;
        idBanco = tabCuentabanco.getIdBanco().getIdBanco();
        idTipocuenta = tabCuentabanco.getIdTipocuenta().getIdTipocuenta();
        nombreBanco = tabCuentabanco.getIdBanco().getNombreBanco();
        nombreTipocuenta = tabCuentabanco.getIdTipocuenta().getNombreTipocuenta();
    }

    public void guardarActualizarCuentaBanco() throws IncompleteFormException {
        cuentaBanco.setIdBanco(tabController.obtenerBancoXId(idBanco));
        cuentaBanco.setIdTipocuenta(tabController.obtenerTipocuentaXId(idTipocuenta));
        if (validarFormulario()) {
            if (cuentaBanco.getIdCuentabanco() != null) {
                tabController.cudCuentaBanco(cuentaBanco, 'u');
                MensajeController.contruirMensaje(TipoMensaje.ACTUALIZAR);
            } else {
                cuentaBanco.setEstadoCuentabanco(true);
                tabController.cudCuentaBanco(cuentaBanco, 's');
                MensajeController.contruirMensaje(TipoMensaje.GUARDAR);
            }
            cuentaBanco = new TabCuentabanco();
            cuentaBancoList = tabController.listarTabCuentaBancos();
        } else {
            throw new IncompleteFormException("Formulario Incompleto");
        }
    }

    public void eliminarCuentaBanco() {
        tabController.cudCuentaBanco(cuentaBanco, 'd');
        MensajeController.contruirMensaje(TipoMensaje.ELIMINAR);
        cuentaBancoList = tabController.listarTabCuentaBancos();
    }

    private boolean validarFormulario() {
        return cuentaBanco.getIdBanco() != null && cuentaBanco.getIdBanco().getIdBanco() != 0 &&
                cuentaBanco.getIdCuentabanco() != null && cuentaBanco.getIdTipocuenta().getIdTipocuenta() != 0 &&
                cuentaBanco.getNumcuentaBanco() != null && cuentaBanco.getNumcuentaBanco().trim().length() != 0;
    }

    // --Getter & Setter
    public TabCuentabanco getCuentaBanco() {
        return cuentaBanco;
    }

    public void setCuentaBanco(TabCuentabanco cuentaBanco) {
        this.cuentaBanco = cuentaBanco;
    }

    public ArrayList<TabCuentabanco> getCuentaBancoList() {
        return cuentaBancoList;
    }

    public void setCuentaBancoList(ArrayList<TabCuentabanco> cuentaBancoList) {
        this.cuentaBancoList = cuentaBancoList;
    }

    public ArrayList<TabBanco> getBancoList() {
        return bancoList;
    }

    public void setBancoList(ArrayList<TabBanco> bancoList) {
        this.bancoList = bancoList;
    }

    public ArrayList<TabTipocuenta> getTipocuentaList() {
        return tipocuentaList;
    }

    public void setTipocuentaList(ArrayList<TabTipocuenta> tipocuentaList) {
        this.tipocuentaList = tipocuentaList;
    }

    public Integer getIdBanco() {
        return idBanco;
    }

    public void setIdBanco(Integer idBanco) {
        this.idBanco = idBanco;
    }

    public Integer getIdTipocuenta() {
        return idTipocuenta;
    }

    public void setIdTipocuenta(Integer idTipocuenta) {
        this.idTipocuenta = idTipocuenta;
    }

    public String getNombreBanco() {
        return nombreBanco;
    }

    public void setNombreBanco(String nombreBanco) {
        this.nombreBanco = nombreBanco;
    }

    public String getNombreTipocuenta() {
        return nombreTipocuenta;
    }

    public void setNombreTipocuenta(String nombreTipocuenta) {
        this.nombreTipocuenta = nombreTipocuenta;
    }
}