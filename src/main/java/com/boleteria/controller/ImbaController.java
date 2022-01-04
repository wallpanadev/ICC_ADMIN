package com.boleteria.controller;

import com.boleteria.model.*;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import java.lang.reflect.Type;
import java.util.ArrayList;

public class ImbaController {
    private static final Logger LOGGER = LogManager.getLogger(ImbaController.class);

    private Client client;
    private WebTarget webTarget;
    private Gson gson;
    private Type type;
    private String model;

    //private final String URL = "http://127.0.0.1:6897/IMBA_SERVICE/imba";
    private final String URL = "http://192.168.0.21:9898/imba"; //--local

    /**=========== AUTOBUS ===========**/
    public ArrayList<ImbaAutobus> listarAutobuses() {
        try {
            model = "Autobus/";
            client = ClientBuilder.newClient();
            webTarget = client.target(URL + model + "getList");
            gson = new Gson();
            type = new TypeToken<ArrayList<ImbaAutobus>>(){}.getType();

            return gson.fromJson(webTarget.request().get(String.class), type);
        } catch (Exception e) {
            LOGGER.error("Listar " + model + ": " + e.getMessage());
        } finally {
            client.close();
        }
        return null;
    }
}