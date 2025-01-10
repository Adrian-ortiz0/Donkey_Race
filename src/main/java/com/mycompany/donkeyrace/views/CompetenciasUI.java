package com.mycompany.donkeyrace.views;

import com.mycompany.donkeyrace.controller.BurrosController;
import com.mycompany.donkeyrace.controller.CompetenciasController;
import com.mycompany.donkeyrace.controller.DueñosController;
import com.mycompany.donkeyrace.models.Burro;
import com.mycompany.donkeyrace.models.Competencia;
import com.mycompany.donkeyrace.models.Dueño;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;

public class CompetenciasUI {
    private Scanner scanner;
    
    public CompetenciasUI(){
        this.scanner = new Scanner(System.in);
    }
    
    public void menuCompetencias(){
        while(true){
            System.out.println("Bienvenido al menu de competencias");
            System.out.println("==================================");
            System.out.println("1. Crear competencia");
            System.out.println("2. Listar competencias actuales");
            System.out.println("3. Registrar burros a competencias");
            System.out.println("4. Finalizar competencias");
            System.out.println("5. Listar ganadores");
            System.out.println("0. Salir");
            int input = Integer.parseInt(scanner.nextLine());
            if(input == 0){
                System.out.println("Volviendo...");
                break;
            } else if (input == 1){
                System.out.println("Ingresar fecha de la competencia (formato: YYYY-MM-DD): ");
                LocalDate fechaCompetencia = null;

                while (fechaCompetencia == null) {
                    try {
                        fechaCompetencia = LocalDate.parse(scanner.nextLine());
                    } catch (DateTimeParseException e) {
                        System.out.println("Fecha inválida. Por favor, ingrese una fecha en el formato YYYY-MM-DD:");
                    }
                }
                System.out.println("Ingresa el lugar de la competencia: ");
                String lugar = scanner.nextLine();
                String estado = "Programada";
                Competencia competencia = new Competencia();
                competencia.setEstado(estado);
                competencia.setFecha(fechaCompetencia);
                competencia.setLugar(lugar);
                boolean registro = CompetenciasController.registrarCompetencia(competencia);
                if(registro){
                    System.out.println("Competencia registrada con exito!");
                } else {
                    System.out.println("Error en registro");
                }
            } else if(input == 2){
                ArrayList<Competencia> competencias = CompetenciasController.listarCompetencias();
                if (competencias.isEmpty()) {
                    System.out.println("No hay competencias registradas.");
                    return;
                } else {
                    for (Competencia c : competencias) {
                        System.out.println("ID: " + c.getId());
                        System.out.println("Fecha: " + c.getFecha());
                        System.out.println("Lugar: " + c.getLugar());
                        System.out.println("Estado: " + c.getEstado());
                        System.out.println("------------------------");
                    }
                }
            } else if(input == 3){
                System.out.println("Ingresa la cedula del dueño: ");
                String cedula = scanner.nextLine();
                Dueño dueño = DueñosController.obtenerDueñoPorCedula(cedula);
                if(dueño == null){
                    System.out.println("No existe el dueño");
                    return;
                }
                System.out.println("Selecciona un burro para inscribir: ");
                ArrayList<Burro> burroList = BurrosController.mostrarBurrosDeDueño(cedula);
                for (int i = 0; i < burroList.size(); i++) {
                    Burro burro = burroList.get(i);
                    System.out.println((i + 1) + ". " + burro.getNombre());
                }
                int eleccionBurro = Integer.parseInt(scanner.nextLine());
                Burro burroSeleccionado = burroList.get(eleccionBurro - 1);
                
                System.out.println("Seleccione en que competencia inscribira al burro: ");
                ArrayList<Competencia> competencias = CompetenciasController.listarCompetenciasProgramadas();
                if (competencias.isEmpty()) {
                    System.out.println("No hay competencias registradas.");
                    return;
                } else {
                    for (int i = 0; i < competencias.size(); i++) {
                        Competencia competencia = competencias.get(i);
                        System.out.println((i + 1) + ". " + "| Fecha: " + competencia.getFecha() + " | Lugar: " + competencia.getLugar());
                        System.out.println("------------------------");
                    }
                    int eleccionCompetencia = Integer.parseInt(scanner.nextLine());
                    Competencia competenciaSeleccionada = competencias.get(eleccionCompetencia - 1);
                    boolean registro = CompetenciasController.registrarBurrosACompetencias(competenciaSeleccionada, burroSeleccionado);
                    if(registro){
                        System.out.println("Burro registrado con exito!");
                    } else {
                        System.out.println("Error en registro!");
                    }
                }
            }
        }
    }
}
