package main;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class Main {

	// DECLARACION DE FUNCIONES
    
    public static void agregarAlumnoBD(Connection conexion, int dni, String apellido, String nombre, String fechaNacimiento, boolean estado) throws SQLException{
        String sql = "INSERT INTO alumno(dni, apellido, nombre, fechaNacimiento, estado) "
                + "VALUES (?, ?, ?, ?, ?)";
        PreparedStatement ps;
        int registros = 0;
    
        ps = conexion.prepareStatement(sql);
        ps.setInt(1, dni);
        ps.setString(2, apellido);
        ps.setString(3, nombre);
        ps.setDate(4, Date.valueOf(fechaNacimiento));
        ps.setBoolean(5, estado);
    
        registros = ps.executeUpdate();
        System.out.println(registros);
    }
    
    public static void agregarMateriasBD(Connection conexion, int idMateria, String nombre, int anio, boolean estado) throws SQLException{
        String sql = "INSERT INTO materia(idMateria, nombre, aÃ±o, estado) "
                    + "VALUES (?, ?, ?, ?)";
        PreparedStatement ps;
        int registros = 0;
        
        
        ps = conexion.prepareStatement(sql);
        
        ps.setInt(1, idMateria);
        ps.setString(2, nombre);
        ps.setInt(3, anio);
        ps.setBoolean(4, estado);
        
        registros = ps.executeUpdate();
        System.out.println(registros);
    }
    
    public static void inscripcionMateriasBD(Connection conexion, int nota, String dni, int mate) throws SQLException{
        String sql = "INSERT INTO inscripcion(nota, idALumno, idMateria) "
                    + "VALUES (?, (SELECT idAlumno FROM alumno WHERE dni = ?), ?)";
        PreparedStatement ps;
        int registros = 0;
        
        
        ps = conexion.prepareStatement(sql);
        
        ps.setInt(1, nota);
        ps.setString(2, dni);
        ps.setInt(3, mate);        
        
        registros = ps.executeUpdate();
        System.out.println(registros);
    }

	//METODO MAIN
    
    public static void main(String[] args) {
        
        String sql = "";
        PreparedStatement ps;
        int registros = 0;
        
        try {
            //Cargo los drivers
            Class.forName("org.mariadb.jdbc.Driver"); 
            //Conexion a base de datos
            Connection conexion = DriverManager.getConnection("jdbc:mariadb://localhost:3306/universidadulp", "root", "");
            
            //Agregar alumnos
            agregarAlumnoBD(conexion, 12345678, "Corales", "Maria", "2000-03-15", true);
            agregarAlumnoBD(conexion, 87654321, "MartÃ­nez", "Juan", "1999-07-20", true);
            agregarAlumnoBD(conexion, 55555555, "LÃ³pez", "Pedro", "2001-05-10", true);
            
            //Agregar materias
            agregarMateriasBD(conexion, 1, "Matematicas", 1, true);
            agregarMateriasBD(conexion, 2, "Historia", 1, true);
            agregarMateriasBD(conexion, 3, "Ciencias", 2, true);
            agregarMateriasBD(conexion, 4, "Literatura", 2, true);
            
            //Inscribir los tres alumnos en 2 materias cada uno
            //Alumno 1 
            sql = "INSERT INTO inscripcion(nota, idALumno, idMateria) "
                    + "VALUES (7, (SELECT idAlumno FROM alumno WHERE dni = 12345678), 1)";
            ps = conexion.prepareStatement(sql);
            registros = ps.executeUpdate();
            System.out.println(registros);
            
            sql = "INSERT INTO inscripcion(nota, idALumno, idMateria) "
                    + "VALUES (6, (SELECT idAlumno FROM alumno WHERE dni = 12345678), 3)";
            ps = conexion.prepareStatement(sql);
            registros = ps.executeUpdate();
            System.out.println(registros);
            
            //Alumno 2 
            sql = "INSERT INTO inscripcion(nota, idALumno, idMateria) "
                    + "VALUES (9, (SELECT idAlumno FROM alumno WHERE dni = 87654321), 2)";
            ps = conexion.prepareStatement(sql);
            registros = ps.executeUpdate();
            System.out.println(registros);
            
            sql = "INSERT INTO inscripcion(nota, idALumno, idMateria) "
                    + "VALUES (6, (SELECT idAlumno FROM alumno WHERE dni = 87654321), 3)";
            ps = conexion.prepareStatement(sql);
            registros = ps.executeUpdate();
            System.out.println(registros);
            
            //Alumno 3 
            sql = "INSERT INTO inscripcion(nota, idALumno, idMateria) "
                    + "VALUES (8, (SELECT idAlumno FROM alumno WHERE dni = 55555555), 2)";
            ps = conexion.prepareStatement(sql);
            registros = ps.executeUpdate();
            System.out.println(registros);
            
            sql = "INSERT INTO inscripcion(nota, idALumno, idMateria) "
                    + "VALUES (2, (SELECT idAlumno FROM alumno WHERE dni = 55555555), 1)";
            ps = conexion.prepareStatement(sql);
            registros = ps.executeUpdate();
            System.out.println(registros);
            
            inscripcionMateriasBD(conexion,8,"55555555",4);
            
            //Listar los datos de los alumnos con calificaciones superiores a 8 
            sql = "SELECT * FROM alumno WHERE idAlumno IN (SELECT idAlumno FROM inscripcion WHERE nota >= 8)";
            ps = conexion.prepareStatement(sql);
            ResultSet resultado = ps.executeQuery();
            while(resultado.next()){
                System.out.println("----------------------");
                //System.out.println("ID: " + resultado.getInt("idAlumno"));
                System.out.println("DNI: " + resultado.getInt("dni"));
                System.out.println("Apellido: " + resultado.getString("apellido"));
                System.out.println("Nombre: " + resultado.getString("nombre"));
                System.out.println("Fecha de nacimiento: " + resultado.getString("fechaNacimiento"));
                //System.out.println("Estado: " + resultado.getBoolean("estado"));
                System.out.println("----------------------");
            }
            
            //Desinscribir un alumno de una de la materias.
            sql = "DELETE FROM inscripcion WHERE idAlumno = "
                    + "(SELECT idAlumno FROM alumno WHERE dni = 55555555) "
                    + "AND idMateria = 1";
            ps = conexion.prepareStatement(sql);
            registros = ps.executeUpdate();
            System.out.println(registros);
            
            
        } catch (ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "Error al cargar driver " + ex.getMessage());
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error de conexion " + ex.getMessage());        }
    }
}
