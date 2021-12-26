package com.boleteria.controller.tab;

import com.boleteria.controller.MensajeController;
import com.boleteria.controller.TabController;
import com.boleteria.enums.TipoMensaje;
import com.boleteria.exception.IncompleteFormException;
import com.boleteria.model.TabBanco;
import com.boleteria.util.Cronometro;

import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;

@Named("bancoController")
@ViewScoped
public class TabBancoController extends Cronometro implements Serializable {
    private final TabController tabController = new TabController();

    private TabBanco banco;
    private ArrayList<TabBanco> tabBancoList;

    public TabBancoController() {
        tabBancoList = tabController.listarTabBancos();
    }

    public void agregarBanco() {
        banco = new TabBanco();
    }

    public void recargarTabla() {
        if (segundo == 0) {
            ejecutar(); // Incia el cronometro
            tabBancoList = tabController.listarTabBancos();
            MensajeController.contruirMensaje(TipoMensaje.RECARGA);
        } else if (segundo < tiempoEspera){
            MensajeController.contruirMensaje(TipoMensaje.ESPERA, tiempoEspera, segundo);
        }
    }

    public void verBanco(TabBanco tabBanco) {
        banco = tabBanco;
    }

    public void guardarActualizarBanco() throws IncompleteFormException {
        if (validarFormulario()) {
            if (banco.getIdBanco() == null) {
                banco.setEstadoBanco(true);
                tabController.cudTabBanco(banco, 's');
                MensajeController.contruirMensaje(TipoMensaje.GUARDAR);
            } else {
                tabController.cudTabBanco(banco, 'u');
                MensajeController.contruirMensaje(TipoMensaje.ACTUALIZAR);
            }
            banco = new TabBanco();
            tabBancoList = tabController.listarTabBancos();
        } else {
            throw new IncompleteFormException("Formulario Incompleto");
        }
    }

    public void eliminarBanco() {
        tabController.cudTabBanco(banco, 'd');
        MensajeController.contruirMensaje(TipoMensaje.ELIMINAR);
        tabBancoList = tabController.listarTabBancos();
    }

    private boolean validarFormulario() {
        return banco.getNombreBanco() != null && banco.getNombreBanco().trim().length() != 0;
    }

    // --Getter & Setter
    public TabBanco getBanco() {
        return banco;
    }

    public void setBanco(TabBanco banco) {
        this.banco = banco;
    }

    public ArrayList<TabBanco> getTabBancoList() {
        return tabBancoList;
    }

    public void setTabBancoList(ArrayList<TabBanco> tabBancoList) {
        this.tabBancoList = tabBancoList;
    }
}