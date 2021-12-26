package com.boleteria.util;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

public class MensajeToast implements Serializable {
    private String titulo;
    private String detalle;
    private IconoMensaje iconoMensaje = ICONO_INFO;
    private ColorMensaje colorMensaje = COLOR_INFO;

    public MensajeToast(String titulo, String detalle) {
        this.titulo = titulo;
        this.detalle = detalle;
    }

    public MensajeToast(String titulo, String detalle, IconoMensaje iconoMensaje, ColorMensaje colorMensaje) {
        this.titulo = titulo;
        this.detalle = detalle;
        this.iconoMensaje = iconoMensaje;
        this.colorMensaje = colorMensaje;
    }

    //-- INFO
    public static IconoMensaje ICONO_INFO = new IconoMensaje(Gravedad.INFO.icono);
    public static ColorMensaje COLOR_INFO = new ColorMensaje(Gravedad.INFO.color);
    //-- WARNING
    public static IconoMensaje ICONO_WARN = new IconoMensaje(Gravedad.WARN.icono);
    public static ColorMensaje COLOR_WARN = new ColorMensaje(Gravedad.WARN.color);
    //-- ERROR
    public static IconoMensaje ICONO_ERROR = new IconoMensaje(Gravedad.ERROR.icono);
    public static ColorMensaje COLOR_ERROR = new ColorMensaje(Gravedad.ERROR.color);

    /** Lista de iconos y colores **/
    private enum Gravedad {
        INFO("bg-success", "bi-info-circle-fill"),
        WARN("bg-warning", "bi-exclamation-triangle-fill"),
        ERROR("bg-danger", "bi-exclamation-diamond-fill");

        private String color;
        private String icono;

        Gravedad(String color, String icono) {
            this.color = color;
            this.icono = icono;
        }

        public String getColor() {
            return color;
        }

        public void setColor(String color) {
            this.color = color;
        }

        public String getIcono() {
            return icono;
        }

        public void setIcono(String icono) {
            this.icono = icono;
        }
    }
    /** Clase para icono **/
    @Getter
    @Setter
    public static class IconoMensaje {
        private String nombreIcono;

        public IconoMensaje(String nombreIcono) {
            this.nombreIcono = nombreIcono;
        }
    }
    /** Clase para color **/
    @Getter
    @Setter
    public static class ColorMensaje {
        private String nombreColor;

        public ColorMensaje(String nombreColor) {
            this.nombreColor = nombreColor;
        }
    }

    //-- Getter & Setter
    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDetalle() {
        return detalle;
    }

    public void setDetalle(String detalle) {
        this.detalle = detalle;
    }

    public IconoMensaje getIconoMensaje() {
        return iconoMensaje;
    }

    public void setIconoMensaje(IconoMensaje iconoMensaje) {
        this.iconoMensaje = iconoMensaje;
    }

    public ColorMensaje getColorMensaje() {
        return colorMensaje;
    }

    public void setColorMensaje(ColorMensaje colorMensaje) {
        this.colorMensaje = colorMensaje;
    }
}