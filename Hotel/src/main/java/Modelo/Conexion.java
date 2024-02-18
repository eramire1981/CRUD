/*

 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.sql.*;

public class Conexion {
      public static Connection conexion1= null;
    
    public static Connection obtener()
        throws SQLException, ClassNotFoundException{
       
        if (conexion1 == null) {
            
         try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conexion1 = DriverManager.getConnection("jdbc:mysql://localhost:3306/hotel", "root", "admin123");
             
         } catch (SQLException ex) {
            throw new SQLException(ex);
         } catch (ClassNotFoundException ex) {
            throw new ClassCastException(ex.getMessage());
         }
      }
        return conexion1;
    }
    
    public static void main(String[] args) {
        Conexion conexion2 = new Conexion();
        System.out.println("hola");
        try{
            if (conexion2.obtener()!=null) {
                System.out.println("La conexión es exitosa");
            }else{
                System.out.println("La conexión es fallida");   
            }
            
        }catch(SQLException ex) {
            } catch (ClassNotFoundException ex) {
            throw new ClassCastException(ex.getMessage());
         }
    }
}
