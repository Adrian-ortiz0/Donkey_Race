
package com.mycompany.donkeyrace.models;

import java.time.LocalDate;

public class Competencia {
    private int id;
    private LocalDate fecha;
    private String lugar;
    private String estado;
    private Burro ganador;
    private int capacidad;

    public Competencia() {
    }

    public Competencia(int id, LocalDate fecha, String lugar, String estado, Burro ganador) {
        this.id = id;
        this.fecha = fecha;
        this.lugar = lugar;
        this.estado = estado;
        this.ganador = ganador;
    }

    public int getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(int capacidad) {
        this.capacidad = capacidad;
    }

    public Burro getGanador() {
        return ganador;
    }

    public void setGanador(Burro ganador) {
        this.ganador = ganador;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
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
