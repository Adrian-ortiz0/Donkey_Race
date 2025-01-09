
package com.mycompany.donkeyrace.models;

import java.time.LocalDate;

public class Competencia {
    private int id;
    private LocalDate fecha;
    private String lugar;

    public Competencia() {
    }

    
    
    public Competencia(int id, LocalDate fecha, String lugar) {
        this.id = id;
        this.fecha = fecha;
        this.lugar = lugar;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public String getLugar() {
        return lugar;
    }

    public void setLugar(String lugar) {
        this.lugar = lugar;
    }
    
    
}
