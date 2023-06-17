/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main.gui;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
public class Config {
    private static Connection mysqlconfig;
    public static Connection configDB() throws SQLException{
        try {
            String url="jdbc:mysql://localhost/toko_berkah";
            String user="root";
            String pass="";
            DriverManager.registerDriver(new com.mysql.jdbc.Driver());
            mysqlconfig=DriverManager.getConnection(url, user, pass);
        } catch (Exception e) {
            System.err.println("Koneksi Gagal "+e.getMessage());
        }
        return mysqlconfig;
   }
    public static boolean isConnected(){
       try{
           Config.configDB();
           System.err.println("Database Terkoneksi");
           return true;
       } catch(Exception e){
           Logger.getLogger(Config.class.getName()).log(Level.SEVERE,null,e);
           System.out.println("Koneksi Gagal");
           return false;
       }
   }
   public static void main(String[]args){
       System.out.println("Cek");
       isConnected();
   }
}
