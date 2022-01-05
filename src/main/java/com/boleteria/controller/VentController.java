package com.boleteria.controller;

import com.boleteria.model.VentPasajero;
import com.boleteria.model.VentReserva;
import com.boleteria.util.ListaPasajero;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;

public class VentController {
    private static final Logger LOGGER = LogManager.getLogger(VentController.class);

    private Client client;
    private WebTarget webTarget;
    private Response response;
    private String model;

    //private final String URL = "http://127.0.0.1:6897/VENT_SERVICE/vent";
    private final String URL = "http://192.168.0.21:9896/vent"; //--local

    /**=========== PASAJERO ===========**/
    public ArrayList<VentPasajero> listarPasajerosXReserva(Integer idReserva) {
        try {
            model = "Pasajero/";
            client = ClientBuilder.newClient();
            webTarget = client.target(URL + model + "getListByReserva/" + idReserva);
            return webTarget.request().accept(MediaType.APPLICATION_JSON).get(new GenericType<ArrayList<VentPasajero>>(){});
        } catch (Exception e) {
            LOGGER.error("Listar x reserva " + model + ": " + e.getMessage());
        } finally {
            client.close();
        }
        return null;
    }

    /**=========== RESERVA ===========**/
    public VentReserva obtenerReservaXId(Integer id) {
        try {
            model = "Reserva/";
            client = ClientBuilder.newClient();
            webTarget = client.target(URL + model + "getById/" + id);
            return webTarget.request().get(VentReserva.class);
        } catch (Exception e) {
            LOGGER.error("Obtener x id " + model + ": " + e.getMessage());
        } finally {
            client.close();
        }
        return null;
    }

    public ArrayList<ListaPasajero> listarReservasXFiltro(Integer idRuta, Integer idAutobus, String fecha, String hora) {
        try {
            model = "Reserva/";
            client = ClientBuilder.newClient();
            webTarget = client.target(URL + model + "getListFilter/" + idRuta + "/" + idAutobus + "/" + fecha + "/" + hora);
            return webTarget.request().accept(MediaType.APPLICATION_JSON).get(new GenericType<ArrayList<ListaPasajero>>(){});
        } catch (Exception e) {
            LOGGER.error("Listar " + model + ": " + e.getMessage());
        } finally {
            client.close();
        }
        return null;
    }

    public Integer obtenerTotalReserva() {
        try {
            model = "Reserva/";
            client = ClientBuilder.newClient();
            webTarget = client.target(URL + model + "getCount");

            return webTarget.request().get(Integer.class);
        } catch (Exception e) {
            LOGGER.error("Obtener count " + model + ": " + e.getMessage());
        } finally {
            client.close();
        }
        return null;
    }

    public void actualizarReserva(VentReserva ventReserva) {
        try {
            model = "Reserva/";
            client = ClientBuilder.newClient();
            webTarget = client.target(URL + model + "update");
            response = webTarget.request().put(Entity.json(ventReserva));
        } catch (Exception e) {
            LOGGER.error("actualizar " + model + ": " + e.getMessage());
        } finally {
            response.close();
            client.close();
        }
    }

    public String descargarListaPasajeros(Integer idRuta, Integer idAutobus, String fecha, String hora) {
        try {
            model = "Reserva/";
            client = ClientBuilder.newClient();
            webTarget = client.target(URL + model + "downloadListaPasajeros/" + idRuta + "/" + idAutobus + "/" + fecha + "/" + hora);
            return webTarget.request().get(String.class);
        } catch (Exception e) {
            LOGGER.error("Descargar " + model + ": " + e.getMessage());
        } finally {
            client.close();
        }
        return null;
    }
}