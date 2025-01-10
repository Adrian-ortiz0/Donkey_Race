package com.mycompany.donkeyrace.controller;

import com.mycompany.donkeyrace.models.Apuesta;
import com.mycompany.donkeyrace.models.Burro;
import com.mycompany.donkeyrace.models.Competencia;
import com.mycompany.donkeyrace.models.Participante;
import com.mycompany.donkeyrace.persistance.CRUD;
import com.mycompany.donkeyrace.persistance.ConnectionDB;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ApuestasController {
    
    public static ArrayList<Apuesta> apuestasDeParticipante(Participante participante){
        CRUD.setConexion(ConnectionDB.getConnection());
        String sql = "select b.ID AS Burro_ID,b.Nombre AS Burro_Nombre, c.ID AS Competencia_ID, c.Lugar AS Competencia_Lugar,a.ID AS Apuesta_ID, a.MontoApostado, c.ID_Ganador AS Ganador_ID FROM apuestas a JOIN burros b ON a.ID_Burro = b.ID JOIN competencias c ON a.ID_Competencia = c.ID WHERE a.ID_Participante = ?;";
        ArrayList<Apuesta> apuestas = new ArrayList();
        Apuesta apuesta = null;
        ResultSet rs = CRUD.consultarDB(sql, participante.getId());
        try {
            while(rs != null && rs.next()){
                apuesta = new Apuesta();
                int idApuesta = rs.getInt("Apuesta_ID");
                double montoApostado = rs.getDouble("MontoApostado");
                        
                Burro burro = new Burro();
                int idBurro = rs.getInt("Burro_ID");
                String nombreBurro = rs.getString("Burro_Nombre");
                burro.setId(idBurro);
                burro.setNombre(nombreBurro);
                
                Competencia competencia = new Competencia();
                int idCompetencia = rs.getInt("Competencia_ID");
                String lugar = rs.getString("Competencia_Lugar");
                Burro burroGanador = new Burro();
                int idGanador = rs.getInt("Ganador_ID");
                burroGanador.setId(idBurro);
                competencia.setId(idCompetencia);
                competencia.setLugar(lugar);
                competencia.setGanador(burroGanador);
                
                
                
                apuesta.setId(idApuesta);
                apuesta.setParticipante(participante);
                apuesta.setBurro(burro);
                apuesta.setCompetencia(competencia);
                apuesta.setMontoApostado(montoApostado);
                
                apuestas.add(apuesta);
                
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(ApuestasController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return apuestas;
    }

    public static boolean realizarApuesta(Participante participante, Burro burro, Competencia competencia, double montoApostado) {
        CRUD.setConexion(ConnectionDB.getConnection());

        String sql = "insert into apuestas (ID_Participante, ID_Burro, ID_Competencia, MontoApostado) VALUES (?, ?, ?, ?)";

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