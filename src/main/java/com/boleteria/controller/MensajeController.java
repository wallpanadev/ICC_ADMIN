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
                asignarMensaje(new MensajeToast("Advertencia", "No se encontro información", MensajeToast.ICONO_WARN, MensajeToast.COLOR_WARN));
                break;
            case 6:
                asignarMensaje(new MensajeToast("Incompleto", "Primero debe ingresar la ciudad de origen", MensajeToast.ICONO_ERROR, MensajeToast.COLOR_ERROR));
                break;
            case 7:
                asignarMensaje(new MensajeToast("Error Fecha", "Debe ingresar una fecha que se encuentre dentro del rango establecido", MensajeToast.ICONO_ERROR, MensajeToast.COLOR_ERROR));
                break;
            case 8:
                asignarMensaje(new MensajeToast("Incompleto", "Primero debe ingresar la fecha de ida", MensajeToast.ICONO_ERROR, MensajeToast.COLOR_ERROR));
                break;
            case 9:
                asignarMensaje(new MensajeToast("Sin buses", "No existen unidades para la fecha y ruta ingresada"));
                break;
            case 10:
                asignarMensaje(new MensajeToast("Máximo de Asientos", "Agregue más pasajeros para poder seleccionar asientos adicionales", MensajeToast.ICONO_WARN, MensajeToast.COLOR_WARN));
                break;
            case 11:
                asignarMensaje(new MensajeToast("Selección Automatica Activo", "Deshabilite la seleccion automatica para poder escoger los asientos deseados"));
                break;
            case 12:
                asignarMensaje(new MensajeToast("Seleccion Asientos", "Debe seleccionar todos los asientos para los pasajeros", MensajeToast.ICONO_ERROR, MensajeToast.COLOR_ERROR));
                break;
            case 13:
                asignarMensaje(new MensajeToast("Límite Pasajeros", "Límite pasajeros sobrepasado", MensajeToast.ICONO_ERROR, MensajeToast.COLOR_ERROR));
                break;
            case 14:
                asignarMensaje(new MensajeToast("Sin buses - Retorno", "No existen unidades para la fecha y ruta de regreso ingresado", MensajeToast.ICONO_ERROR, MensajeToast.COLOR_ERROR));
                break;
            case 15:
                asignarMensaje(new MensajeToast("Error fecha de nacimiento", "Solo puede realizar la compra si es mayor de edad", MensajeToast.ICONO_ERROR, MensajeToast.COLOR_ERROR));
                break;
            case 16:
                asignarMensaje(new MensajeToast("Celular Incorrecto", "Ingrese un número de celular valido", MensajeToast.ICONO_ERROR, MensajeToast.COLOR_ERROR));
                break;
            case 17:
                asignarMensaje(new MensajeToast("Dirección Incorrecta", "Ingrese una dirección valida", MensajeToast.ICONO_ERROR, MensajeToast.COLOR_ERROR));
                break;
            case 18:
                asignarMensaje(new MensajeToast("Error Pasajeros", "Un adulto debe realizar la compra", MensajeToast.ICONO_ERROR, MensajeToast.COLOR_ERROR));
                break;
            case 19:
                asignarMensaje(new MensajeToast("Falta autobus ida", "Debe seleccionar el aubotbus de ida", MensajeToast.ICONO_ERROR, MensajeToast.COLOR_ERROR));
                break;
            case 20:
                asignarMensaje(new MensajeToast("Incompleto", "Debe leer y aceptar los términos de la compañía", MensajeToast.ICONO_ERROR, MensajeToast.COLOR_ERROR));
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