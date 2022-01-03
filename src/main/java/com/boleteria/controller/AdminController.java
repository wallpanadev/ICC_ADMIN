package com.boleteria.controller;

import com.google.gson.Gson;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;
import java.lang.reflect.Type;

public class AdminController {
    private static final Logger LOGGER = LogManager.getLogger(AdminController.class);

    private Client client;
    private WebTarget webTarget;
    private Gson gson;
    private Type type;
    private Response response;
    private String model;

    //private final String URL = "http://127.0.0.1:9899/ADMIN_SERVICE/admin";
    private final String URL = "http://192.168.0.21:9899/admin"; //--local

    public Integer verificarUsuario(String nick, String pass) {
        try {
            model = "Usuario/";
            client = ClientBuilder.newClient();
            webTarget = client.target(URL + model + "validateUser/" + nick + "/" + pass);
            return webTarget.request().get(Integer.class);
        } catch (Exception e) {
            LOGGER.error("Verificar " + model + ": " + e.getMessage());
        } finally {
            client.close();
        }
        return null;
    }

    /*
    public boolean verificarUsuario(Usuario userBD, Usuario u) {
        em = emf.createEntityManager();
        Query q = em.createNativeQuery("SELECT AES_DECRYPT(?1, ?2)");
        q.setParameter(1, userBD.getPasswordUser());
        q.setParameter(2, new GlobalParameters().getKeyDescoder());
        try {
            byte[] passB = (byte[]) q.getSingleResult();
            return Arrays.equals(passB, u.getPasswordUser());
        } catch (NullPointerException e) {
            return false;
        } finally {
            em.close();
        }
    }
    * */
}
