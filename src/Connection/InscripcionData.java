package Connection;

import Entidades.Alumno;
import Entidades.Inscripcion;
import Entidades.Materia;
import java.sql.Connection;
import java.util.List;

public class InscripcionData {
    private Connection con;
    private MateriaData matData;
    private AlumnoData aluData;

    public InscripcionData(MateriaData matData, AlumnoData aluData){
        this.matData = matData;
        this.aluData = aluData;
        con = Conexion.getConexion();
    }
    
    public void guardarInscripcion(Inscripcion insc){
        
    }
    
    public List<Inscripcion> obtenerInscripciones(){
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
