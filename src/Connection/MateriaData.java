
package Connection;

import Entidades.Materia;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class MateriaData {
    
    private Connection con;

    public MateriaData() {
        this.con = Conexion.getConexion();
    }
 
    public void guardarMateria(Materia materia){
        int registros;
        String sql = "INSERT INTO materia(nombre, año, estado) "
                + "VALUES (?, ?, ?)";
         
        try {
            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, materia.getNombre());
            ps.setInt(2, materia.getAnioMateria());
            ps.setBoolean(3, materia.isActivo()); 
            
            registros = ps.executeUpdate(); //GUARDAMOS LA CANTIDAD DE FILAS AFECTADAS
            ResultSet rs = ps.getGeneratedKeys(); //GUARDAMOS EL CONJUNTO DE CLAVES AUTOGENERADAS
            
            if (registros == 1) { // ES UNA FORMA DE CONTAR CUANTAS FILAS SE AGREGARON
                rs.next();
                JOptionPane.showMessageDialog(null, "Se ha creado la materia "+materia.getNombre()+" id = "+rs.getInt("idMateria"));
            }
            ps.close();
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al acceder a la tabla Materia"+ ex.getMessage());
            ex.printStackTrace();  
        }
    }
    
    public Materia buscarMateria(int id){
        String sql = "SELECT * FROM `materia` WHERE idMateria = ? and estado = 1";
        PreparedStatement ps;        
        ResultSet resultados;
        Materia materiaEncontrada = null;
           
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            resultados = ps.executeQuery();
            
            if(resultados.next()){
                materiaEncontrada = new Materia(resultados.getInt("idMateria"), resultados.getString("nombre"), resultados.getInt("año"), resultados.getBoolean("estado"));
            }
            else JOptionPane.showMessageDialog(null, "No se encuentra la materia");
            ps.close();
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "No se pudo acceder a la tabla MATERIA");
        }
        return materiaEncontrada;        
    }

    
    public void modificarMateria (Materia materia){
        String sql = "UPDATE materia SET nombre= ?,año = ? WHERE idMateria = ? ";
        PreparedStatement ps = null;
        
        try{
            ps = con.prepareStatement(sql);
            ps.setString(1, materia.getNombre());
            ps.setInt(2, materia.getAnioMateria());
            ps.setInt(3, materia.getIdMateria());  
            int exito = ps.executeUpdate();
            if(exito == 1){ 
                JOptionPane.showMessageDialog(null, "Modificado Exitosamente."); 
            }else{ 
                JOptionPane.showMessageDialog(null, "La materia no existe"); 
            } 
        }catch (SQLException ex) {
            Logger.getLogger(null, "Error al acceder a la tabla Materia "+ ex.getMessage());
        }
                
    }
    
    public void eliminarMateria(int id){
        try{
            String sql = "UPDATE materia SET estado= 0 WHERE idMateria = ? ";                
            PreparedStatement ps = con.prepareStatement(sql);    
            ps.setInt(1, id); 
            int fila = ps.executeUpdate(); 

            if(fila ==1 ){ 
                JOptionPane.showMessageDialog(null, " Se eliminó la materia."); 
            } 
            ps.close(); 
        }catch(SQLException ex) {
            Logger.getLogger(null, " Error al acceder a la tabla Materia");
        }                  
    
    }
    
    public List<Materia> listarMaterias(){
        List<Materia> materias = new ArrayList<>();
        try{
            String sql = "SELECT * FROM materia WHERE estado = 1";
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
    }
}
