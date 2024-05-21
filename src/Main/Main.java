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
        System.out.println(" | 5 - Listar alumnos                         |");
        System.out.println(" | 6 - Borrar alumno                          |");
        System.out.println(" |                                            |");
        System.out.println(" |    ------------ MATERIAS -------------     |");
        System.out.println(" | 7 - Agregar materia                        |");
        System.out.println(" | 8 - Buscar materia por id                  |");
        System.out.println(" | 9 - Actualizar materia                     |");
        System.out.println(" | 10 - Listar materias por                   |");
        System.out.println(" | 11 - Borrar materia                        |");
        System.out.println(" | 12 - SALIR                                 |");
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
                        System.out.println("\n======= Listado de alumnos =======\n");
                        for (Alumno alum : alumno.listarAlumnos()) {
                            System.out.println(alum.getApellido()+", "+alum.getNombre()+" DNI: "+alum.getDni());
                        }
                        System.out.println("");

                    break;

                case 6:
                    
                    if(alumnoComun != null){
                        System.out.print("Desea borrar al alumno actual (s/n)"+alumnoComun.getApellido()+", "+alumnoComun.getNombre()+" -->");
                        
                        if(scan.next().startsWith("s"))
                            alumno.eliminarAlumno(alumnoComun.getIdAlumno());
                        else
                            System.out.println("Debe seleccionar un alumno mediante la busqueda del menu");
                    }else
                        System.out.println("Debe seleccionar un alumno mediante la busqueda del menu");
                    break;

                case 7:
                        System.out.print("Ingrese el nombre de la materia -->");
                        String nombreMateria = scan.next();
                        System.out.print("Ingrese el anio de cursado de la materia -->");
                        int anioCursado = scan.nextInt();
                        materiaComun = new Materia(nombreMateria, anioCursado, true);
                        materia.guardarMateria(materiaComun);
                    break;

                case 8:
                        System.out.print("Ingrese el id de la materia a buscar -->");
                        id = scan.nextInt();
                        materiaComun = materia.buscarMateria(id);

                        if(materiaComun != null){
                            System.out.println(materiaComun);
                        }
                    break;
                    
                case 9:
                        if(materiaComun != null){
                        System.out.print("Desea actualizar la materia actual (s/n) "+materiaComun.getNombre()+", "+materiaComun.getAnioMateria()+"--> ");
                        
                        if(scan.next().startsWith("s")){
                            System.out.print("Ingrese el nombre de la materia -->");
                            materiaComun.setNombre(scan.next());
                            System.out.print("Ingrese el ano de cursada -->");
                            materiaComun.setAnioMateria(scan.nextInt());
                            materia.modificarMateria(materiaComun);
                        }
                        else
                            System.out.println("Debe seleccionar una materia mediante la busqueda del menu");
                    }else
                        System.out.println("Debe seleccionar una materia mediante la busqueda del menu");
                    break;
                        
                case 10:
                        System.out.println("\n======= Listado de materias =======\n");
                        for (Materia mater : materia.listarMaterias()) {
                            System.out.println(mater.getNombre()+", "+mater.getAnioMateria()+" ANIO");
                        }
                        System.out.println("");
                        break;

                case 11:
                        if(materiaComun != null){
                        System.out.print("Desea borrar la materia actual (s/n) "+materiaComun.getNombre()+", "+materiaComun.getAnioMateria()+" -->");
                        
                        if(scan.next().startsWith("s"))
                            materia.eliminarMateria(materiaComun.getIdMateria());
                        else
                            System.out.println("Debe seleccionar una materia mediante la busqueda del menu");
                    }else
                        System.out.println("Debe seleccionar una materia mediante la busqueda del menu");
                    break;
            }
        }while(opc != 12);
        
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
