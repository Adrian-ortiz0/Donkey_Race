
package com.mycompany.donkeyrace.views;

import com.mycompany.donkeyrace.controller.ParticipantesController;
import com.mycompany.donkeyrace.models.Participante;
import java.util.ArrayList;
import java.util.Scanner;

public class ParticipantesUI {
    private Scanner scanner;
    
    public ParticipantesUI(){
        this.scanner = new Scanner(System.in);
    }
    
    public void menuParticipantes(){
        while(true){
            System.out.println("Bienvenido al menu de participantes!");
            System.out.println("====================================");
            System.out.println("1. Registrar");
            System.out.println("2. Editar/Recargar Saldo");
            System.out.println("3. Eliminar");
            System.out.println("4. Listar");
            System.out.println("0. Salir");
            int input = Integer.parseInt(scanner.nextLine());
            if(input == 0){
                System.out.println("Volviendo...");
                break;
            } else if(input == 1){
                System.out.println("Ingrese la cedula del participante");
                String cedula = scanner.nextLine();
                Participante participanteComprobacion = ParticipantesController.traerParticipantePorCedula(cedula);
                if(participanteComprobacion != null){
                    System.out.println("El participante ya existe!");
                    return;
                } else {
                    System.out.println("Ingrese el nombre del participante");
                    String nombre = scanner.nextLine();
                    Participante participante = new Participante();
                    participante.setCedula(cedula);
                    participante.setNombre(nombre);
                    participante.setSaldoDisponible(0);
                    boolean participanteRegistrado = ParticipantesController.registrarParticipante(participante);
                    if(participanteRegistrado){
                        System.out.println("Participante " + nombre + " Registrado con exito!");
                    } else {
                        System.out.println("Error en registro");
                    } 
                }
                
            } else if(input == 2){
                System.out.println("Escribe el numero de cedula del participante a editar/recargar");
                String cedula = scanner.nextLine();
                Participante participante = ParticipantesController.traerParticipantePorCedula(cedula);
                if(participante == null){
                    System.out.println("El participante no existe!");
                    return;
                } else {
                    System.out.println("Que parametro deseas cambiar?");
                    System.out.println("1. Nombre");
                    System.out.println("2. Cedula");
                    System.out.println("3. Recargar Saldo");
                    System.out.println("4. Salir");
                    int opcionParametro = Integer.parseInt(scanner.nextLine());
                    if(opcionParametro == 4){
                        System.out.println("Saliendo...");
                        break;
                    } else if (opcionParametro == 1){
                        System.out.println("Ingresar tu nuevo nombre. Nombre Actual: (" + participante.getNombre() + ")");
                        String nombreNuevo = scanner.nextLine();
                        boolean actualizacionDeNombre = ParticipantesController.editarParticipante(nombreNuevo, participante.getCedula(), participante.getSaldoDisponible(), participante.getId());
                        if(actualizacionDeNombre){
                            System.out.println("Se actualizo el nombre con exito");
                        } else {
                            System.out.println("Error en la actualizacion");
                        }
                    } else if (opcionParametro == 2){
                        System.out.println("Imngresa tu nueva cedula. Cedula Actual: (" + participante.getCedula() + ")");
                        String nuevaCedula = scanner.nextLine();
                        boolean actualizacionDeCedula = ParticipantesController.editarParticipante(participante.getNombre(), nuevaCedula, participante.getSaldoDisponible(), participante.getId());
                        if(actualizacionDeCedula){
                            System.out.println("Cedula actualizada con exito");
                        } else {
                            System.out.println("Error en la actualizacion");
                        }
                    } else if (opcionParametro == 3){
                        System.out.println("Ingresa el valor de saldo a recargar: ");
                        double saldoARecargar = Double.parseDouble(scanner.nextLine());
                        participante.recargarSaldo(saldoARecargar);
                        boolean actualizacionDeSaldo = ParticipantesController.editarParticipante(participante.getNombre(), participante.getCedula(), saldoARecargar, participante.getId());
                        if(actualizacionDeSaldo){
                            System.out.println("Saldo recargado correctamente");
                        } else {
                            System.out.println("Error al recargar saldo");
                        }
                    } else {
                        System.out.println("No existe esa opcion");
                        return;
                    }
                }
            } else if (input == 3){
                System.out.println("Ingresa la cedula del participante a eliminar: ");
                String cedula = scanner.nextLine();
                Participante participante = ParticipantesController.traerParticipantePorCedula(cedula);
                if(participante == null){
                    System.out.println("El participante no existe!");
                    return;
                } else {
                    boolean participanteEliminado = ParticipantesController.eliminarParticipante(cedula);
                    if(participanteEliminado){
                        System.out.println("Participante eliminado con exito!");
                    } else {
                        System.out.println("Error al eliminar participante");
                    }
                }
            } else if (input == 4){
                ArrayList<Participante> participantes = ParticipantesController.listarParticipantes();
                if(participantes.isEmpty()){
                    System.out.println("No hay participantes registrados");
                } else {
                    for(int i = 0; i < participantes.size(); i++){
                        Participante participante = participantes.get(i);
                        System.out.println((i + 1) + ". " + participante.getNombre() + "| Cedula: " + participante.getCedula() + "| Saldo Actual: " + participante.getSaldoDisponible());
                    }
                }
            }
        }
    }
}
