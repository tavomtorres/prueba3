/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

/**
 *
 * @author Jesus Trejo
 */
public class Util {
 
    public static String Consultar_PK(String Tabla, String NombreClave) throws SQLException{
      
        ConexionOracle Conexion= new ConexionOracle();
        Connection Con=Conexion.Conectar();
        Statement st= Con.createStatement();
        String Clave="1";
        int conta = 0;
         ResultSet Valores= st.executeQuery("SELECT MAX("+NombreClave+") FROM "+Tabla);
            while (Valores.next()){
                    Clave=Valores.getString(1);
                    conta++;
                }
         // JOptionPane.showMessageDialog(null,"el conta DEVUELTA ES:"+Integer.toString(conta));
          if (conta >=1)
            return Clave;
          else
            return ("0");
         
      }
    
    public static String Consultar_PKPorNombre(String Tabla, String NombreClave, String ABuscar , String AComparar) throws SQLException{
      
        ConexionOracle Conexion= new ConexionOracle();
        Connection Con=Conexion.Conectar();
        Statement st= Con.createStatement();
         ResultSet Valores= st.executeQuery("SELECT ("+NombreClave+") FROM "+Tabla+" WHERE ( '"+ABuscar+"' = "+AComparar+" ) ");
            while (Valores.next()){
                    JOptionPane.showMessageDialog(null,"AQUIII");
                    String Clave=Valores.getString(1);
                    return (Clave);
                }
        return ("false");
     }
    
    
}
