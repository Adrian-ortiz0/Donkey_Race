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

public class DueñosController {

    public static boolean registrarDueños(Dueño dueño) {
        CRUD.setConexion(ConnectionDB.getConnection());
        String sql = "insert into dueños (Nombre, Cedula) values (?, ?)";
        Object[] params = {
            dueño.getNombre(),
            dueño.getCedula()
        };
        return CRUD.insertarDB(sql, params);
    }

    public static Dueño obtenerDueñoPorCedula(String cedula) {
        CRUD.setConexion(ConnectionDB.getConnection());
        String sql = "select * from dueños where Cedula = ?";
        Object[] params = {cedula};
        ResultSet rs = CRUD.consultarDB(sql, params);
        Dueño dueño = null;

        try {

            if (rs != null && rs.next()) {
                dueño = new Dueño();
                int id = rs.getInt("ID");
                String nombre = rs.getString("Nombre");
                dueño.setId(id);
                dueño.setCedula(cedula);
                dueño.setNombre(nombre);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DueñosController.class.getName()).log(Level.SEVERE, null, ex);
        }

        return dueño;
    }
}
