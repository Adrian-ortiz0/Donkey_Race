package com.mycompany.donkeyrace.controller;

import com.mycompany.donkeyrace.models.Participante;
import com.mycompany.donkeyrace.persistance.CRUD;
import com.mycompany.donkeyrace.persistance.ConnectionDB;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ParticipantesController {
    
    public static boolean eliminarParticipante(String cedula){
        CRUD.setConexion(ConnectionDB.getConnection());
        String sql = "delete from participantes where Cedula = ?";
        return CRUD.eliminarDB(sql, cedula);
    }
    
    public static ArrayList<Participante> listarParticipantes(){
        CRUD.setConexion(ConnectionDB.getConnection());
        String sql = "select * from participantes";
        Participante participante = null;
        ArrayList<Participante> listaParticipantes = new ArrayList();
        ResultSet rs = CRUD.consultarDB(sql);
        try {
            while(rs != null && rs.next()){
                participante = new Participante();
                int id = rs.getInt("ID");
                String nombre = rs.getString("Nombre");
                String cedula = rs.getString("Cedula");
                double saldoDisponible = rs.getDouble("SaldoDisponible");
                participante.setCedula(cedula);
                participante.setId(id);
                participante.setNombre(nombre);
                participante.setSaldoDisponible(saldoDisponible);
                listaParticipantes.add(participante);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ParticipantesController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listaParticipantes;
    }
    
    public static Participante traerParticipantePorCedula(String cedula){
        CRUD.setConexion(ConnectionDB.getConnection());
        String sql = "select * from participantes where Cedula = ?";
        Participante participante = null;
        ResultSet rs = CRUD.consultarDB(sql, cedula);
        try {
            while(rs != null && rs.next()){
                participante = new Participante();
                int id = rs.getInt("ID");
                String nombre = rs.getString("Nombre");
                double saldoDisponible = rs.getDouble("SaldoDisponible");
                participante.setId(id);
                participante.setCedula(cedula);
                participante.setNombre(nombre);
                participante.setSaldoDisponible(saldoDisponible);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ParticipantesController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return participante;
    }
    
    public static boolean editarParticipante(String nombre, String cedula, double saldo, int id){
        CRUD.setConexion(ConnectionDB.getConnection());
        String sql = "UPDATE participantes set Nombre = ?, Cedula = ?, SaldoDisponible = ? WHERE ID = ?";
        Object [] params = {
            nombre,
            cedula,
            saldo,
            id
        };
        return CRUD.updateDB(sql, params);
    }
    
    public static boolean registrarParticipante(Participante participante){
        CRUD.setConexion(ConnectionDB.getConnection());
        String sql = "insert into participantes (Nombre, Cedula, SaldoDisponible) values (?, ?, ?)";
        Object [] params = {
            participante.getNombre(),
            participante.getCedula(),
            participante.getSaldoDisponible()
        };
        return CRUD.insertarDB(sql, params);
    }
}
