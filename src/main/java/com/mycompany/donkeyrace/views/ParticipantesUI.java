
package com.mycompany.donkeyrace.views;

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
            System.out.println("2. Editar");
            System.out.println("3. Eliminar");
            System.out.println("4. Listar");
            System.out.println("0. Salir");
        }
    }
}
