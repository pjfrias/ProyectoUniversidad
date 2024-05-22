package Connection;

import Entidades.Alumno;
import Entidades.Inscripcion;
import Entidades.Materia;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class InscripcionData {
    private Connection con;
  

    public InscripcionData(){
        
        con = Conexion.getConexion();
    }
    
    public void guardarInscripcion(Inscripcion inscripcion){
       String sql = "INSERT INTO incripcion(nota, idAlumno, idMateria) "
                + "VALUES (?, ?, ?)";
       try {
            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setDouble(1, inscripcion.getNota());
            ps.setInt(2, inscripcion.getAlumno().getIdAlumno());
            ps.setInt(3, inscripcion.getMateria().getIdMateria()); 
            
           int registros = ps.executeUpdate(); //GUARDAMOS LA CANTIDAD DE FILAS AFECTADAS
            ResultSet rs = ps.getGeneratedKeys(); //GUARDAMOS EL CONJUNTO DE CLAVES AUTOGENERADAS
            
            if (registros == 1) { // ES UNA FORMA DE CONTAR CUANTAS FILAS SE AGREGARON
                rs.next();
                JOptionPane.showMessageDialog(null, "Se ha hecho la inscripcion "+inscripcion.getAlumno()+" con nombre = "+inscripcion.getMateria().getNombre());
            }
            ps.close();
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al acceder a la tabla Inscripciones"+ ex.getMessage());
            ex.printStackTrace();  
        }
    }
    
    public List<Inscripcion> obtenerInscripciones(){
        List<Inscripcion> inscripciones = new ArrayList<>();
        try{
            String sql = "SELECT * FROM inscripciones WHERE estado = 1";
            PreparedStatement ps = con.prepareStatement(sql);  
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                Materia materia = new Materia();
                materia.setIdMateria(rs.getInt("idMateria"));
                materia.setNombre(rs.getString("nombre"));
                materia.setAnioMateria(rs.getInt("año"));
                materia.setActivo(rs.getBoolean("estado"));
                materias.add(materia);
            }
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, " Error al acceder a la tabla Materias "+ ex.getMessage());
        }    
        return materias;
        return null;
    }
    
    public List<Inscripcion> obtenerInscripcionesPorAlumno(int id){
        return null;
    }
    
    public List<Materia> obtenerMateriasCursadas(int id){
        return null;
    }
    
    public List<Materia> obtenerMateriasNoCursadas(int id){
        return null;
    }
    
    public void borrarInscripcionMateriaAlumno(int idAlumno, int idMateria){
        
    }
    
    public void actualizarNota(int idAlumno, int idMateria, double nota){
    
    }
    
    public List<Alumno> obtenerAlumnosXMateria(int idMateria){
        return null;
    }
}
