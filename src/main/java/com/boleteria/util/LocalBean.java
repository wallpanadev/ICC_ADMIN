package com.boleteria.util;

import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import java.io.Serializable;
import java.util.Locale;

@Named
@SessionScoped
public class LocalBean implements Serializable {
    private Locale locale;

    public LocalBean() {
        locale = FacesContext.getCurrentInstance().getViewRoot().getLocale();
    }

    public Locale getLocale() {
        return locale;
    }

    public String getIdioma() {
        return locale.getLanguage();
    }

    public void setIdioma(String idioma) {
        locale = new Locale(idioma);
    }
}