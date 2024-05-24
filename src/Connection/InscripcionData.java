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
            String sql = "select a.idAlumno id_alumno, a.fechaNacimiento fecha, a.nombre nombre_alumno , a.apellido ,a.dni, "
                    + "m.nombre nom_materia, m.idMateria id_materia, m.anio, i.nota "
                    + "from inscripcion i "
                    + "join alumno a on a.idAlumno = i.idAlumno "
                    + "join materia m on m.idMateria = i.idMateria "
                    + "where a.estado = 1 and m.estado = 1";
            PreparedStatement ps = con.prepareStatement(sql);  
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                Inscripcion inscripcion = new Inscripcion();
                Alumno alumno=new Alumno();
                Materia materia=new Materia();
                alumno.setIdAlumno(rs.getInt("id_alumno"));
                alumno.setFechaNac(rs.getDate("fecha").toLocalDate());
                alumno.setNombre(rs.getString("nombre_alumno"));
                alumno.setApellido(rs.getString("apellido"));
                alumno.setDni(rs.getInt("dni"));
                materia.setNombre(rs.getString("nom_materia"));
                materia.setIdMateria(rs.getInt("id_materia"));
                materia.setAnioMateria(rs.getInt("anio"));
                inscripcion.setNota(rs.getDouble("nota"));
            inscripcion.setAlumno(alumno);
            inscripcion.setMateria(materia);
            
            inscripciones.add(inscripcion); 
            }
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Error al acceder a la tabla inscripciones "+ ex.getMessage());
        }    
        return inscripciones;
        
    }
    
    public List<Inscripcion> obtenerInscripcionesPorAlumno(int id){
        
        List<Inscripcion> inscripciones = new ArrayList<>();
        try{
            String sql = "select a.idAlumno id_alumno, a.fechaNacimiento fecha, a.nombre nombre_alumno , a.apellido ,a.dni, m.nombre nom_materia, m.idMateria id_materia, m.anio, i.nota "
                    + "from inscripcion i "
                    + "join alumno a on a.idAlumno = i.idAlumno "
                    + "join materia m on m.idMateria = i.idMateria "
                    + "where a.estado = 1 and m.estado = 1 "
                    + "and a.idAlumno = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                Inscripcion inscripcion = new Inscripcion();
                Alumno alumno=new Alumno();
                Materia materia=new Materia();
                alumno.setIdAlumno(rs.getInt("id_alumno"));
                alumno.setFechaNac(rs.getDate("fecha").toLocalDate());
                alumno.setNombre(rs.getString("nombre_alumno"));
                alumno.setApellido(rs.getString("apellido"));
                alumno.setDni(rs.getInt("dni"));
                materia.setNombre(rs.getString("nom_materia"));
                materia.setIdMateria(rs.getInt("id_materia"));
                materia.setAnioMateria(rs.getInt("anio"));
                inscripcion.setNota(rs.getDouble("nota"));
            inscripcion.setAlumno(alumno);
            inscripcion.setMateria(materia);
            
            inscripciones.add(inscripcion); 
            }
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Error al acceder a la tabla inscripciones "+ ex.getMessage());
        }    
        return inscripciones;
    }
    
    public List<Materia> obtenerMateriasCursadas(int id){
        List<Materia> materias = new ArrayList<>();
        try{
            String sql = "select m.nombre, m.id, m.anio, i.nota "
                    + "from inscripcion i "
                    + "join alumno a on a.idAlumno = i.idAlumno "
                    + "join materia m on m.idMateria = i.idMateria "
                    + "where a.estado = 1 and m.estado = 1 "
                    + "and a.idAlumno = ? "
                    + "and i.nota != -1";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                Materia materia=new Materia();
                materia.setNombre(rs.getString("nombre"));
                materia.setIdMateria(rs.getInt("id"));
                materia.setAnioMateria(rs.getInt("anio"));
                materias.add(materia); 
            }
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Error al acceder a la tabla inscripciones "+ ex.getMessage());
        }    
        return materias;
    }
    
    public List<Materia> obtenerMateriasNoCursadas(int id){
        List<Materia> materias = new ArrayList<>();
        try{
            String sql = "select m.nombre, m.id, m.anio, i.nota "
                    + "from inscripcion i "
                    + "join alumno a on a.idAlumno = i.idAlumno "
                    + "join materia m on m.idMateria = i.idMateria "
                    + "where a.estado = 1 and m.estado = 1 "
                    + "and a.idAlumno = ? "
                    + "and i.nota = -1";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                Materia materia=new Materia();
                materia.setNombre(rs.getString("nombre"));
                materia.setIdMateria(rs.getInt("id"));
                materia.setAnioMateria(rs.getInt("anio"));
                materias.add(materia); 
            }
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Error al acceder a la tabla Inscripcion "+ ex.getMessage());
        }    
        return materias;
    }
    
    public void borrarInscripcionMateriaAlumno(int idAlumno, int idMateria){
        try{
            String sql = "delete inscripcion where idAlumno = ? anda idMateria = ? ";                
            PreparedStatement ps = con.prepareStatement(sql);    
            ps.setInt(1, idAlumno);
            ps.setInt(2, idMateria);
            int fila = ps.executeUpdate(); 

            if(fila != 0 ){ 
                JOptionPane.showMessageDialog(null, "Se eliminó la inscripcion."); 
            } 
            ps.close(); 
        }catch(SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al acceder a la tabla Inscripcion");
        }
    }
    
    public void actualizarNota(int idAlumno, int idMateria, double nota){
        try{
            String sql = "update inscripcion set nota = ? where idAlumno = ? anda idMateria = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setDouble(1, nota);
            ps.setInt(2, idAlumno);
            ps.setInt(3, idMateria);
            ps.close();
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Error al acceder a la tabla Inscricpion");
        }
    }
    
    public List<Alumno> obtenerAlumnosXMateria(int idMateria){
        List<Alumno> alumnos = new ArrayList<>();
        try{
            String sql = "select a.idAlumno, a.nombre, a.apellido, a.dni, a.fechaNacim"
                    + "from inscripcion i "
                    + "join alumno a on a.idAlumno = i.idAlumno "
                    + "where a.estado = 1 and m.estado = 1 "
                    + "and a.idMateria = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, idMateria);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                Alumno alumno = new Alumno();
                alumno.setIdAlumno(rs.getInt("idAlumno"));
                alumno.setDni(rs.getInt("dni"));
                alumno.setNombre(rs.getString("nombre"));
                alumno.setApellido(rs.getString("apellido"));
                alumno.setFechaNac(rs.getDate("fechaNacim").toLocalDate());
                alumnos.add(alumno);
            }
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Error al acceder a la tabla inscripciones "+ ex.getMessage());
        }    
        return alumnos;
    }
}
