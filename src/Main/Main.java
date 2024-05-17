package main;

import Connection.AlumnoData;
import Connection.MateriaData;
import Entidades.Materia;

public class Main {
    
    public static void main(String[] args) {
        
        AlumnoData alumno = new AlumnoData();
        
        //alumno.guardarAlumno(new Alumno(1,23454321,"Lopez","Gerardo",LocalDate.of(2002, Month.MARCH, 23),true));
        
        //System.out.println(alumno.bucarAlumno(40));
        
        //System.out.println(alumno.buscarAlumnoPorDni(55555555));
        
        //System.out.println(alumno.listarAlumnos());
        
        //Alumno alumnoComun = alumno.bucarAlumno(40);
        
        //alumnoComun.setApellido("Velazquez");
        
        //alumno.modificarAlumno(alumnoComun);
        
        //alumno.eliminarAlumno(40);
        
        MateriaData materia = new MateriaData();
        
        //materia.guardarMateria(new Materia("Lengua", 2, true));
        
        //System.out.println(materia.buscarMateria(3));
        
        //Materia materiaComun = new Materia("Filosofia", 1, true);
        //materia.eliminarMateria(8);
        System.out.println(materia.listarMaterias());
    }

}
