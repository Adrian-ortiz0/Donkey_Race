
package com.mycompany.donkeyrace.views;

import java.util.Scanner;

public class UI {
    private Scanner scanner;
    
    public UI(){
        this.scanner = new Scanner(System.in);
    }
    
    public void start(){
        while(true){
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
            if(input == 0){
                System.out.println("Saliendo...");
                break;
            } else if (input == 1){
                BurrosUI bu = new BurrosUI();
                bu.menuBurros();
            } else if(input == 2){
                ParticipantesUI pu = new ParticipantesUI();
                pu.menuParticipantes();
            }
        }
    }
}
