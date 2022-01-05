package com.boleteria.controller.vent;

import com.boleteria.controller.BltController;
import com.boleteria.controller.ImbaController;
import com.boleteria.controller.MensajeController;
import com.boleteria.controller.VentController;
import com.boleteria.enums.TipoMensaje;
import com.boleteria.model.*;
import com.boleteria.util.Cronometro;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

@Named("reservaController")
@ViewScoped
public class VentReservaController extends Cronometro implements Serializable {
    private static final Logger LOGGER = LogManager.getLogger(VentReservaController.class);

    private final VentController ventController = new VentController();
    private final BltController bltController = new BltController();
    private final ImbaController imbaController = new ImbaController();

    private VentReserva reserva;

    private ArrayList<VentPasajero> pasajeroList;
    private ArrayList<ListaPasajero> listaPasajeros;
    private final ArrayList<BltRuta> rutaList;
    private final ArrayList<ImbaAutobus> autobusList;

    private Integer idRuta;
    private Integer idAutobus;

    private String fechaFiltro;
    private String horaFiltro;

    private final DateTimeFormatter formatterDateHTML = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private final DateTimeFormatter formatterTimeHTML = DateTimeFormatter.ofPattern("HH:mm");

    public VentReservaController() {
        rutaList = bltController.listarRutas();
        autobusList = imbaController.listarAutobuses();

        fechaFiltro = LocalDate.now().format(formatterDateHTML);
        horaFiltro = LocalTime.MIDNIGHT.format(formatterTimeHTML);
    }

    public void descargarListaPasajeros() {
        if (listaPasajeros != null && !listaPasajeros.isEmpty()) {
            FacesContext fc = FacesContext.getCurrentInstance();
            ExternalContext ec = fc.getExternalContext();
            try {
                LocalDate date = LocalDate.parse(fechaFiltro, formatterDateHTML);
                LocalTime time = LocalTime.parse(horaFiltro, formatterTimeHTML);
                InputStream inputStream = new ByteArrayInputStream(Base64.getDecoder().decode(
                        ventController.descargarListaPasajeros(
                                idRuta,
                                idAutobus,
                                date.format(DateTimeFormatter.ofPattern("yyyyMMdd")),
                                time.format(DateTimeFormatter.ofPattern("HHmmss")))));
                ec.responseReset();
                ec.setResponseContentType("application/pdf");
                ec.setResponseHeader("Content-Disposition", "attachment; filename=\"ListaPasajeros-" + fechaFiltro + ".pdf\"");
                OutputStream outputStream = ec.getResponseOutputStream();
                int length;
                byte[] buffer = new byte[1024];
                while ((length = inputStream.read(buffer)) > 0) {
                    outputStream.write(buffer, 0, length);
                }
                outputStream.flush();
                inputStream.close();
            } catch (Exception e) {
                LOGGER.error("Error al descargar - linea " + e.getStackTrace()[0].getLineNumber() + ": " + e.getMessage());
            } finally {
                fc.responseComplete();
            }
        }
    }

    public void recargarTabla() {
        if (segundo == 0) {
            ejecutar(); // Incia el cronometro
            listaPasajeros = new ArrayList<>();
            listaPasajeros.addAll(filtrarListaPasajeros());
            MensajeController.contruirMensaje(TipoMensaje.RECARGA);
        } else if (segundo < tiempoEspera){
            MensajeController.contruirMensaje(TipoMensaje.ESPERA, tiempoEspera, segundo);
        }
    }

    private List<ListaPasajero> filtrarListaPasajeros() {
        if (idRuta != null && idRuta > 0 && idAutobus != null && idAutobus > 0 &&
                fechaFiltro != null && fechaFiltro.length() > 0 && horaFiltro != null && horaFiltro.length() > 0) {
            LocalDate date = LocalDate.parse(fechaFiltro, formatterDateHTML);
            LocalTime time = LocalTime.parse(horaFiltro, formatterTimeHTML);
            List<ListaPasajero> listaPasajeroList = ventController.listarReservasXFiltro(
                    idRuta,
                    idAutobus,
                    date.format(DateTimeFormatter.ofPattern("yyyyMMdd")),
                    time.format(DateTimeFormatter.ofPattern("HHmmss")));
            if (listaPasajeroList == null || listaPasajeroList.isEmpty()) {
                return new ArrayList<>();
            } else {
                return listaPasajeroList;
            }
        } else {
            return new ArrayList<>();
        }
    }

    public void buscarListaPasajeros() {
        listaPasajeros = new ArrayList<>();
        listaPasajeros.addAll(filtrarListaPasajeros());
        if (listaPasajeros.isEmpty()) {
            MensajeController.contruirMensaje(TipoMensaje.LISTA_VACIA);
        } else {
            MensajeController.contruirMensaje(TipoMensaje.LISTA_PASAJEROS);
        }
    }

    public void verBoleto(ListaPasajero listaPasajero) {
        reserva = ventController.obtenerReservaXId(listaPasajero.getIdReserva());
        pasajeroList = ventController.listarPasajerosXReserva(listaPasajero.getIdReserva());
    }

    public void actualizarPagoBoleto() {
        ventController.actualizarReserva(reserva);
        buscarListaPasajeros();
        BltBoleto  bltBoleto = bltController.obtenerBoletoXCodalfa(reserva.getCodalfaReserva());
        if (bltBoleto != null) {
            bltBoleto.setPagadoBoleto(true);
            bltController.actualizarBoleto(bltBoleto);
        }
        MensajeController.contruirMensaje(TipoMensaje.ACTUALIZAR);
    }

    public ArrayList<ListaPasajero> getListaPasajeros() {
        return listaPasajeros;
    }

    public VentReserva getReserva() {
        return reserva;
    }

    public ArrayList<BltRuta> getRutaList() {
        return rutaList;
    }

    public ArrayList<ImbaAutobus> getAutobusList() {
        return autobusList;
    }

    public Integer getIdRuta() {
        return idRuta;
    }

    public void setIdRuta(Integer idRuta) {
        this.idRuta = idRuta;
    }

    public Integer getIdAutobus() {
        return idAutobus;
    }

    public void setIdAutobus(Integer idAutobus) {
        this.idAutobus = idAutobus;
    }

    public String getFechaFiltro() {
        return fechaFiltro;
    }

    public void setFechaFiltro(String fechaFiltro) {
        this.fechaFiltro = fechaFiltro;
    }

    public String getHoraFiltro() {
        return horaFiltro;
    }

    public void setHoraFiltro(String horaFiltro) {
        this.horaFiltro = horaFiltro;
    }

    public ArrayList<VentPasajero> getPasajeroList() {
        return pasajeroList;
    }
}