package com.boleteria.controller;

import com.boleteria.model.*;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;
import java.lang.reflect.Type;
import java.util.ArrayList;

public class BltController {
    private static final Logger LOGGER = LogManager.getLogger(BltController.class);

    private Client client;
    private WebTarget webTarget;
    private Gson gson;
    private Type type;
    private Response response;
    private String model;

    //private final String URL = "http://127.0.0.1:6897/BLT_SERVICE/blt";
    private final String URL = "http://192.168.0.21:9895/blt"; //--local

    /**=========== BOLETO ===========**/
    public BltBoleto obtenerBoletoXCodalfa(String cod) {
        try {
            model = "Boleto/";
            client = ClientBuilder.newClient();
            webTarget = client.target(URL + model + "getByCodalfa/" + cod);
            return webTarget.request().get(BltBoleto.class);
        } catch (Exception e) {
            LOGGER.error("Obtener x codalfa " + model + ": " + e.getMessage());
        } finally {
            client.close();
        }
        return null;
    }

    public void actualizarBoleto(BltBoleto bltBoleto) {
        try {
            model = "Boleto/";
            client = ClientBuilder.newClient();
            webTarget = client.target(URL + model + "update");
            response = webTarget.request().put(Entity.json(bltBoleto));
        } catch (Exception e) {
            LOGGER.error("update " + model + ": " + e.getMessage());
        } finally {
            response.close();
            client.close();
        }
    }

    /**=========== HORARIO ===========**/
    public BltHorario guardarHorario(BltHorario bltHorario) {
        try {
            model = "Horario/";
            client = ClientBuilder.newClient();
            webTarget = client.target(URL + model + "save");
            response = webTarget.request().post(Entity.json(bltHorario));
            return response.readEntity(BltHorario.class);
        } catch (Exception e) {
            LOGGER.error("save " + model + ": " + e.getMessage());
        } finally {
            response.close();
            client.close();
        }
        return null;
    }

    /**=========== BLT_RUTA ===========**/
    public ArrayList<BltRuta> listarRutas() {
        try {
            model = "Ruta/";
            client = ClientBuilder.newClient();
            webTarget = client.target(URL + model + "getList");
            gson = new Gson();
            type = new TypeToken<ArrayList<BltRuta>>(){}.getType();

            return gson.fromJson(webTarget.request().get(String.class), type);
        } catch (Exception e) {
            LOGGER.error("Listar " + model + ": " + e.getMessage());
        } finally {
            client.close();
        }
        return null;
    }

    public ArrayList<BltRutaparada> obtenerRutaparadaXIdRuta(Integer id) {
        try {
            model = "Rutaparada/";
            client = ClientBuilder.newClient();
            webTarget = client.target(URL + model + "getByIdRuta/" + id);
            gson = new Gson();
            type = new TypeToken<ArrayList<BltRutaparada>>(){}.getType();

            return gson.fromJson(webTarget.request().get(String.class), type);
        } catch (Exception e) {
            LOGGER.error("Obtener x idRuta " + model + ": " + e.getMessage());
        } finally {
            client.close();
        }
        return null;
    }

    public void guardarHorariodetalle(BltHorariodetalle bltHorariodetalle) {
        try {
            model = "Horariodetalle/";
            client = ClientBuilder.newClient();
            webTarget = client.target(URL + model + "save");
            response = webTarget.request().post(Entity.json(bltHorariodetalle));
        } catch (Exception e) {
            LOGGER.error("save " + model + ": " + e.getMessage());
        } finally {
            response.close();
            client.close();
        }
    }
}