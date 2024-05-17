
package Connection;

import Entidades.Materia;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class MateriaData {
    
    private Connection con;

    public MateriaData(Connection con) {
        this.con = con;
    }
 
    public void guardarMateria(Materia materia)  throws SQLException{
        String sql = "INSERT INTO materia(nombre, año, estado) "
                + "VALUES (?, ?, ?, ?)";
        PreparedStatement ps;
        int registros;
    
        ps = con.prepareStatement(sql);
        ps.setString(1, materia.getNombre());
        ps.setInt(2, materia.getAnioMateria());
        ps.setBoolean(3, true);          
    
        registros = ps.executeUpdate();
        System.out.println(registros);
        
    }
    
    public Materia buscarMateria(int id)throws SQLException{
        String sql = "SELECT * FROM `materia` WHERE idMateria = ?";
        PreparedStatement ps;        
        ResultSet resultados;
        Materia objeto = null;
           
        ps = con.prepareStatement(sql);
        ps.setInt(1, id);
        resultados = ps.executeQuery();
        while(resultados.next()){
            objeto =new Materia(resultados.getString(2), resultados.getInt(3), resultados.getBoolean(4));
        }       
        return objeto;        
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
            String sql = "UPDATE materia SET estado= ? WHERE idMateria = ? ";                
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
    
    public List<Materia> listarMaterias() throws SQLException{
        String sql = "SELECT * FROM `materia` WHERE 1";
        PreparedStatement ps;        
        ResultSet resultados;
        List<Materia> lista = new ArrayList<>();
           
        ps = con.prepareStatement(sql);        
        resultados = ps.executeQuery();
        while(resultados.next()){
            lista.add(new Materia(resultados.getInt(1),resultados.getString(2), resultados.getInt(3), resultados.getBoolean(4)));
        }       
        return lista;        
    }
}
