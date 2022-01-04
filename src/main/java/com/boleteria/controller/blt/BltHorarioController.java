package com.boleteria.controller.blt;

import com.boleteria.controller.BltController;
import com.boleteria.controller.ImbaController;
import com.boleteria.model.BltHorario;
import com.boleteria.model.BltHorariodetalle;
import com.boleteria.model.BltRutaparada;
import com.boleteria.model.ImbaAutobus;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

@Named("horarioController")
@ViewScoped
public class BltHorarioController implements Serializable {
    private static final Logger LOGGER = LogManager.getLogger(BltHorarioController.class);

    private final ImbaController imbaController = new ImbaController();
    private final BltController bltController = new BltController();

    private void crearHorario() {
        int idRutaAux = 1;
        ImbaAutobus imbaAutobus = imbaController.listarAutobuses().get(0);
        ArrayList<BltRutaparada> bltRutaparadas = bltController.obtenerRutaparadaXIdRuta(idRutaAux);

        StringBuilder otro = new StringBuilder();
        for (int y = 0; y < 48; y++) {
            otro.append(0).append(" ");
        }

        for (int i = 1; i <= 7; i++) {
            BltHorario bltHorario = new BltHorario();
            bltHorario.setIdRuta(idRutaAux);
            bltHorario.setIdAutobus(imbaAutobus);
            bltHorario.setAsientosHorario(otro.toString().trim());
            bltHorario.setAsientosdisponiblesHorario(imbaAutobus.getAsientosAutobus());
            bltHorario.setFechaHorario(LocalDate.now().plusDays(i));
            bltHorario.setHoraHorario(LocalTime.MIDNIGHT.plusHours(1));
            bltHorario.setEstadoHorario(true);
            bltHorario = bltController.guardarHorario(bltHorario);

            for (BltRutaparada rutaparada : bltRutaparadas) {
                BltHorariodetalle bltHorariodetalle = new BltHorariodetalle();
                bltHorariodetalle.setIdHorario(bltHorario);
                bltHorariodetalle.setIdRutaparada(rutaparada);
                bltHorariodetalle.setCantpasajeroHorariodetalle(0);
                bltHorariodetalle.setHorasalidaHorariodetalle(bltHorario.getHoraHorario());
                bltController.guardarHorariodetalle(bltHorariodetalle);
            }
        }
    }
}