package com.mycompany.donkeyrace.controller;

import com.mycompany.donkeyrace.models.Burro;
import com.mycompany.donkeyrace.models.Dueño;

import com.mycompany.donkeyrace.persistance.CRUD;
import com.mycompany.donkeyrace.persistance.ConnectionDB;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BurrosController {
    
    public static boolean eliminarBurro(int idBurro){
        String sql = "delete from burros where ID = ?";
        return CRUD.eliminarDB(sql,idBurro);
    }

    public static boolean editarBurro(String nombre, String edad, String raza, int id) {
        String sql = "UPDATE burros SET Nombre = ?, Edad = ?, Raza = ? WHERE ID = ?";
        return CRUD.updateDB(sql, nombre, edad, raza, id);
    }

    public static boolean registrarBurro(Burro burro, int idDueño) {
        CRUD.setConexion(ConnectionDB.getConnection());
        String sql = "insert into burros (Nombre, Edad, Raza, ID_Dueño) values (?, ?, ?, ?)";
        Object[] params = {
            burro.getNombre(),
            burro.getEdad(),
            burro.getRaza(),
            idDueño
        };
        return CRUD.insertarDB(sql, params);
    }

    public static ArrayList<Burro> mostrarBurrosDeDueño(String cedula) {
        CRUD.setConexion(ConnectionDB.getConnection());
        Dueño dueño = DueñosController.obtenerDueñoPorCedula(cedula);
        int idDueño = dueño.getId();
        String sql = "select ID, Nombre, Edad, Raza from burros where ID_Dueño = ?;";
        Object[] params = {
            idDueño
        };
        ResultSet rs = CRUD.consultarDB(sql, params);
        try {
            while (rs != null && rs.next()) {
                int id = rs.getInt("ID");
                String nombre = rs.getString("Nombre");
                String edad = rs.getString("Edad");
                String raza = rs.getString("Raza");
                Burro burro = new Burro();
                burro.setId(id);
                burro.setDueño(dueño);
                burro.setEdad(edad);
                burro.setNombre(nombre);
                burro.setRaza(raza);
                dueño.getBurros().add(burro);
            }
        } catch (SQLException ex) {
            Logger.getLogger(BurrosController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return dueño.getBurros();
    }

    public static void listarTodosLosBurros() {
        CRUD.setConexion(ConnectionDB.getConnection());
        String sql = "SELECT b.ID, b.Nombre AS Nombre_Burro, b.Edad, b.Raza, d.Nombre AS Nombre_Dueño FROM burros b JOIN dueños d ON b.ID_Dueño = d.ID";
        ResultSet rs = CRUD.consultarDB(sql);
        try {
            while (rs != null && rs.next()) {
                int id = rs.getInt("ID");
                String nombre = rs.getString("Nombre_Burro");
                String edad = rs.getString("Edad");
                String raza = rs.getString("Raza");
                String nombreDueño = rs.getString("Nombre_Dueño");
                System.out.println("ID: " + id + " Nombre: " + nombre + " Edad: " + edad + " Raza: " + raza + " Nombre Dueño: " + nombreDueño);
            }
        } catch (SQLException ex) {
            Logger.getLogger(BurrosController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
