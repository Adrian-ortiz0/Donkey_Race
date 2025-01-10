package com.mycompany.donkeyrace.controller;

import com.mycompany.donkeyrace.models.Burro;
import com.mycompany.donkeyrace.models.Competencia;
import com.mycompany.donkeyrace.models.Participante;
import com.mycompany.donkeyrace.persistance.CRUD;
import com.mycompany.donkeyrace.persistance.ConnectionDB;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ApuestasController {

    public static boolean realizarApuesta(Participante participante, Burro burro, Competencia competencia, double montoApostado) {
        CRUD.setConexion(ConnectionDB.getConnection());

        String sql = "INSERT INTO apuestas (ID_Participante, ID_Burro, ID_Competencia, MontoApostado) VALUES (?, ?, ?, ?)";

        boolean apuestaInsertada = CRUD.insertarDB(sql, participante.getId(), burro.getId(), competencia.getId(), montoApostado);
        if (apuestaInsertada) {
            System.out.println("Apuesta realizada con éxito.");
            return true;
        } else {
            System.out.println("Error al realizar la apuesta.");
        }

        return false;
    }

}
