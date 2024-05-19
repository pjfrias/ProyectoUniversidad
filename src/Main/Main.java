package main;

import Connection.AlumnoData;
import Connection.MateriaData;
import Entidades.Materia;
import Entidades.Alumno;
import java.time.LocalDate;
import java.time.Month;
import java.util.Scanner;

public class Main {
    
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        AlumnoData alumno = new AlumnoData();
        Alumno alumnoComun = null;
        MateriaData materia = new MateriaData();
        Materia materiaComun = null;
        
        System.out.println(" ==== BIENVENIDO AL SISTEMA DE UNIVERSIDAD ====");
        System.out.println(" |                                            |");
        System.out.println(" |    ------------- ALUMNOS -------------     |");
        System.out.println(" | 1 - Agregar alumno                         |");
        System.out.println(" | 2 - Buscar alumno por id                   |");
        System.out.println(" | 3 - Buscar alumno por DNI                  |");
        System.out.println(" | 4 - Actualizar datos alumno                |");
        System.out.println(" | 5 - Borrar alumno                          |");
        System.out.println(" |    ------------ MATERIAS -------------     |");
        System.out.println(" | 6 - Agregar materia                        |");
        System.out.println(" | 7 - Buscar materia por id                  |");
        System.out.println(" | 8 - Borrar materia                         |");
        System.out.println(" | 9 - SALIR                                  |");
        System.out.println(" |============================================|");
        
        
        int opc;
        
        do{
            System.out.print("INGRESE SU OPCION DE MENU -> ");
            opc = scan.nextInt();
          switch(opc){
                case 1:
                    
                    System.out.print("Ingrese el DNI -->");
                    int dni = scan.nextInt();
                    scan.nextLine();
                    System.out.print("Ingrese el nombre -->");
                    String nombre = scan.next();
                    System.out.print("Ingrese el apellido -->");
                    String apellido = scan.next();
                    System.out.print("Ingrese el anio de nacimiento (aaaa) -->");
                    int anio = scan.nextInt();
                    System.out.print("Ingrese el mes de nacimiento (mm) -->");
                    int mes = scan.nextInt();
                    System.out.print("Ingrese el dia de nacimiento (dd) -->");
                    int dia = scan.nextInt();
                    LocalDate fecha_nacim = LocalDate.of(anio, Month.of(mes), dia);
                    alumnoComun = new Alumno(dni,nombre,apellido,fecha_nacim,true);
                    alumno.guardarAlumno(alumnoComun);

                    break;

                case 2:
                    
                    System.out.print("Ingrese el id del alumno a buscar -->");
                    int id = scan.nextInt();
                    alumnoComun = alumno.bucarAlumno(id);
                    
                    if(alumnoComun != null){
                        System.out.println(alumnoComun);
                    }
                    break;

                case 3:
                    
                    System.out.print("Ingrese el DNI del alumno a buscar -->");
                    dni = scan.nextInt();
                    alumnoComun = alumno.buscarAlumnoPorDni(dni);
                    
                    if(alumnoComun != null){
                        System.out.println(alumnoComun);
                    }

                    break;

                case 4:
                    
                    if(alumnoComun != null){
                        System.out.print("Desea actualizar al alumno actual (s/n)--> "+alumnoComun.getApellido()+", "+alumnoComun.getNombre() );
                        
                        if(scan.next().startsWith("s")){
                            System.out.print("Ingrese el DNI -->");
                            alumnoComun.setDni(scan.nextInt());
                            scan.nextLine();
                            System.out.print("Ingrese el nombre -->");
                            alumnoComun.setNombre(scan.next());
                            System.out.print("Ingrese el apellido -->");
                            alumnoComun.setApellido(scan.next());
                            System.out.print("Ingrese el anio de nacimiento (aaaa) -->");
                            anio = scan.nextInt();
                            System.out.print("Ingrese el mes de nacimiento (mm) -->");
                            mes = scan.nextInt();
                            System.out.print("Ingrese el dia de nacimiento (dd) -->");
                            dia = scan.nextInt();
                            alumnoComun.setFechaNac(LocalDate.of(anio, Month.of(mes), dia));
                            alumno.modificarAlumno(alumnoComun);
                        }
                        else
                            System.out.println("Debe seleccionar un alumno mediante la busqueda del menu");
                    }else
                        System.out.println("Debe seleccionar un alumno mediante la busqueda del menu");
                    break;
                    

                case 5:
                    
                    if(alumnoComun != null){
                        System.out.print("Desea borrar al alumno actual (s/n)--> "+alumnoComun.getApellido()+", "+alumnoComun.getNombre() );
                        
                        if(scan.next().startsWith("s"))
                            alumno.eliminarAlumno(alumnoComun.getIdAlumno());
                        else
                            System.out.println("Debe seleccionar un alumno mediante la busqueda del menu");
                    }else
                        System.out.println("Debe seleccionar un alumno mediante la busqueda del menu");
                    break;

                case 6:
                        System.out.println("A IMPLEMENTAR");
                    break;

                case 7:
                        System.out.println("A IMPLEMENTAR");
                    break;

                case 8:
                        System.out.println("A IMPLEMENTAR");
                    break;
            }
        }while(opc != 9);
        
        //alumno.guardarAlumno(new Alumno(1,23454321,"Lopez","Gerardo",LocalDate.of(2002, Month.MARCH, 23),true));
        
        //System.out.println(alumno.bucarAlumno(40));
        
        //System.out.println(alumno.buscarAlumnoPorDni(55555555));
        
        //System.out.println(alumno.listarAlumnos());
        
        //Alumno alumnoComun = alumno.bucarAlumno(40);
        
        //alumnoComun.setApellido("Velazquez");
        
        //alumno.modificarAlumno(alumnoComun);
        
        //alumno.eliminarAlumno(40);
        
        //MateriaData materia = new MateriaData();
        
        //materia.guardarMateria(new Materia("Lengua", 2, true));
        
        //System.out.println(materia.buscarMateria(3));
        
        //Materia materiaComun = new Materia("Filosofia", 1, true);
        //materia.eliminarMateria(8);
        //System.out.println(materia.listarMaterias());
    }

}
