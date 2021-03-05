package com.psp.servidor;

import java.util.Random;

// Thread habilitamos tortuga pueda ser hilo
public class Tortuga extends Thread {
    private String nombreTortuga;
    private int dorsalTortuga;
    private Carrera carrera;

    public Tortuga(String nombreTortuga, int dorsalTortuga) {
        this.nombreTortuga = nombreTortuga;
        this.dorsalTortuga = dorsalTortuga;
    }

    public String getNombreTortuga() {
        return nombreTortuga;
    }

    public void setNombreTortuga(String nombreTortuga) {
        this.nombreTortuga = nombreTortuga;
    }

    public int getDorsalTortuga() {
        return dorsalTortuga;
    }

    public void setDorsalTortuga(int dorsalTortuga) {
        this.dorsalTortuga = dorsalTortuga;
    }

    public void setCarrera(Carrera carrera) {
        this.carrera = carrera;
    }

    @Override
    public String toString() {
        return "Tortuga{" +
                "nombreTortuga='" + nombreTortuga + '\'' +
                ", dorsalTortuga='" + dorsalTortuga + '\'' +
                '}';
    }

    // Lógica del hilo: mover posiciones
    public void run(){
        Random random = new Random();
        for (int i = 0; i < 500; i+= random.nextInt(50)) {
        }
        // Intentamos nombrar ganadora a esta tortuga
        carrera.nombrarGanadora(this);
    }
}
