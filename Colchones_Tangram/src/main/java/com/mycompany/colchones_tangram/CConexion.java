package com.mycompany.colchones_tangram;

import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.JOptionPane;


public class CConexion {
    
Connection conectar = null;

String usuario ="root";
String contrasenia ="12345678";
String bd="bdColchonesTangram";
String ip="localhost";
String puerto="3306";

String cadena = "jdbc:mysql://"+ip+":"+puerto+"/"+bd;
public Connection estableceConexion () {
try {
Class.forName("com.mysql.jdbc.Driver");
conectar=DriverManager.getConnection(cadena, usuario, contrasenia);
JOptionPane. showMessageDialog (null, "Se conecto correctamente a la base de datos ");

} catch (Exception e) {
JOptionPane. showMessageDialog(null, "No se conecto a la base de datos, error: "+ e.toString());

}
return conectar;
}
    
}
