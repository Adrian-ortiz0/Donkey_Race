package com.mycompany.donkeyrace.controller;

import com.mycompany.donkeyrace.models.Burro;
import com.mycompany.donkeyrace.models.Competencia;
import com.mycompany.donkeyrace.persistance.CRUD;
import com.mycompany.donkeyrace.persistance.ConnectionDB;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CompetenciasController {
    
//    public static ArrayList<Competencia> listarCompetenciasDelBurro(Burro burro){
//        CRUD.setConexion(ConnectionDB.getConnection());
//        String sql = "SELECT cb.ID, cb.ID_Burro, b.nombre AS nombre_burro, ";
//    }
//    
    public static boolean ocuparCapacidad(Competencia competencia){
        CRUD.setConexion(ConnectionDB.getConnection());
        String sql = "UPDATE competencias set Capacidad = ? WHERE ID = ?";
        Object [] params = {
          competencia.getCapacidad(),
          competencia.getId()
        };
        return CRUD.updateDB(sql, params);
    }
    
    public static boolean asignarGanador(Competencia competencia, Burro burro){
        CRUD.setConexion(ConnectionDB.getConnection());
        String sql = "UPDATE competencias SET ID_Ganador = ? WHERE ID = ? AND Estado = 'Finalizada'";
        Object [] params = {
            burro.getId(),
            competencia.getId()
        };
        return CRUD.updateDB(sql, params);
    }
    
    public static boolean finalizarCompetencia(Competencia competencia){
        CRUD.setConexion(ConnectionDB.getConnection());
        String sql = "UPDATE competencias SET Estado = 'Finalizada' WHERE ID = ? AND Estado = 'Programada';";
        return CRUD.updateDB(sql, competencia.getId());
    }
    
    public static ArrayList<Burro> listarBurrosPorCompetencia(int idCompetencia) {
    CRUD.setConexion(ConnectionDB.getConnection());
    String sql = "SELECT b.ID, b.Nombre, b.Edad, b.Raza " +
                 "FROM burros b " +
                 "JOIN competencias_burros cb ON b.ID = cb.ID_Burro " +
                 "WHERE cb.ID_Competencia = ?"; 
    ArrayList<Burro> burros = new ArrayList<>();
    ResultSet rs = CRUD.consultarDB(sql, idCompetencia);
    
    try {
        while (rs != null && rs.next()) {
            Burro burro = new Burro();
            burro.setId(rs.getInt("ID"));
            burro.setNombre(rs.getString("Nombre"));
            burro.setEdad(rs.getString("Edad"));
            burro.setRaza(rs.getString("Raza"));
            burros.add(burro);
        }
    } catch (SQLException ex) {
        Logger.getLogger(BurrosController.class.getName()).log(Level.SEVERE, null, ex);
    }
    return burros;
}

    public static boolean registrarBurrosACompetencias(Burro burro, Competencia competencia ){
        CRUD.setConexion(ConnectionDB.getConnection());
        String sql = "insert into competencias_burros (ID_Burro, ID_Competencia) VALUES (?, ?);";
        Object [] params = {
            burro.getId(),
            competencia.getId()
            
        };
        return CRUD.insertarDB(sql, params);
    }

    public static boolean registrarCompetencia(Competencia competencia) {
        CRUD.setConexion(ConnectionDB.getConnection());
        String sql = "insert into competencias (Fecha, Lugar, Estado) values (?, ?, ?);";
        Object[] params = {
            java.sql.Date.valueOf(competencia.getFecha()),
            competencia.getLugar(),
            competencia.getEstado(),};
        return CRUD.insertarDB(sql, params);
    }
    public static ArrayList<Competencia> listarCompetenciasProgramadas(){
        CRUD.setConexion(ConnectionDB.getConnection());
        String sql = "select ID, Fecha, Lugar, Estado, Capacidad from competencias where Estado = 'Programada'";
        ArrayList<Competencia> competencias = new ArrayList();
        Competencia competencia = null;
        ResultSet rs = CRUD.consultarDB(sql);
        try {
            while(rs != null && rs.next()){
                competencia = new Competencia();
                int id = rs.getInt("ID");
                java.sql.Date fechaSql = rs.getDate("Fecha");
                LocalDate fecha = fechaSql.toLocalDate();
                String lugar = rs.getString("Lugar");
                String estado = rs.getString("Estado");
                int capacidad = rs.getInt("Capacidad");
                
                competencia.setEstado(estado);
                competencia.setId(id);
                competencia.setFecha(fecha);
                competencia.setLugar(lugar);
                competencia.setCapacidad(capacidad);
                
                competencias.add(competencia);
            }
        } catch (SQLException ex) {
            Logger.getLogger(CompetenciasController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return competencias;
    }
    
    public static ArrayList<Competencia> listarCompetencias(){
        CRUD.setConexion(ConnectionDB.getConnection());
        String sql = "select ID, Fecha, Lugar, Estado from competencias";
        ArrayList<Competencia> competencias = new ArrayList();
        Competencia competencia = null;
        ResultSet rs = CRUD.consultarDB(sql);
        try {
            while(rs != null && rs.next()){
                competencia = new Competencia();
                int id = rs.getInt("ID");
                java.sql.Date fechaSql = rs.getDate("Fecha");
                LocalDate fecha = fechaSql.toLocalDate();
                String lugar = rs.getString("Lugar");
                String estado = rs.getString("Estado");
                
                competencia.setEstado(estado);
                competencia.setId(id);
                competencia.setFecha(fecha);
                competencia.setLugar(lugar);
                
                competencias.add(competencia);
            }
        } catch (SQLException ex) {
            Logger.getLogger(CompetenciasController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return competencias;
    }
}
