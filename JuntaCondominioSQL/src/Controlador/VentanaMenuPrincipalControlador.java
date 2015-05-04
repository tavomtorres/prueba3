/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.ConexionOracle;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author Jesus Trejo
 */
public class VentanaMenuPrincipalControlador {

    public static ArrayList<String> permisos = new ArrayList();

    public static void EncuentraPermisosSQL(String PK) throws SQLException{
        permisos.clear();
        ConexionOracle Conexion= new ConexionOracle();
        Connection Con=Conexion.Conectar();
        Statement st= Con.createStatement();
        String Permiso = "";
        ResultSet Valores= st.executeQuery("SELECT ROL_PER_FK_PER " +
                                    "       FROM ROL_PER  , ROL , USUARIO " +
                                    "       WHERE (ROL_CLAVE = USU_FK_ROL) AND (ROL_CLAVE = ROL_PER_FK_ROL) and (USU_ID = '"+PK+"')");
            while (Valores.next()){
               Permiso=Valores.getString(1);
               permisos.add(Permiso);
            }
    }
}
