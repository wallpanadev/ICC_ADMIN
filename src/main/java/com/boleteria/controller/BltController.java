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

    public ArrayList<BltBoleto> listarBoletosXCedula(String cedula) {
        try {
            model = "Boleto/";
            client = ClientBuilder.newClient();
            webTarget = client.target(URL + model + "getListByCedula/" + cedula);
            gson = new Gson();
            type = new TypeToken<ArrayList<BltBoleto>>(){}.getType();

            return gson.fromJson(webTarget.request().get(String.class), type);
        } catch (Exception e) {
            LOGGER.error("Listar x cedula " + model + ": " + e.getMessage());
        } finally {
            client.close();
        }
        return null;
    }

    public void guardarBoleto(BltBoleto bltBoleto) {
        try {
            model = "Boleto/";
            client = ClientBuilder.newClient();
            webTarget = client.target(URL + model + "save");
            response = webTarget.request().post(Entity.json(bltBoleto));
        } catch (Exception e) {
            LOGGER.error("save " + model + ": " + e.getMessage());
        } finally {
            response.close();
            client.close();
        }
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

    public String descargarBoleto(String cod) {
        try {
            model = "Boleto/";
            client = ClientBuilder.newClient();
            webTarget = client.target(URL + model + "downloadBoleto/" + cod);
            return webTarget.request().get(String.class);
        } catch (Exception e) {
            LOGGER.error("Descargar " + model + ": " + e.getMessage());
        } finally {
            client.close();
        }
        return null;
    }

    /**=========== HORARIO ===========**/
    public ArrayList<BltHorario> listarHorariosXIdRuta(Integer id, String fechaHora) {
        try {
            model = "Horario/";
            client = ClientBuilder.newClient();
            webTarget = client.target(URL + model + "getListByRutaYHorario/" + id + "/" + fechaHora);
            gson = new Gson();
            type = new TypeToken<ArrayList<BltHorario>>(){}.getType();

            return gson.fromJson(webTarget.request().get(String.class), type);
        } catch (Exception e) {
            LOGGER.error("Listar x idRuta y horario " + model +": " + e.getMessage());
        } finally {
            client.close();
        }
        return null;
    }

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

    public void actualizarHorario(BltHorario bltHorario) {
        try {
            model = "Horario/";
            client = ClientBuilder.newClient();
            webTarget = client.target(URL + model + "update");
            response = webTarget.request().put(Entity.json(bltHorario));
        } catch (Exception e) {
            LOGGER.error("update " + model + ": " + e.getMessage());
        } finally {
            response.close();
            client.close();
        }
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

    public ArrayList<TabCiudad> listarCiudadIni() {
        try {
            model = "Ruta/";
            client = ClientBuilder.newClient();
            webTarget = client.target(URL + model + "getCiudadIniList");
            gson = new Gson();
            type = new TypeToken<ArrayList<TabCiudad>>(){}.getType();

            return gson.fromJson(webTarget.request().get(String.class), type);
        } catch (Exception e) {
            LOGGER.error("Listar ciudad ini" + model + ": " + e.getMessage());
        } finally {
            client.close();
        }
        return null;
    }

    public ArrayList<TabCiudad> listarCiudadFin(String codCiudadIni) {
        try {
            model = "Ruta/";
            client = ClientBuilder.newClient();
            webTarget = client.target(URL + model + "getCiudadFinList/" + codCiudadIni);
            gson = new Gson();
            type = new TypeToken<ArrayList<TabCiudad>>(){}.getType();

            return gson.fromJson(webTarget.request().get(String.class), type);
        } catch (Exception e) {
            LOGGER.error("Listar ciudad fin " + model + ": " + e.getMessage());
        } finally {
            client.close();
        }
        return null;
    }

    public BltRuta listarRutasXCiudades(String codCiudadIni, String codCiudadFin) {
        try {
            model = "Ruta/";
            client = ClientBuilder.newClient();
            webTarget = client.target(URL + model + "getByCiudades/" + codCiudadIni + "/" + codCiudadFin);

            return webTarget.request().get(BltRuta.class);
        } catch (Exception e) {
            LOGGER.error("Obtener x ciudades " + model + ": " + e.getMessage());
        } finally {
            client.close();
        }
        return null;
    }

    public void cudRuta(BltRuta bltRuta, char cud) {
        try {
            model = "Ruta/";
            client = ClientBuilder.newClient();
            switch (cud) {
                case 's':
                    webTarget = client.target(URL + model + "save");
                    response = webTarget.request().post(Entity.json(bltRuta));
                    break;
                case 'u':
                    webTarget = client.target(URL + model + "update");
                    response = webTarget.request().put(Entity.json(bltRuta));
                    break;
                case 'd':
                    webTarget = client.target(URL + model + "delete/" + bltRuta.getIdRuta());
                    response = webTarget.request().put(Entity.json(bltRuta.getIdRuta()));
                    break;
            }
        } catch (Exception e) {
            LOGGER.error("cud " + model + ": " + e.getMessage());
        } finally {
            response.close();
            client.close();
        }
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