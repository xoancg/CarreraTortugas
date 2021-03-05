package com.psp.servidor;

import java.util.Random;

// Thread habilitamos tortuga pueda ser hilo
public class Tortuga extends Thread {
    private String nombre;
    private int dorsal;
    private Carrera carrera;

    public Tortuga(String nombre, int dorsal) {
        this.nombre = nombre;
        this.dorsal = dorsal;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getDorsal() {
        return dorsal;
    }

    public void setDorsal(int dorsal) {
        this.dorsal = dorsal;
    }

    public void setCarrera(Carrera carrera) {
        this.carrera = carrera;
    }

    @Override
    public String toString() {
        return "{nombre='" + nombre + '\'' +
                ", dorsal='" + dorsal + '\'' +
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
