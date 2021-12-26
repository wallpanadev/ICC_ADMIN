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

public class TabController {
    private static final Logger LOGGER = LogManager.getLogger(TabController.class);

    private Client client;
    private WebTarget webTarget;
    private Gson gson;
    private Type type;
    private Response response;
    private String model;

    //private final String URL = "http://127.0.0.1:6897/TAB_SERVICE/";
    private final String URL = "http://192.168.0.21:9897/"; //--local

    /**=========== BANCO ===========**/
    public ArrayList<TabBanco> listarTabBancos() {
        try {
            client = ClientBuilder.newClient();
            webTarget = client.target(URL + "tabBanco/getList");
            gson = new Gson();
            type = new TypeToken<ArrayList<TabBanco>>(){}.getType();

            return gson.fromJson(webTarget.request().get(String.class), type);
        } catch (Exception e) {
            LOGGER.error("Listar banco: " + e.getMessage());
        } finally {
            client.close();
        }
        return null;
    }

    public TabBanco obtenerBancoXId(Integer id) {
        try {
            client = ClientBuilder.newClient();
            webTarget = client.target(URL + "tabBanco/getById/" + id);

            return webTarget.request().get(TabBanco.class);
        } catch (Exception e) {
            LOGGER.error("Obtener banco id: " + e.getMessage());
        } finally {
            client.close();
        }
        return null;
    }

    public void cudTabBanco(TabBanco tabBanco, char cud) {
        try {
            client = ClientBuilder.newClient();
            switch (cud) {
                case 's':
                    webTarget = client.target(URL + "tabBanco/save");
                    response = webTarget.request().post(Entity.json(tabBanco));
                    break;
                case 'u':
                    webTarget = client.target(URL + "tabBanco/update");
                    response = webTarget.request().put(Entity.json(tabBanco));
                    break;
                case 'd':
                    webTarget = client.target(URL + "tabBanco/delete/" + tabBanco.getIdBanco());
                    response = webTarget.request().put(Entity.json(tabBanco.getIdBanco()));
                    break;
            }
        } catch (Exception e) {
            LOGGER.error("cud Banco: " + e.getMessage());
        } finally {
            response.close();
            client.close();
        }
    }

    /**=========== CIUDAD ===========**/
    public ArrayList<TabCiudad> listarTabCiudades() {
        try {
            client = ClientBuilder.newClient();
            webTarget = client.target(URL + "tabCiudad/getList");
            gson = new Gson();
            type = new TypeToken<ArrayList<TabCiudad>>(){}.getType();

            return gson.fromJson(webTarget.request().get(String.class), type);
        } catch (Exception e) {
            LOGGER.error("Listar ciudad: " + e.getMessage());
        } finally {
            client.close();
        }
        return null;
    }

    public TabCiudad obtenerCiudadXId(String cod) {
        try {
            client = ClientBuilder.newClient();
            webTarget = client.target(URL + "tabCiudad/getById/" + cod);

            return webTarget.request().get(TabCiudad.class);
        } catch (Exception e) {
            LOGGER.error("Obtener ciudad por id: " + e.getMessage());
        } finally {
            client.close();
        }
        return null;
    }

    public void cudTabCiudad(TabCiudad tabCiudad, char cud) {
        try {
            client = ClientBuilder.newClient();
            switch (cud) {
                case 's':
                    webTarget = client.target(URL + "tabCiudad/save");
                    response = webTarget.request().post(Entity.json(tabCiudad));
                    break;
                case 'u':
                    webTarget = client.target(URL + "tabCiudad/update");
                    response = webTarget.request().put(Entity.json(tabCiudad));
                    break;
            }
        } catch (Exception e) {
            LOGGER.error("cud Ciudad: " + e.getMessage());
        } finally {
            response.close();
            client.close();
        }
    }

    public boolean encontraCiudadXId(String cod) {
        try {
            client = ClientBuilder.newClient();
            webTarget = client.target(URL + "tabCiudad/existsById/" + cod);

            return webTarget.request().get(Boolean.class);
        } catch (Exception e) {
            LOGGER.error("Encontrar ciudad por id: " + e.getMessage());
        } finally {
            client.close();
        }
        return false;
    }

    /**=========== CUENTABANCO ===========**/
    public ArrayList<TabCuentabanco> listarTabCuentaBancos() {
        try {
            client = ClientBuilder.newClient();
            webTarget = client.target(URL + "tabCuentabanco/getList");
            gson = new Gson();
            type = new TypeToken<ArrayList<TabCuentabanco>>(){}.getType();

            return gson.fromJson(webTarget.request().get(String.class), type);
        } catch (Exception e) {
            LOGGER.error("Listar cuenta banco: " + e.getMessage());
        } finally {
            client.close();
        }
        return null;
    }

    public void cudCuentaBanco(TabCuentabanco tabCuentabanco, char cud) {
        try {
            client = ClientBuilder.newClient();
            switch (cud) {
                case 's':
                    webTarget = client.target(URL + "tabCuentabanco/save");
                    response = webTarget.request().post(Entity.json(tabCuentabanco));
                    break;
                case 'u':
                    webTarget = client.target(URL + "tabCuentabanco/update");
                    response = webTarget.request().put(Entity.json(tabCuentabanco));
                    break;
                case 'd':
                    webTarget = client.target(URL + "tabCuentabanco/delete/" + tabCuentabanco.getIdCuentabanco());
                    response = webTarget.request().put(Entity.json(tabCuentabanco.getIdCuentabanco()));
                    break;
            }
        } catch (Exception e) {
            LOGGER.error("cud cuenta banco: " + e.getMessage());
        } finally {
            response.close();
            client.close();
        }
    }

    /**=========== DOMCORREO ===========**/
    public ArrayList<TabDomcorreo> listarTabDomcorreos() {
        try {
            client = ClientBuilder.newClient();
            webTarget = client.target(URL + "tabDomcorreo/getList");
            gson = new Gson();
            type = new TypeToken<ArrayList<TabDomcorreo>>(){}.getType();

            return gson.fromJson(webTarget.request().get(String.class), type);
        } catch (Exception e) {
            LOGGER.error("Listar Domcorreo: " + e.getMessage());
        } finally {
            client.close();
        }
        return null;
    }

    public void cudTabDomcorreo(TabDomcorreo tabDomcorreo, char cud) {
        try {
            client = ClientBuilder.newClient();
            switch (cud) {
                case 's':
                    webTarget = client.target(URL + "tabDomcorreo/save");
                    response = webTarget.request().post(Entity.json(tabDomcorreo));
                    break;
                case 'u':
                    webTarget = client.target(URL + "tabDomcorreo/update");
                    response = webTarget.request().put(Entity.json(tabDomcorreo));
                    break;
                case 'd':
                    webTarget = client.target(URL + "tabDomcorreo/delete/" + tabDomcorreo.getIdDomcorreo());
                    response = webTarget.request().put(Entity.json(tabDomcorreo.getIdDomcorreo()));
                    break;
            }
        } catch (Exception e) {
            LOGGER.error("cud Domcorreo: " + e.getMessage());
        } finally {
            response.close();
            client.close();
        }
    }

    /**=========== PARAMETRO ===========**/
    public ArrayList<TabParametro> listarTabParametros() {
        try {
            client = ClientBuilder.newClient();
            webTarget = client.target(URL + "tabParametro/getList");
            gson = new Gson();
            type = new TypeToken<ArrayList<TabParametro>>(){}.getType();

            return gson.fromJson(webTarget.request().get(String.class), type);
        } catch (Exception e) {
            LOGGER.error("Listar parametros: " + e.getMessage());
        } finally {
            client.close();
        }
        return null;
    }

    public TabParametro obtenerParametroXNombre(String nombre) {
        try {
            client = ClientBuilder.newClient();
            webTarget = client.target(URL + "tabParametro/getByNombre/" + nombre);

            return webTarget.request().get(TabParametro.class);
        } catch (Exception e) {
            LOGGER.error("Obtener x nombre tabParametro: " + e.getMessage());
        } finally {
            client.close();
        }
        return null;
    }

    public ArrayList<TabParametro> obtenerParametroLikeNombre(String nombre) {
        try {
            client = ClientBuilder.newClient();
            webTarget = client.target(URL + "tabParametro/getListLikeNombre/" + nombre);
            gson = new Gson();
            type = new TypeToken<ArrayList<TabParametro>>(){}.getType();

            return gson.fromJson(webTarget.request().get(String.class), type);
        } catch (Exception e) {
            LOGGER.error("Obtener like nombre tabParametro: " + e.getMessage());
        } finally {
            client.close();
        }
        return null;
    }

    public void cudTabParametro(TabParametro tabParametro, char cud) {
        try {
            client = ClientBuilder.newClient();
            switch (cud) {
                case 's':
                    webTarget = client.target(URL + "tabParametro/save");
                    response = webTarget.request().post(Entity.json(tabParametro));
                    break;
                case 'u':
                    webTarget = client.target(URL + "tabParametro/update");
                    response = webTarget.request().put(Entity.json(tabParametro));
                    break;
                case 'd':
                    webTarget = client.target(URL + "tabParametro/delete/" + tabParametro.getIdParametro());
                    response = webTarget.request().put(Entity.json(tabParametro.getIdParametro()));
                    break;
            }
        } catch (Exception e) {
            LOGGER.error("cud Parametro: " + e.getMessage());
        } finally {
            response.close();
            client.close();
        }
    }

    /**=========== PERSONA ===========**/
    public TabPersona obtenerPersonaXIdentificacion(String identificacion) {
        try {
            model = "tabPersona/";
            client = ClientBuilder.newClient();
            webTarget = client.target(URL + model + "getByIdent/" + identificacion);

            return webTarget.request().get(TabPersona.class);
        } catch (Exception e) {
            LOGGER.error("Obtener x identificacion " + model + ": " + e.getMessage());
        } finally {
            client.close();
        }
        return null;
    }

    public TabPersona cudPersona(TabPersona tabPersona, char cud) {
        TabPersona persona = new TabPersona();
        try {
            model = "tabPersona/";
            client = ClientBuilder.newClient();
            switch (cud) {
                case 's':
                    webTarget = client.target(URL + model + "save");
                    response = webTarget.request().post(Entity.json(tabPersona));
                    persona = response.readEntity(TabPersona.class);
                    break;
                case 'u':
                    webTarget = client.target(URL + model + "update");
                    response = webTarget.request().put(Entity.json(tabPersona));
                    break;
            }
        } catch (Exception e) {
            LOGGER.error("cud " + model + ": " + e.getMessage());
        } finally {
            response.close();
            client.close();
        }
        return persona;
    }

    /**=========== PARROQUIA ===========**/
    public ArrayList<TabParroquia> listarTabParroquias() {
        try {
            client = ClientBuilder.newClient();
            webTarget = client.target(URL + "tabParroquia/getList");
            gson = new Gson();
            type = new TypeToken<ArrayList<TabParroquia>>(){}.getType();

            return gson.fromJson(webTarget.request().get(String.class), type);
        } catch (Exception e) {
            LOGGER.error("Listar parroquia: " + e.getMessage());
        } finally {
            client.close();
        }
        return null;
    }

    public boolean encontraParroquiaXId(String cod) {
        try {
            client = ClientBuilder.newClient();
            webTarget = client.target(URL + "tabParroquia/existsById/" + cod);

            return webTarget.request().get(Boolean.class);
        } catch (Exception e) {
            LOGGER.error("Encontrar parroquia por id: " + e.getMessage());
        } finally {
            client.close();
        }
        return false;
    }

    public void cudTabParroquia(TabParroquia tabParroquia, char cud) {
        try {
            client = ClientBuilder.newClient();
            switch (cud) {
                case 's':
                    webTarget = client.target(URL + "tabParroquia/save");
                    response = webTarget.request().post(Entity.json(tabParroquia));
                    break;
                case 'u':
                    webTarget = client.target(URL + "tabParroquia/update");
                    response = webTarget.request().put(Entity.json(tabParroquia));
                    break;
            }
        } catch (Exception e) {
            LOGGER.error("cud Parroquia: " + e.getMessage());
        } finally {
            response.close();
            client.close();
        }
    }

    /**=========== PROVINCIA ===========**/
    public ArrayList<TabProvincia> listarTabProvincias() {
        try {
            client = ClientBuilder.newClient();
            webTarget = client.target(URL + "tabProvincia/getList");
            gson = new Gson();
            type = new TypeToken<ArrayList<TabProvincia>>(){}.getType();

            return gson.fromJson(webTarget.request().get(String.class), type);
        } catch (Exception e) {
            LOGGER.error("Listar provincia: " + e.getMessage());
        } finally {
            client.close();
        }
        return null;
    }

    public TabProvincia obtenerProvinciaXId(String cod) {
        try {
            client = ClientBuilder.newClient();
            webTarget = client.target(URL + "tabProvincia/getById/" + cod);

            return webTarget.request().get(TabProvincia.class);
        } catch (Exception e) {
            LOGGER.error("Obtener provincia por id: " + e.getMessage());
        } finally {
            client.close();
        }
        return null;
    }

    public void cudTabProvincia(TabProvincia tabProvincia, char cud) {
        try {
            client = ClientBuilder.newClient();
            switch (cud) {
                case 's':
                    webTarget = client.target(URL + "tabProvincia/save");
                    response = webTarget.request().post(Entity.json(tabProvincia));
                    break;
                case 'u':
                    webTarget = client.target(URL + "tabProvincia/update");
                    response = webTarget.request().put(Entity.json(tabProvincia));
                    break;
            }
        } catch (Exception e) {
            LOGGER.error("cud Provincia: " + e.getMessage());
        } finally {
            response.close();
            client.close();
        }
    }

    /**=========== TIPOCUENTA ===========**/
    public ArrayList<TabTipocuenta> listarTabTipocuentas() {
        try {
            client = ClientBuilder.newClient();
            webTarget = client.target(URL + "tabTipocuenta/getList");
            gson = new Gson();
            type = new TypeToken<ArrayList<TabTipocuenta>>(){}.getType();

            return gson.fromJson(webTarget.request().get(String.class), type);
        } catch (Exception e) {
            LOGGER.error("Listar tipo cuenta: " + e.getMessage());
        } finally {
            client.close();
        }
        return null;
    }

    public TabTipocuenta obtenerTipocuentaXId(Integer id) {
        try {
            client = ClientBuilder.newClient();
            webTarget = client.target(URL + "tabTipocuenta/getById/" + id);

            return webTarget.request().get(TabTipocuenta.class);
        } catch (Exception e) {
            LOGGER.error("Obtener tipo cuenta por id: " + e.getMessage());
        } finally {
            client.close();
        }
        return null;
    }

    public void cudTabTipocuenta(TabTipocuenta tabTipocuenta, char cud) {
        try {
            client = ClientBuilder.newClient();
            switch (cud) {
                case 's':
                    webTarget = client.target(URL + "tabTipocuenta/save");
                    response = webTarget.request().post(Entity.json(tabTipocuenta));
                    break;
                case 'u':
                    webTarget = client.target(URL + "tabTipocuenta/update");
                    response = webTarget.request().put(Entity.json(tabTipocuenta));
                    break;
                case 'd':
                    webTarget = client.target(URL + "tabTipocuenta/delete/" + tabTipocuenta.getIdTipocuenta());
                    response = webTarget.request().put(Entity.json(tabTipocuenta.getIdTipocuenta()));
                    break;
            }
        } catch (Exception e) {
            LOGGER.error("cud tipo cuenta: " + e.getMessage());
        } finally {
            response.close();
            client.close();
        }
    }
}