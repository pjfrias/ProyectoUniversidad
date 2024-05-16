package Connection;

import java.sql.*;
import javax.swing.JOptionPane;

public class Conexion {
   
   private static final String DRIVER = "org.mariadb.jdbc.Driver";
   private static final String URL = "jdbc:mysql://localhost/universidadulp";
   private static final String USUARIO = "root";
   private static final String PASSWORD = "";
   
   private static Connection connection;
   
   private Conexion(){}
   
   public static Connection getConexion(){
       
       if(connection == null){
       
           try{
               Class.forName(DRIVER);
               connection = DriverManager.getConnection(URL,USUARIO,PASSWORD);
            }catch(ClassNotFoundException ex){
                JOptionPane.showMessageDialog(null,"Error al cargar el driver de conexion");
            }catch(SQLException e){
               switch(e.getErrorCode()){
                   case 1049:
                       JOptionPane.showMessageDialog(null,"Base de datos desconocida");
                       break;
                   default:
                       JOptionPane.showMessageDialog(null,"Error de conexion");
                       break;
                }
           }
       }
       
       return connection;
   }
}
