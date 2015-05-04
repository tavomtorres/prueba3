/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import static Controlador.VentanaMenuRolesControlador.Consultar_PK_ROL;
import Interfaz.VentanaMenuRoles;
import Modelo.ConexionOracle;
import Modelo.Util;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author Jesus Trejo
 */
public class VentanaMenuEdificioControlador {

public static ArrayList<String> Estados= new ArrayList();
public static ArrayList<String> Municipios = new ArrayList();

public static void LlenarEstado() throws SQLException{
    llenarListaEstados();
     
}

public static void llenarListaEstados() throws SQLException{
        
    ConexionOracle Conexion= new ConexionOracle();
    Connection Con=Conexion.Conectar();
    Statement st= Con.createStatement();
    ResultSet Valores= st.executeQuery("SELECT (LUG_NOMBRE) FROM LUGAR WHERE (LUG_FK_LUG = 0) and (LUG_NOMBRE != 'Venezuela')");
       while (Valores.next()){
            //JOptionPane.showMessageDialog(null,"AQUIII");
            Estados.add(Valores.getString(1));
       }
}

public static void llenarListaMunicipios(String FK_Lugar) throws SQLException{
    Municipios.clear();
    ConexionOracle Conexion= new ConexionOracle();
    Connection Con=Conexion.Conectar();
    Statement st= Con.createStatement();
    ResultSet Valores= st.executeQuery("SELECT (LUG_NOMBRE) FROM LUGAR WHERE (LUG_FK_LUG ="+FK_Lugar+" )");
       while (Valores.next()){
            //JOptionPane.showMessageDialog(null,"AQUIII");
            Municipios.add(Valores.getString(1));
            
       }
}

public static void InsertarEdificioSQL(String Nombre, String FK_Lugar) throws SQLException{

        ConexionOracle Conexion= new ConexionOracle();
        Connection Con=Conexion.Conectar();
        String Clave= Util.Consultar_PK("EDIFICIO","EDI_CLAVE");
       
        if (Clave.equalsIgnoreCase("null")){
            JOptionPane.showMessageDialog(null,"LOGRE BURLAR EL NULL");
            Clave="0";
        }
        JOptionPane.showMessageDialog(null,"ENCONTRO LA CLAVE DEL EDIF ->"+Clave);
        PreparedStatement pst=  Con.prepareStatement("INSERT INTO EDIFICIO (EDI_CLAVE,EDI_NOMBRE,EDI_FK_LUGAR) VALUES (?,?,?)");
        pst.setInt(1,Integer.parseInt(Clave)+1);
        pst.setString(2,Nombre);
        pst.setInt(3,Integer.parseInt(FK_Lugar));
        pst.executeUpdate();    




}

}

