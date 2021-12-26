package com.boleteria.util;

import java.util.Timer;
import java.util.TimerTask;

public class Cronometro {
    private Timer timer;
    protected int segundo = 0;
    protected int tiempoEspera = 15;

    protected Cronometro() {
    }

    protected Cronometro(int tiempoEspera) {
        this.tiempoEspera = tiempoEspera;
    }

    private TimerTask controlar() {
        return new TimerTask() {
            @Override
            public void run() {
                segundo++;
                if (segundo == tiempoEspera) {
                    segundo = 0;
                    timer.cancel();
                }
            }
        };
    }

    public void ejecutar() {
        if (segundo == 0) {
            timer = new Timer();
            timer.scheduleAtFixedRate(controlar(), 0, 1000);
        }
    }
}