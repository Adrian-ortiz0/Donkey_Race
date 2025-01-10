package com.mycompany.donkeyrace.models;

public class Apuesta {
    private int id;
    private double montoApostado;
    private Participante participante;
    private Burro burro;
    private Competencia competencia;

    public Apuesta() {
    }

    public Apuesta(int id, double montoApostado, Participante participante, Burro burro, Competencia competencia) {
        this.id = id;
        this.montoApostado = montoApostado;
        this.participante = participante;
        this.burro = burro;
        this.competencia = competencia;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getMontoApostado() {
        return montoApostado;
    }

    public void setMontoApostado(double montoApostado) {
        this.montoApostado = montoApostado;
    }

    public Participante getParticipante() {
        return participante;
    }

    public void setParticipante(Participante participante) {
        this.participante = participante;
    }

    public Burro getBurro() {
        return burro;
    }

    public void setBurro(Burro burro) {
        this.burro = burro;
    }

    public Competencia getCompetencia() {
        return competencia;
    }

    public void setCompetencia(Competencia competencia) {
        this.competencia = competencia;
    }
    
}