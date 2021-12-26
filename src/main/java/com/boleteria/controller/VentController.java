package com.boleteria.controller;

import com.boleteria.model.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
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

    /**=========== CLIENTE ===========**/
    public VentCliente obtenerClienteXIdPersona(Integer id) {
        try {
            model = "Cliente/";
            client = ClientBuilder.newClient();
            webTarget = client.target(URL + model + "getByPersona/" + id);

            return webTarget.request().get(VentCliente.class);
        } catch (Exception e) {
            LOGGER.error("Obtener x id persona " + model + ": " + e.getMessage());
        } finally {
            client.close();
        }
        return null;
    }

    public VentCliente cudCliente(VentCliente ventCliente, char cud) {
        VentCliente cliente = new VentCliente();
        try {
            model = "Cliente/";
            client = ClientBuilder.newClient();
            switch (cud) {
                case 's':
                    webTarget = client.target(URL + model + "save");
                    response = webTarget.request().post(Entity.json(ventCliente));
                    cliente = response.readEntity(VentCliente.class);
                    break;
                case 'u':
                    webTarget = client.target(URL + model + "update");
                    response = webTarget.request().put(Entity.json(ventCliente));
                    break;
            }
        } catch (Exception e) {
            LOGGER.error("cud " + model + ": " + e.getMessage());
        } finally {
            response.close();
            client.close();
        }
        return cliente;
    }

    /**=========== DETALLE_RESERVA ===========**/
    public void guardarDetalleReservaList(ArrayList<VentDetallereserva> ventDetallereservaList) {
        try {
            model = "Detallereserva/";
            client = ClientBuilder.newClient();
            webTarget = client.target(URL + model +"saveAll");
            response = webTarget.request().post(Entity.json(ventDetallereservaList));
        } catch (Exception e) {
            LOGGER.error("save All " + model + ": " + e.getMessage());
        } finally {
            response.close();
            client.close();
        }
    }

    /**=========== FORMA_PAGO ===========**/
    public VentFormapago obtenerFormapagoXId(Integer id) {
        try {
            model = "Formapago/";
            client = ClientBuilder.newClient();
            webTarget = client.target(URL + model + "getById/" + id);

            return webTarget.request().get(VentFormapago.class);
        } catch (Exception e) {
            LOGGER.error("Obtener x id " + model + ": " + e.getMessage());
        } finally {
            client.close();
        }
        return null;
    }

    /**=========== PASAJERO ===========**/
    public void guardarPasajeroList(ArrayList<VentPasajero> ventPasajeroList) {
        try {
            model = "Pasajero/";
            client = ClientBuilder.newClient();
            webTarget = client.target(URL + model +"saveAll");
            response = webTarget.request().post(Entity.json(ventPasajeroList));
        } catch (Exception e) {
            LOGGER.error("save All " + model + ": " + e.getMessage());
        } finally {
            response.close();
            client.close();
        }
    }

    /**=========== RESERVA ===========**/
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

    public VentReserva cudReserva(VentReserva ventReserva, char cud) {
        VentReserva reserva = new VentReserva();
        try {
            model = "Reserva/";
            client = ClientBuilder.newClient();
            switch (cud) {
                case 's':
                    webTarget = client.target(URL + model + "save");
                    response = webTarget.request().post(Entity.json(ventReserva));
                    reserva = response.readEntity(VentReserva.class);
                    break;
                case 'u':
                    webTarget = client.target(URL + model + "update");
                    response = webTarget.request().put(Entity.json(ventReserva));
                    break;
            }
        } catch (Exception e) {
            LOGGER.error("cud " + model + ": " + e.getMessage());
        } finally {
            response.close();
            client.close();
        }
        return reserva;
    }
}