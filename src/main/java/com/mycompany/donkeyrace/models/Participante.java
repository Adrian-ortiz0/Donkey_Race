
package com.mycompany.donkeyrace.models;

public class Participante {
    private int id;
    private String nombre;
    private String cedula;
    private double saldoDisponible;

    public Participante() {
    }

    public Participante(int id, String nombre, String cedula, double saldoDisponible) {
        this.id = id;
        this.nombre = nombre;
        this.cedula = cedula;
        this.saldoDisponible = saldoDisponible;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public double getSaldoDisponible() {
        return saldoDisponible;
    }

    public void setSaldoDisponible(double saldoDisponible) {
        this.saldoDisponible = saldoDisponible;
    }
    
}
