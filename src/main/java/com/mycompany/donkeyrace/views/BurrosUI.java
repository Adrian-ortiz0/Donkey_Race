package com.mycompany.donkeyrace.views;

import com.mycompany.donkeyrace.controller.BurrosController;
import com.mycompany.donkeyrace.controller.DueñosController;
import com.mycompany.donkeyrace.models.Burro;
import com.mycompany.donkeyrace.models.Dueño;
import java.util.ArrayList;
import java.util.Scanner;

public class BurrosUI {

    private Scanner scanner;

    public BurrosUI() {
        this.scanner = new Scanner(System.in);
    }

    public void menuBurros() {
        while (true) {
            System.out.println("Bienvenido al menu de burros");
            System.out.println("=============================");
            System.out.println("1. Registrar");
            System.out.println("2. Editar");
            System.out.println("3. Listar");
            System.out.println("4. Eliminar");
            System.out.println("0. Salir");
            int input = Integer.parseInt(scanner.nextLine());
            if (input == 0) {
                System.out.println("Saliendo...");
                break;
            } else if (input == 1) {
                System.out.println("Ingrese la cedula del dueño: ");
                String cedula = scanner.nextLine();
                Dueño dueñoPosible = DueñosController.obtenerDueñoPorCedula(cedula);
                if (dueñoPosible == null) {
                    System.out.println("No existe el dueño, se pasara a registrar uno nuevo: ");
                    System.out.println("Ingrese el nombre del dueño: ");
                    String nombreDueño = scanner.nextLine();
                    Dueño dueño = new Dueño();
                    dueño.setCedula(cedula);
                    dueño.setNombre(nombreDueño);
                    DueñosController.registrarDueños(dueño);
                    System.out.println("Dueño Registrado con exito");
                    System.out.println("Desea registrar un burro? (1.Si/2.No) ");
                    int input2 = Integer.parseInt(scanner.nextLine());
                    if (input2 == 2) {
                        System.out.println("Saliendo...");
                        break;
                    } else if (input2 == 1) {
                        Dueño dueñoRegistrado = DueñosController.obtenerDueñoPorCedula(cedula);
                        System.out.println("Ingresa el nombre: ");
                        String nombre = scanner.nextLine();
                        System.out.println("Ingresa la edad: ");
                        String edad = scanner.nextLine();
                        System.out.println("Ingresa la raza: ");
                        String raza = scanner.nextLine();
                        Burro burro = new Burro();
                        burro.setNombre(nombre);
                        burro.setEdad(edad);
                        burro.setRaza(raza);
                        int idDueño = dueñoRegistrado.getId();
                        System.out.println("id dueño " + idDueño);
                        boolean burroRegistrado = BurrosController.registrarBurro(burro, idDueño);
                        if (burroRegistrado) {
                            System.out.println("Burro " + nombre + " registrado exitosamente!");
                        } else {
                            System.out.println("Error registrando el burro");
                        }
                    }
                } else {
                    System.out.println("Bienvenido dueño: " + dueñoPosible.getNombre());
                    System.out.println("Ingresa el nombre: ");
                    String nombre = scanner.nextLine();
                    System.out.println("Ingresa la edad: ");
                    String edad = scanner.nextLine();
                    System.out.println("Ingresa la raza: ");
                    String raza = scanner.nextLine();
                    Burro burro = new Burro();
                    burro.setNombre(nombre);
                    burro.setEdad(edad);
                    burro.setRaza(raza);
                    int idDueño = dueñoPosible.getId();
                    System.out.println("id dueño " + idDueño);
                    boolean burroRegistrado = BurrosController.registrarBurro(burro, idDueño);
                    if (burroRegistrado) {
                        System.out.println("Burro " + nombre + " registrado exitosamente!");
                    } else {
                        System.out.println("Error registrando el burro");
                    }
                }
            } else if (input == 2) {
                System.out.println("Ingresa la cedula del dueño: ");
                String cedula = scanner.nextLine();
                System.out.println("Selecciona un burro para editar: ");

                ArrayList<Burro> burroList = BurrosController.mostrarBurrosDeDueño(cedula);
                for (int i = 0; i < burroList.size(); i++) {
                    Burro burro = burroList.get(i);
                    System.out.println((i + 1) + ". " + burro.getNombre());
                }
                int eleccionBurro = Integer.parseInt(scanner.nextLine());
                Burro burroSeleccionado = burroList.get(eleccionBurro - 1);

                System.out.println("Que deseas editar?");
                System.out.println("1. Nombre");
                System.out.println("2. Edad");
                System.out.println("3. Raza");
                System.out.println("4. Salir");

                int opcionEditar = Integer.parseInt(scanner.nextLine());

                switch (opcionEditar) {
                    case 1:
                        System.out.println("Ingresa el nuevo nombre: " + " Nombre actual: " + "(" + burroSeleccionado.getNombre() + ")");
                        String nuevoNombre = scanner.nextLine();
                        burroSeleccionado.setNombre(nuevoNombre);
                        if(BurrosController.editarBurro(nuevoNombre, burroSeleccionado.getEdad(), burroSeleccionado.getRaza(), burroSeleccionado.getId())){
                            System.out.println("El nombre se actualizo correctamente");
                        } else {
                            System.out.println("Error al actualizar");
                        }
                        break;
                    case 2:
                        System.out.println("Ingresa la nueva edad: " + " Edad actual: " + "(" + burroSeleccionado.getEdad() + ")");
                        String nuevaEdad = scanner.nextLine();
                        burroSeleccionado.setEdad(nuevaEdad);
                        if(BurrosController.editarBurro(burroSeleccionado.getNombre(), nuevaEdad, burroSeleccionado.getRaza(), burroSeleccionado.getId())){
                            System.out.println("La edad del burro se actualizo");
                        } else {
                            System.out.println("Error al actualizar");
                        }
                        break;
                    case 3:
                        System.out.println("Ingresa nueva raza: " + " Raza actual: " + "(" + burroSeleccionado.getRaza() + ")");
                        String nuevaRaza = scanner.nextLine();
                        burroSeleccionado.setRaza(nuevaRaza);
                        if(BurrosController.editarBurro(burroSeleccionado.getNombre(), burroSeleccionado.getEdad(), nuevaRaza, burroSeleccionado.getId())){
                            System.out.println("La raza del burro se actualizo");
                        } else {
                            System.out.println("Error al actualizar");
                        }
                        break;
                    case 4:
                        System.out.println("Saliendo sin realizar cambios.");
                        return; 
                    default:
                        System.out.println("Opcion invalida. Intenta nuevamente.");
                        break;
                }

            } else if (input == 3) {
                BurrosController.listarTodosLosBurros();
            } else if (input == 4) {
                System.out.println("Ingrese la cedula del dueño: ");
                String cedula = scanner.nextLine();
                System.out.println("Selecciona un burro para eliminar: ");

                ArrayList<Burro> burroList = BurrosController.mostrarBurrosDeDueño(cedula);
                for (int i = 0; i < burroList.size(); i++) {
                    Burro burro = burroList.get(i);
                    System.out.println((i + 1) + ". " + burro.getNombre());
                }
                int eleccionBurro = Integer.parseInt(scanner.nextLine());
                Burro burroSeleccionado = burroList.get(eleccionBurro - 1);
                int idBurro = burroSeleccionado.getId();
                BurrosController.eliminarBurro(idBurro);
                System.out.println("Burro eliminado con exito!");
            }
        }
    }
}
