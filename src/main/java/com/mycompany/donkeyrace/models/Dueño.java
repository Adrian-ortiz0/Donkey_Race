
package com.mycompany.donkeyrace.models;

import java.util.ArrayList;

public class Dueño {
    private int id;
    private String nombre;
    private String cedula;
    private ArrayList<Burro> burros;

    public Dueño() {
        this.burros = new ArrayList();
    }

    public Dueño(int id, String nombre, String cedula, ArrayList<Burro> burros) {
        this.id = id;
        this.nombre = nombre;
        this.cedula = cedula;
        this.burros = new ArrayList();
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
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

    public ArrayList<Burro> getBurros() {
        return burros;
    }

    public void setBurros(ArrayList<Burro> burros) {
        this.burros = burros;
    }
    
    
}
