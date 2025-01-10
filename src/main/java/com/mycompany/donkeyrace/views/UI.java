
package com.mycompany.donkeyrace.views;

import java.util.Scanner;

public class UI {
    private Scanner scanner;
    
    public UI(){
        this.scanner = new Scanner(System.in);
    }
    
    public void start(){
        OUTER:
        while (true) {
            System.out.println("Bienvenido al sistema Donkey Race!");
            System.out.println("==================================");
            System.out.println();
            System.out.println("Que deseas hacer?");
            System.out.println("1. Gestion burros");
            System.out.println("2. Gestion apostadores");
            System.out.println("3. Gestion competencias");
            System.out.println("4. Gestion apuestas");
            System.out.println("0. Salir");
            int input = Integer.parseInt(scanner.nextLine());
            switch (input) {
                case 0 -> {
                    System.out.println("Saliendo...");
                    break OUTER;
                }
                case 1 -> {
                    BurrosUI bu = new BurrosUI();
                    bu.menuBurros();
                }
                case 2 -> {
                    ParticipantesUI pu = new ParticipantesUI();
                    pu.menuParticipantes();
                }
                case 3 -> {
                    CompetenciasUI cu = new CompetenciasUI();
                    cu.menuCompetencias();
                }
                case 4 -> {
                    ApuestasUI au = new ApuestasUI();
                    au.menuApuestas();
                }
                default -> {
                }
            }
        }
    }
}
