package com.boleteria.controller;

import com.boleteria.enums.TipoMensaje;
import com.boleteria.util.MensajeToast;

import javax.inject.Named;
import java.io.Serializable;

@Named
public class MensajeController implements Serializable {
    private static String titulo;
    private static String detalle;
    private static String iconoMensaje = MensajeToast.ICONO_INFO.getNombreIcono();
    private static String colorMensaje = MensajeToast.COLOR_INFO.getNombreColor();

    public static void contruirMensaje(TipoMensaje tipoMensaje, int ... args) {
        int tipoMsg = tipoMensaje.ordinal();
        switch (tipoMsg) {
            case 0:
                asignarMensaje(new MensajeToast("En proceso", "Recargando tabla..."));
                break;
            case 1:
                asignarMensaje(new MensajeToast(
                        "Tiempo do espera",
                        "Tiene que esperar " + (args[0] - args[1]) + " seg. para volver a recargar la tabla"));
                break;
            case 2:
                asignarMensaje(new MensajeToast("Exito", "Información actualizada correctamente"));
                break;
            case 3:
                asignarMensaje(new MensajeToast("Exito", "Información guardada correctamente"));
                break;
            case 4:
                asignarMensaje(new MensajeToast("Exito", "Información eliminada correctamente"));
                break;
            case 5:
                asignarMensaje(new MensajeToast("Sin datos", "La lista de pasajero se encuentra vacia", MensajeToast.ICONO_WARN, MensajeToast.COLOR_WARN));
                break;
            case 6:
                asignarMensaje(new MensajeToast("Busqueda Exitosa", "Se ha encontrado la siguiente información"));
                break;
            case 7:
                asignarMensaje(new MensajeToast("Tabla Vacia", "No se ha encontrado información para descargar el reporte", MensajeToast.ICONO_ERROR, MensajeToast.COLOR_ERROR));
                break;
            case 8:
                asignarMensaje(new MensajeToast("En proceso...", "La descarga se realizará en unos segundos"));
                break;
        }
    }

    private static void asignarMensaje(MensajeToast mensajeToast) {
        titulo = mensajeToast.getTitulo();
        detalle = mensajeToast.getDetalle();
        iconoMensaje = mensajeToast.getIconoMensaje().getNombreIcono();
        colorMensaje = mensajeToast.getColorMensaje().getNombreColor();
    }

    public static String getTitulo() {
        return titulo;
    }

    public static void setTitulo(String titulo) {
        MensajeController.titulo = titulo;
    }

    public static String getDetalle() {
        return detalle;
    }

    public static void setDetalle(String detalle) {
        MensajeController.detalle = detalle;
    }

    public static String getIconoMensaje() {
        return iconoMensaje;
    }

    public static void setIconoMensaje(String iconoMensaje) {
        MensajeController.iconoMensaje = iconoMensaje;
    }

    public static String getColorMensaje() {
        return colorMensaje;
    }

    public static void setColorMensaje(String colorMensaje) {
        MensajeController.colorMensaje = colorMensaje;
    }
}