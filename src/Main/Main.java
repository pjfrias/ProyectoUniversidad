package main;

import Connection.MateriaData;
import Connection.AlumnoData;
import Connection.InscripcionData;
import Entidades.Materia;
import Entidades.Alumno;
import Entidades.Inscripcion;
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
        InscripcionData inscripcion = new InscripcionData();
        Inscripcion inscripcionComun = null;
        
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
        System.out.println(" | 10 - Listar materias                       |");
        System.out.println(" | 11 - Borrar materia                        |"); 
        System.out.println(" |    ------------ INSCRIPCIONES -------------|");
        System.out.println(" | 12 - Agregar inscripcion                   |");
        System.out.println(" | 13 - Listar Inscripciones                  |");
        System.out.println(" | 14 - Buscar Inscripciones por Alumno       |");
        System.out.println(" | 15 - Materias cursadas por Alumno          |");
        System.out.println(" | 16 - Materias no cursadas por Alumno       |");
        System.out.println(" | 17 - Borrar inscripcion                    |");
        System.out.println(" | 18 - Actualizar nota                       |");
        System.out.println(" | 19 - Listar alumnos por materia            |");
        System.out.println(" |============================================|");
        System.out.println(" | 20 - SALIR                                 |");
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
                    verAlumnoMateriaSeleccionados(alumnoComun,materiaComun);
                    if(alumnoComun != null){
                        System.out.print("Desea actualizar al alumno actual "+alumnoComun.getApellido()+", "+alumnoComun.getNombre()+" (s/n) -->");
                        
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
                    verAlumnoMateriaSeleccionados(alumnoComun,materiaComun);
                    if(alumnoComun != null){
                        System.out.print("Desea borrar al alumno actual "+alumnoComun.getApellido()+", "+alumnoComun.getNombre()+" (s/n) -->");
                        
                        if(scan.next().startsWith("s"))
                            alumno.eliminarAlumno(alumnoComun.getIdAlumno());
                        else
                            System.out.println("Debe seleccionar un alumno mediante la busqueda del menu");
                    }else
                        System.out.println("Debe seleccionar un alumno mediante la busqueda del menu");
                    break;

                case 7:
                    verAlumnoMateriaSeleccionados(alumnoComun,materiaComun);
                    System.out.print("Ingrese el nombre de la materia -->");
                    String nombreMateria = scan.next();
                    System.out.print("Ingrese el anio de cursado de la materia -->");
                    int anioCursado = scan.nextInt();
                    materiaComun = new Materia(nombreMateria, anioCursado, true);
                    materia.guardarMateria(materiaComun);
                    break;

                case 8:
                    verAlumnoMateriaSeleccionados(alumnoComun,materiaComun);
                    System.out.print("Ingrese el id de la materia a buscar -->");
                    id = scan.nextInt();
                    materiaComun = materia.buscarMateria(id);

                    if(materiaComun != null){
                        System.out.println(materiaComun);
                    }
                    break;
                    
                case 9:
                    verAlumnoMateriaSeleccionados(alumnoComun,materiaComun);
                    if(materiaComun != null){
                        System.out.print("Desea actualizar la materia actual "+materiaComun.getNombre()+", "+materiaComun.getAnioMateria()+" (s/n) --> ");

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
                    verAlumnoMateriaSeleccionados(alumnoComun,materiaComun);
                    if(materiaComun != null){
                    System.out.print("Desea borrar la materia actual "+materiaComun.getNombre()+", "+materiaComun.getAnioMateria()+" (s/n) -->");

                    if(scan.next().startsWith("s"))
                        materia.eliminarMateria(materiaComun.getIdMateria());
                    else
                        System.out.println("Debe seleccionar una materia mediante la busqueda del menu");
                    }else
                        System.out.println("Debe seleccionar una materia mediante la busqueda del menu");
                    break;
                    
                case 12:
                    verAlumnoMateriaSeleccionados(alumnoComun,materiaComun);
                    if(materiaComun != null && alumnoComun != null){
                        System.out.print("Desea inscribir al alumno "+alumnoComun.getApellido()+", "+alumnoComun.getNombre()
                                            +" en la materia "+materiaComun.getNombre()+" ? (s/n) -->");
                        if (scan.next().startsWith("s")) {
                            inscripcion = new InscripcionData();
                            inscripcionComun.setAlumno(alumnoComun);
                            inscripcionComun.setMateria(materiaComun);
                            inscripcionComun.setNota(-1);
                            inscripcion.guardarInscripcion(inscripcionComun);
                            
                        }else System.out.println("Debe realizar las busquedas del alumno y materia a inscribir.");
                    }else System.out.println("Debe realizar las busquedas del alumno y materia a inscribir.");
                    break;
                    
                case 13:
                    verAlumnoMateriaSeleccionados(alumnoComun,materiaComun);
                    System.out.println(" --------- INSCRIPCIONES REGISTRADAS ----------");
                    for (Inscripcion inscr : inscripcion.obtenerInscripciones()) {
                        System.out.println("Inscripcion nro: "+inscr.getIdInscripcion()+" - Alumno: "+inscr.getAlumno().getApellido()+", "+inscr.getAlumno().getNombre()+
                                            " - Materia: "+inscr.getMateria().getNombre()+" de "+inscr.getMateria().getAnioMateria()+" ° anio");
                    }
                    
                    break;
                    
                case 14:
                    verAlumnoMateriaSeleccionados(alumnoComun,materiaComun);
                    if (alumnoComun != null) {
                    System.out.print("Desea listar las inscripciones del alumno actual "+alumnoComun.getApellido()+", "+alumnoComun.getNombre()+" (s/n)--> ");
                        if(scan.next().startsWith("s")){
                            System.out.println(" --------- INSCRIPCIONES DE "+alumnoComun.getApellido()+", "+alumnoComun.getNombre()+" ----------");
                            for(Inscripcion inscr : inscripcion.obtenerInscripcionesPorAlumno(alumnoComun.getIdAlumno())){
                                System.out.println("Materia: "+inscr.getMateria().getNombre()+" de "+inscr.getMateria().getAnioMateria()+" ° anio con nota: "+inscr.getNota());
                            }
                        }
                        else
                            System.out.println("Debe seleccionar un alumno mediante la busqueda del menu");
                    }else System.out.println("Debe seleccionar un alumno mediante la busqueda del menu");
                    break;
                    
                case 15:
                    verAlumnoMateriaSeleccionados(alumnoComun,materiaComun);
                    if (alumnoComun != null) {
                        System.out.print("Desea listar las materias cursadas del alumno actual "+alumnoComun.getApellido()+", "+alumnoComun.getNombre()+" (s/n)-->");
                        if(scan.next().startsWith("s")){
                            System.out.println(" --------- MATERIAS CURSADAS POR "+alumnoComun.getApellido()+", "+alumnoComun.getNombre()+" ----------");
                            for(Materia mat : inscripcion.obtenerMateriasCursadas(alumnoComun.getIdAlumno())){
                                System.out.println("Materia: "+mat.getNombre()+" de "+mat.getAnioMateria());
                            }
                        }else System.out.println("Debe seleccionar un alumno mediante la busqueda del menu");
                    }else System.out.println("Debe seleccionar un alumno mediante la busqueda del menu");
                    break;
                    
                case 16:
                    verAlumnoMateriaSeleccionados(alumnoComun,materiaComun);
                    if (alumnoComun != null) {
                        System.out.print("Desea listar las materias NO cursadas del alumno actual "+alumnoComun.getApellido()+", "+alumnoComun.getNombre()+" (s/n)-->");
                        if(scan.next().startsWith("s")){
                            System.out.println(" --------- MATERIAS NO CURSADAS POR "+alumnoComun.getApellido()+", "+alumnoComun.getNombre()+" ----------");
                            for(Materia mat : inscripcion.obtenerMateriasNoCursadas(alumnoComun.getIdAlumno())){
                                System.out.println("Materia: "+mat.getNombre()+" de "+mat.getAnioMateria());
                            }
                        }else System.out.println("Debe seleccionar un alumno mediante la busqueda del menu");
                    }else System.out.println("Debe seleccionar un alumno mediante la busqueda del menu");
                    break;
                    
                case 17:
                    verAlumnoMateriaSeleccionados(alumnoComun,materiaComun);
                    if(materiaComun != null && alumnoComun != null){
                        System.out.print("Desea eliminar la inscripcion del alumno "+alumnoComun.getApellido()+", "+alumnoComun.getNombre()
                                            +" en la materia "+materiaComun.getNombre()+" ? (s/n) -->");
                        if (scan.next().startsWith("s")) {
                            inscripcion.borrarInscripcionMateriaAlumno(alumnoComun.getIdAlumno(), materiaComun.getIdMateria());
                            
                        }else System.out.println("Debe realizar las busquedas del alumno y materia a desinscribir.");
                    }else System.out.println("Debe realizar las busquedas del alumno y materia a desinscribir.");
                    
                    break;
                    
                case 18:
                    verAlumnoMateriaSeleccionados(alumnoComun,materiaComun);
                    if(materiaComun != null && alumnoComun != null){
                        System.out.print("Desea actualizar la nota del alumno "+alumnoComun.getApellido()+", "+alumnoComun.getNombre()
                                            +" en la materia "+materiaComun.getNombre()+" ? (s/n) -->");
                        if (scan.next().startsWith("s")) {
                            System.out.print("Ingrese la nota -->");
                            inscripcion.actualizarNota(alumnoComun.getIdAlumno(), materiaComun.getIdMateria(),scan.nextDouble());
                        }else System.out.println("Debe realizar las busquedas del alumno y materia para cambiar la nota.");
                    }else System.out.println("Debe realizar las busquedas del alumno y materia para cambair la nota.");
                    
                    break;
                    
                case 19:
                    verAlumnoMateriaSeleccionados(alumnoComun,materiaComun);
                    if (materiaComun != null) {
                        System.out.print("Desea listar los alumnos inscriptos en la materia "+materiaComun.getNombre()+" de "+materiaComun.getAnioMateria()+" ° anio ? (s/n) -->");
                        if(scan.next().startsWith("s")){
                            System.out.println(" --------- ALUMNOS INSCRIPTOS EN "+materiaComun.getNombre()+" de "+materiaComun.getAnioMateria()+" ° anio ----------");
                            for(Alumno alu : inscripcion.obtenerAlumnosXMateria(materiaComun.getIdMateria())){
                                System.out.println("Alumno: "+alu.getNombre()+", "+alu.getApellido()+" DNI: "+alu.getDni());
                            }
                        }else System.out.println("Debe seleccionar un alumno mediante la busqueda del menu");
                    }else System.out.println("Debe seleccionar un alumno mediante la busqueda del menu");
                    break;
            }
        }while(opc != 20);
    }
    
    public static void verAlumnoMateriaSeleccionados(Alumno alu, Materia mat){
        System.out.println("\n---  ALUMNO Y MATERIA SELECCIONADOS ---");
        if (alu != null) System.out.println("Alumno: "+alu.getApellido()+", "+alu.getApellido());
        else System.out.println("ALUMNO -> NINGUNO");
        if (mat != null) System.out.println("Materia: "+mat.getNombre()+" de "+mat.getAnioMateria()+" anio");
        else System.out.println("MATERIA -> NINGUNA");
        System.out.println("----------------------------------------");
    }

}
