package com.boleteria.controller.tab;

import com.boleteria.controller.BltController;
import com.boleteria.controller.ImbaController;
import com.boleteria.controller.MensajeController;
import com.boleteria.controller.TabController;
import com.boleteria.enums.TipoMensaje;
import com.boleteria.exception.IncompleteFormException;
import com.boleteria.model.*;
import com.boleteria.util.Cronometro;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.servlet.http.Part;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Base64;

@Named("parametroController")
@ViewScoped
public class TabParametroController extends Cronometro implements Serializable {
    private static final Logger LOGGER = LogManager.getLogger(TabParametroController.class);

    private final TabController tabController = new TabController();
    private final ImbaController imbaController = new ImbaController();
    private final BltController bltController = new BltController();

    private TabParametro parametro;
    private ArrayList<TabParametro> tabParametroList;

    private Part file;

    public TabParametroController() {
        //crearHorario();
        tabParametroList = tabController.listarTabParametros();
    }

    private void crearHorario() {
        int idRutaAux = 1;
        ImbaAutobus imbaAutobus = imbaController.listarAutobuses().get(0);
        ArrayList<BltRutaparada> bltRutaparadas = bltController.obtenerRutaparadaXIdRuta(idRutaAux);

        StringBuilder otro = new StringBuilder();
        for (int y = 0; y < 48; y++) {
            otro.append(0).append(" ");
        }

        for (int i = 1; i <= 7; i++) {
            BltHorario bltHorario = new BltHorario();
            bltHorario.setIdRuta(idRutaAux);
            bltHorario.setIdAutobus(imbaAutobus);
            bltHorario.setAsientosHorario(otro.toString().trim());
            bltHorario.setAsientosdisponiblesHorario(imbaAutobus.getAsientosAutobus());
            bltHorario.setFechaHorario(LocalDate.now().plusDays(i));
            bltHorario.setHoraHorario(LocalTime.MIDNIGHT.plusHours(1));
            bltHorario.setEstadoHorario(true);
            bltHorario = bltController.guardarHorario(bltHorario);

            for (BltRutaparada rutaparada : bltRutaparadas) {
                BltHorariodetalle bltHorariodetalle = new BltHorariodetalle();
                bltHorariodetalle.setIdHorario(bltHorario);
                bltHorariodetalle.setIdRutaparada(rutaparada);
                bltHorariodetalle.setCantpasajeroHorariodetalle(0);
                bltHorariodetalle.setHorasalidaHorariodetalle(bltHorario.getHoraHorario());
                bltController.guardarHorariodetalle(bltHorariodetalle);
            }
        }
    }

    public void agregarParametro() {
        parametro = new TabParametro();
    }

    public void recargarTabla() {
        if (segundo == 0) {
            ejecutar(); // Incia el cronometro
            tabParametroList = tabController.listarTabParametros();
            MensajeController.contruirMensaje(TipoMensaje.RECARGA);
        } else if (segundo < tiempoEspera){
            MensajeController.contruirMensaje(TipoMensaje.ESPERA, tiempoEspera, segundo);
        }
    }

    public void verParametro(TabParametro tabParametro) {
        parametro = tabParametro;
    }

    public void guardarActualizarParametro() throws IncompleteFormException {
        if (validarFormulario()) {
            cargarArchivo();
            if (parametro.getIdParametro() == null) {
                parametro.setEstadoParametro(true);
                tabController.cudTabParametro(parametro, 's');
                MensajeController.contruirMensaje(TipoMensaje.GUARDAR);
            } else {
                tabController.cudTabParametro(parametro, 'u');
                MensajeController.contruirMensaje(TipoMensaje.ACTUALIZAR);
            }
            parametro = new TabParametro();
            tabParametroList = tabController.listarTabParametros();
        } else {
            throw new IncompleteFormException("Formulario Incompleto");
        }
    }

    public void eliminarParametro() {
        tabController.cudTabParametro(parametro, 'd');
        MensajeController.contruirMensaje(TipoMensaje.ELIMINAR);
        tabParametroList = tabController.listarTabParametros();
    }

    private boolean validarFormulario() {
        return parametro.getNombreParametro() != null && parametro.getNombreParametro().trim().length() != 0 &&
                parametro.getValorParametro() != null && parametro.getValorParametro().trim().length() != 0;
    }

    private void cargarArchivo() {
        if (file != null) {
            try {
                InputStream input = file.getInputStream();
                parametro.setFileParametro(Base64.getEncoder().encodeToString(input.readAllBytes()));
                LOGGER.info("Tamaño: " + file.getSize());
            } catch (IOException e) {
                LOGGER.error(e.getMessage());
            }
        }
    }

    // --Getter & Setter
    public TabParametro getParametro() {
        return parametro;
    }

    public void setParametro(TabParametro parametro) {
        this.parametro = parametro;
    }

    public ArrayList<TabParametro> getTabParametroList() {
        return tabParametroList;
    }

    public void setTabParametroList(ArrayList<TabParametro> tabParametroList) {
        this.tabParametroList = tabParametroList;
    }

    public Part getFile() {
        return file;
    }

    public void setFile(Part file) {
        this.file = file;
    }
}