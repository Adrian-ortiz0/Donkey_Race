package com.mycompany.donkeyrace.views;

import com.mycompany.donkeyrace.controller.ApuestasController;
import com.mycompany.donkeyrace.controller.CompetenciasController;
import com.mycompany.donkeyrace.controller.DueñosController;
import com.mycompany.donkeyrace.controller.ParticipantesController;
import com.mycompany.donkeyrace.models.Burro;
import com.mycompany.donkeyrace.models.Competencia;
import com.mycompany.donkeyrace.models.Dueño;
import com.mycompany.donkeyrace.models.Participante;
import java.util.ArrayList;
import java.util.Scanner;

public class ApuestasUI {

    private Scanner scanner;

    public ApuestasUI() {
        this.scanner = new Scanner(System.in);
    }

    public void menuApuestas() {
        OUTER:
        while (true) {
            System.out.println("Bienvenido al menu de apuestas");
            System.out.println("==============================");
            System.out.println();
            System.out.println("1. Apostar");
            System.out.println("2. Recargar");
            System.out.println("0. Salir");
            int input = Integer.parseInt(scanner.nextLine());
            switch (input) {
                case 0 -> {
                    System.out.println("Saliendo...");
                    break OUTER;
                }
                case 1 ->                     {
                        System.out.println("Ingrese la cedula del participante: ");
                        String cedula = scanner.nextLine();
                        Participante participante = ParticipantesController.traerParticipantePorCedula(cedula);
                        if (participante == null) {
                            System.out.println("El participante no existe");
                        } else {
                            System.out.println("Seleccione en que competencia en la que desea apostar: ");
                            ArrayList<Competencia> competencias = CompetenciasController.listarCompetenciasProgramadas();
                            if (competencias.isEmpty()) {
                                System.out.println("No hay competencias registradas.");
                                return;
                            } else {
                                for (int i = 0; i < competencias.size(); i++) {
                                    Competencia competencia = competencias.get(i);
                                    System.out.println((i + 1) + ". " + "| Fecha: " + competencia.getFecha() + " | Lugar: " + competencia.getLugar());
                                    System.out.println("-------------------------------------------------------------------------------------------");
                                }
                                int eleccionCompetencia = Integer.parseInt(scanner.nextLine());
                                Competencia competenciaSeleccionada = competencias.get(eleccionCompetencia - 1);
                                System.out.println("Seleccione el burro por el que desea apostar: ");
                                ArrayList<Burro> burrosCompetencia = CompetenciasController.listarBurrosPorCompetencia(competenciaSeleccionada.getId());
                                for(int i = 0; i < burrosCompetencia.size(); i++){
                                    Burro burro = burrosCompetencia.get(i);
                                    System.out.println((i + 1) + ". " + burro.getNombre() + " | Edad: " + burro.getEdad() + " | Raza: " + burro.getRaza());
                                }
                                int eleccionBurro = Integer.parseInt(scanner.nextLine());
                                Burro burroElegido = burrosCompetencia.get(eleccionBurro - 1);
                                
                                if(burroElegido == null){
                                    System.out.println("Error en la eleccion de burro");
                                    return;
                                } else {
                                    System.out.println("Cuanto desea apostar por " + burroElegido.getNombre());
                                    double montoApostado = Double.parseDouble(scanner.nextLine());
                                    if(montoApostado > participante.getSaldoDisponible()){
                                        System.out.println("No tienes esa cantidad para apostar (Cantidad Actual:" + participante.getSaldoDisponible()+")");
                                        return;
                                    }
                                    boolean apuesta = ApuestasController.realizarApuesta(participante, burroElegido, competenciaSeleccionada, montoApostado);
                                    if(apuesta){
                                        System.out.println("Apuesta realizada!");
                                    } else {
                                        System.out.println("Error al hacer la apuesta");
                                    }
                                }
                                
                            }
                        }                          }
                case 2 ->                     {
                        System.out.println("Escribe el numero de cedula del participante a recargar");
                        String cedula = scanner.nextLine();
                        Participante participante = ParticipantesController.traerParticipantePorCedula(cedula);
                        if (participante == null) {
                            System.out.println("El participante no existe!");
                            return;
                        } else {
                            System.out.println("Ingresa el valor de saldo a recargar: ");
                            double saldoARecargar = Double.parseDouble(scanner.nextLine());
                            participante.recargarSaldo(saldoARecargar);
                            boolean actualizacionDeSaldo = ParticipantesController.editarParticipante(participante.getNombre(), participante.getCedula(), saldoARecargar, participante.getId());
                            if (actualizacionDeSaldo) {
                                System.out.println("Saldo recargado correctamente");
                            } else {
                                System.out.println("Error al recargar saldo");
                            }
                        }                          }
                default -> {
                }
            }
        }
    }
}
