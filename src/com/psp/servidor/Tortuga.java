package com.psp.servidor;

public class Tortuga {
    private String nombreTortuga;
    private int dorsalTortuga;

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

    @Override
    public String toString() {
        return "Tortuga{" +
                "nombreTortuga='" + nombreTortuga + '\'' +
                ", dorsalTortuga='" + dorsalTortuga + '\'' +
                '}';
    }
}
