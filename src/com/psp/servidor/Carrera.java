package com.psp.servidor;

import java.util.ArrayList;

public class Carrera {
    private ArrayList<Tortuga> tortugas;
    private Tortuga ganadora;

    public Tortuga getGanadora() {
        return ganadora;
    }

    // Constructor
    public Carrera(ArrayList<Tortuga> tortugas) {
        this.tortugas = tortugas;
    }

    public void iniciarCarrera(){

        for (int i = 0; i < tortugas.size(); i++) {
            Tortuga tortuga = tortugas.get(i);
            // Le decimos a la tortuga en qué carrera participará (podría haber varias carreras)
            tortuga.setCarrera(this);
            tortuga.start();
        }
    }

    public synchronized void nombrarGanadora(Tortuga tortuga){
        if(ganadora == null) {
            ganadora = tortuga;
        } else {
            System.out.println("Has perdido");
        }

    }

    // Esperamos a que todas las tortugas terminen
    public void terminarCarrera() throws InterruptedException {
        for (int i = 0; i < tortugas.size(); i++) {
            Tortuga tortuga = tortugas.get(i);
            // Bloqueamos el hilo hasta que cada tortuga termine
            tortuga.join();
        }
    }
}
