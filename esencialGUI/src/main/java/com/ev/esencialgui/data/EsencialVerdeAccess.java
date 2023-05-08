/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ev.esencialgui.data;

import java.util.ArrayList;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 *
 * @author Daniel Granados <dagranados@estudiantec.cr>
 */


public class EsencialVerdeAccess implements IDataConstants{
   private static EsencialVerdeAccess instance;
   private Connection conexion;
   
   private EsencialVerdeAccess(){
       try {
               conexion = DriverManager.getConnection(CONN_STRING);
       } catch (Exception ex){
           ex.printStackTrace();
       }
   }
   
   public synchronized static EsencialVerdeAccess getInstance() {
       if (instance == null){
           instance = new EsencialVerdeAccess();
       }
       return instance;
   }

   public void getQuery(){
       try {
            Statement stmt = conexion.createStatement();
            String SQL = "SELECT localId, nombre FROM dbo.locales";
            ResultSet rs = stmt.executeQuery(SQL);
            while (rs.next()){
                System.out.println(rs.getString("localId") + " " + rs.getString("nombre"));
            }
       } catch (Exception ex){
           ex.printStackTrace();
       }
   }
}
