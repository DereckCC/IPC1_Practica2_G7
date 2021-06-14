package com.grupo_7;

import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class Main {

    public static Scanner consola = new Scanner(System.in);
    public static ProcesoAlumnos procesoAlumnos = new ProcesoAlumnos();
    public static ProcesoProfesores procesoProfesores = new ProcesoProfesores();
    public static ProcesoCursos procesoCursos = new ProcesoCursos();
    public static ProcesoAsignarAlumnos procesoAsignarAlumnos = new ProcesoAsignarAlumnos();
    public static ProcesoAsignarProfesores procesoAsignarProfesores = new ProcesoAsignarProfesores();
    public static ProcesoNotas procesoNotas = new ProcesoNotas();
    private static boolean adminControl = false;
    public static final Usuario[] usuario = new Usuario[5];
    static int guardar_alumnos = 0, guardar_proferores = 0, guardar_cursos = 0, guardar_usuario = 0;

    public static void main(String[] args) throws IOException {
        // write your code here
        startMenu(login());
    }

    public static void startMenu(boolean loginControl) throws IOException {
        if (loginControl) {
            String menu_opciones = "";

            System.out.println("Seleccione una opción:\n");
            if (adminControl) System.out.println("00. Crear Usuario.");
            System.out.println("01. Cargar Alumnos.");
            System.out.println("02. Cargar Profesores.");
            System.out.println("03. Cargar cursos.");
            System.out.println("04. Asignar alumnos.");
            System.out.println("05. Asignar profesores.");
            System.out.println("06. Cargar notas.");
            System.out.println("07. Agregar usuario al sistema.");
            System.out.println("08. Generar reportes.");
            System.out.println("09. Cerrar sesión.");

            menu_opciones = consola.nextLine();

            switch (menu_opciones) {
                case "0":
                    if (adminControl) crearUsuario();
                    break;
                case "1":
                    System.out.println("Ingrese la ruta del archivo.");
                    procesoAlumnos.leerTextoPlano();
                    procesoAlumnos.mostrarArreglo();
                    break;
                case "2":
                    System.out.println("Ingrese la ruta del archivo.");
                    procesoProfesores.leerTextoPlano();
                    procesoProfesores.mostrarArreglo();
                    break;
                case "3":
                    System.out.println("Ingrese la ruta del archivo.");
                    procesoCursos.leerTextoPlano();
                    procesoCursos.mostrarArreglo();
                    break;
                case "4":
                    System.out.println("Ingrese la ruta del archivo.");
                    procesoAsignarAlumnos.leerTextoPlano(procesoAlumnos.alumnos, procesoAlumnos.contador, procesoCursos.cursos, procesoCursos.contador);
                    procesoAsignarAlumnos.mostrarArreglo();
                    break;
                case "5":
                    System.out.println("Ingrese la ruta del archivo.");
                    procesoAsignarProfesores.leerTextoPlano(procesoProfesores.profesores, procesoProfesores.contador, procesoCursos.cursos, procesoCursos.contador);
                    procesoAsignarProfesores.mostrarArreglo();
                    break;
                case "6":
                    System.out.println("Ingrese la ruta del archivo");
                    procesoNotas.leerTextoPlano(procesoAlumnos.alumnos, procesoAlumnos.contador, procesoCursos.cursos, procesoCursos.contador);
                    procesoNotas.mostrarArreglo();
                    break;
                case "7":

                    break;
                case "8":
                    menuReportes();
                default:
                    System.out.println("Opción no valida\n");
                    break;
                case "9":
                    loginControl = false;
                    adminControl = false;
                    System.exit(0);
                    break;
            }
        }
        startMenu(true);
    }

    //---------------------------------------------------------------------------------

    public static boolean login() {

        Console console = System.console();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Ingresar nombre de Usuario: ");
        String user = scanner.nextLine();


        Scanner scanner1 = new Scanner(System.in);
        System.out.println("Ingresar contraseña: ");
        String password = scanner1.nextLine();

        if (user.equals("admin") && password.equals("admin")) {
            System.out.println("Bienvenido Administrador");
            adminControl = true;
            return true;
            //falta adjuntar metodos para que se ejecuten solo en caso de ser admin
        } else if (user.equals("1") && password.equals("1")) {
            System.out.println("Bienvenido <Insertar nombre usuario>");
            return true;
        } else {
            System.out.println("Usuario y/o contraseña incorrectos.");
            return false;
        }

        /*String username = console.readLine("Username: ");
        System.out.println("Ingresar contraseña: ");
        System.out.println("username = " + username);
        char[] password = console.readPassword("Password: ");
        System.out.println("password = " + password);

        Console console = System.console();
        Scanner scanner1 = new Scanner(System.in);
        char [] password = console.readPassword();*/
    }

//---------------------------------------------------------------------------------

    public static void crearUsuario() {
        String ruta;
        System.out.println("Ingrese la ruta de archivo");
        ruta = consola.nextLine();
        File archivo = null;
        FileReader fi = null;
        BufferedReader buffer = null;
        try {
            archivo = new File(ruta);
            fi = new FileReader(archivo);
            buffer = new BufferedReader(fi);
            String lecturaArchivo = buffer.readLine();
            System.out.println("Usuario, Contraseña, confirmarContraseña");
            while ((lecturaArchivo = buffer.readLine()) != null) {
                String[] separar = lecturaArchivo.split(", ");
                for (int i = 0; i < separar.length; i++) {
                    String userName = separar[0];
                    String password = separar[1];
                    Usuario crearUsuario = new Usuario(userName, password);
                    usuario[guardar_usuario] = crearUsuario;
                }
                guardar_usuario++;
            }
            mostrardatos(usuario);
        } catch (Exception e) {

        } finally {
            try {
                if (null != fi) {
                    fi.close();
                }
            } catch (Exception e2) {
                System.out.println("Algo salio mal. Intente de nuevo");
                System.out.println(e2.getMessage());
            }
        }
    }

    public static void mostrardatos(Usuario[] datos) {

        for (int i = 0; i < datos.length; i++) {
            datos[i].MostrarDatos();
        }

    }
//---------------------------------------------------------------------------------

    //REPORTES

    public static void menuReportes() throws IOException {
        String menu_opciones = "";

        System.out.println("Ingrese la opción del reporte que desea generar");
        System.out.println("01. Reporte de Alumnos");
        System.out.println("02. Reporte de Asignación de Alumnos");
        System.out.println("03. Reporte de Asignación de Profesores");
        System.out.println("04. Reporte General de Cursos");
        System.out.println("05. Reporte de Curso en Específico");
        System.out.println("06. Mejores Alumnos");
        System.out.println("07. Regresar a menu Principal");


        menu_opciones = consola.nextLine();

        switch (menu_opciones) {
            case "1":
                report("Reporte de Alumnos", 1);
                break;
            case "2":
                report("Reporte de Asignacion Alumnos", 2);
                break;
            case "3":
                report("Reporte de Asignacion Profesores", 3);
                break;
            case "4":
                report("Reporte General de Cursos", 4);
                break;
            case "5":
                report("Reporte Especifico", 5);
                break;
            case "6":
                report("Reporte Mejores Alumnos", 6);
                break;
            case "7":
                startMenu(true);
                break;
            default:
                System.out.println("OpciÃ³n no valida\n");
                break;


        }

        startMenu(true);
    }

    public static void report(String title, int type) {

        FileWriter filewriter = null;
        PrintWriter printw = null;
        Date date = new Date();

        try {
            filewriter = new FileWriter(title + ".html");
            printw = new PrintWriter(filewriter);
            DateFormat hourFormat = new SimpleDateFormat("HH:mm:ss");
            DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

            printw.println("<html>");
            printw.println("<head>");
            printw.println("<title>" + title + "</title>");
            printw.println("<link href=\"https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/css/bootstrap.min.css\" rel=\"stylesheet\" integrity=\"sha384-BmbxuPwQa2lc/FVzBcNJ7UAyJxM6wuqIj61tLrc4wSX0szH/Ev+nYRRuWlolflfl\" crossorigin=\"anonymous\">");
            printw.println("<link rel=\"stylesheet\" href=\"style.css\">");
            printw.println("</head>");

            printw.println("<body style=\"padding: 40px\" bgcolor=\"#bdc3c7\">");


            if (type == 1) {
                try {
                    printw.println("<container class=\"container\">");
                    printw.println("<center style=\"margin-bottom: 20px\"><h1><font>" + title + "</font></h1></center>");
                    printw.println("<br>");
                    printw.println("<h4 class=\"fs-4\">");

                    printw.println("</h4>");
                    printw.println("<h4 class=\"fs-4\">");
                    printw.println("Hora de Creacion: " + hourFormat.format(date) + " " + "Fecha de Creacion: " + dateFormat.format(date));
                    printw.println("</h4>");
                    printw.println("<div style=\"margin-bottom: 10px\">");

                    printw.println("<table class=\"table table-dark table-striped\">");
                    printw.println("<thead>");
                    printw.println("<tr>");
                    printw.println("<table class=\"table table-dark table-striped\">");
                    printw.println("<th scope=\"col\">CARNET</th>");
                    printw.println("<th scope=\"col\">NOMBRE</th>");
                    printw.println("<th scope=\"col\">EDAD</th>");
                    printw.println("<th scope=\"col\">GENERO</th>");
                    printw.println("</tr>");
                    printw.println("</thead>");

                    printw.println("<tbody>");

                    for (int i = 0; i < ProcesoAlumnos.contador; i++) {

                        printw.println("<tr>");
                        printw.println("<th>" + ProcesoAlumnos.alumnos[i].carnet + "</th>");
                        printw.println("<th>" + ProcesoAlumnos.alumnos[i].nombre + "</th>");

                        int edad;
                        int anioActual = 2021;
                        String[] fechaNac = ProcesoAlumnos.alumnos[i].getFechaNacimiento().split("/");
                        String anio = fechaNac[2];
                        int anioA = Integer.parseInt(anio);
                        edad = anioActual - anioA;
                        printw.println("<th>" + edad + "</th>");

                        char gen = ProcesoAlumnos.alumnos[i].getGenero();
                        String g = String.valueOf(gen);

                        if (g.equals("M")) {
                            g = "Masculino";
                        } else {
                            g = "Femenino";
                        }
                        printw.println("<th>" + g + "</th>");
                        printw.println("</tr>");
                    }
                    printw.println("</tbody>");
                    printw.println("</table>");
                    printw.println("</div>");

                    printw.println("</container>");

                    System.out.println("Generado exitosamente, en la ruta root.");
                } catch (Exception e) {
                    System.out.println("Error al generar.");
                }

            }
            if (type == 2) {
                try {
                    printw.println("<container class=\"container\">");
                    printw.println("<center style=\"margin-bottom: 20px\"><h1><font>" + title + "</font></h1></center>");
                    printw.println("<br>");
                    printw.println("<h4 class=\"fs-4\">");

                    printw.println("</h4>");
                    printw.println("<h4 class=\"fs-4\">");
                    printw.println("Hora de Creacion: " + hourFormat.format(date) + " " + "Fecha de Creacion: " + dateFormat.format(date));
                    printw.println("</h4>");
                    printw.println("<div style=\"margin-bottom: 10px\">");

                    printw.println("<table class=\"table table-dark table-striped\">");
                    printw.println("<thead>");
                    printw.println("<tr>");
                    printw.println("<table class=\"table table-dark table-striped\">");
                    printw.println("<th scope=\"col\">CARNET</th>");
                    printw.println("<th scope=\"col\">NOMBRE ALUMNO</th>");
                    printw.println("<th scope=\"col\">CODIGO</th>");
                    printw.println("<th scope=\"col\">NOMBRE CURSO</th>");
                    printw.println("<th scope=\"col\">FECHA ASIGNACION</th>");
                    printw.println("</tr>");
                    printw.println("</thead>");

                    printw.println("<tbody>");
                    printw.println("<tr>");

                    for (int i = 0; i < ProcesoAsignarAlumnos.contador; i++) {
                        for (int j = 0; j < ProcesoAlumnos.contador; j++) {
                            //System.out.println(ProcesoAlumnos.alumnos[j].getId() + "" + ProcesoAsignarAlumnos.asignarAlumnos[i].getIdAlumno());

                            if (ProcesoAlumnos.alumnos[j].getId() == ProcesoAsignarAlumnos.asignarAlumnos[i].getIdAlumno()) {
                                printw.println("<th>" + ProcesoAlumnos.alumnos[j].getCarnet() + "</th>");
                                printw.println("<th>" + ProcesoAlumnos.alumnos[j].getNombre() + "</th>");
                            }
                        }
                        for (int j = 0; j < ProcesoCursos.contador; j++) {
                            if (ProcesoCursos.cursos[j].getId() == ProcesoAsignarAlumnos.asignarAlumnos[i].getIdCurso()) {
                                printw.println("<th>" + ProcesoCursos.cursos[j].getCodigo() + "</th>");
                                printw.println("<th>" + ProcesoCursos.cursos[j].getNombre() + "</th>");
                            }

                        }
                        printw.println("<th>" + dateFormat.format(date) + "</th>");
                        printw.println("</tr>");
                    }
                    printw.println("</tbody>");
                    printw.println("</table>");
                    printw.println("</div>");

                    printw.println("</container>");

                    System.out.println("Generado exitosamente, en la ruta root.");
                } catch (Exception e) {
                    System.out.println("Error al generar.");
                }
            }
            if (type == 3) {
                try {
                    printw.println("<container class=\"container\">");
                    printw.println("<center style=\"margin-bottom: 20px\"><h1><font>" + title + "</font></h1></center>");
                    printw.println("<br>");
                    printw.println("<h4 class=\"fs-4\">");

                    printw.println("</h4>");
                    printw.println("<h4 class=\"fs-4\">");
                    printw.println("Hora de Creacion: " + hourFormat.format(date) + " " + "Fecha de Creacion: " + dateFormat.format(date));
                    printw.println("</h4>");
                    printw.println("<div style=\"margin-bottom: 10px\">");

                    printw.println("<table class=\"table table-dark table-striped\">");
                    printw.println("<thead>");
                    printw.println("<tr>");
                    printw.println("<table class=\"table table-dark table-striped\">");
                    printw.println("<th scope=\"col\">REGISTRO</th>");
                    printw.println("<th scope=\"col\">NOMBRE PROFESOR</th>");
                    printw.println("<th scope=\"col\">CODIGO</th>");
                    printw.println("<th scope=\"col\">NOMBRE CURSO</th>");
                    printw.println("<th scope=\"col\">FECHA ASIGNACION</th>");
                    printw.println("</tr>");
                    printw.println("</thead>");

                    printw.println("<tbody>");
                    printw.println("<tr>");

                    for (int i = 0; i < ProcesoAsignarProfesores.contador; i++) {
                        for (int j = 0; j < ProcesoProfesores.contador; j++) {
                            if (ProcesoProfesores.profesores[j].getId() == ProcesoAsignarProfesores.asignarProfesores[i].getIdProfesor()) {
                                printw.println("<th>" + ProcesoProfesores.profesores[j].getRegistroPersonal() + "</th>");
                                printw.println("<th>" + ProcesoProfesores.profesores[j].getNombre() + "</th>");
                            }
                        }

                        for (int j = 0; j < ProcesoCursos.contador; j++) {
                            if (ProcesoCursos.cursos[j].getId() == ProcesoAsignarProfesores.asignarProfesores[i].getIdCurso()) {
                                printw.println("<th>" + ProcesoCursos.cursos[j].getCodigo() + "</th>");
                                printw.println("<th>" + ProcesoCursos.cursos[j].getNombre() + "</th>");
                            }

                        }

                        printw.println("<th>" + dateFormat.format(date) + "</th>");

                        printw.println("</tr>");

                    }
                    printw.println("</tbody>");
                    printw.println("</table>");
                    printw.println("</div>");

                    printw.println("</container>");

                    System.out.println("Generado exitosamente, en la ruta root.");
                } catch (Exception e) {
                    System.out.println("Error al generar.");
                }
            }
            if (type == 4) {
                try {
                    printw.println("<container class=\"container\">");
                    printw.println("<center style=\"margin-bottom: 20px\"><h1><font>" + title + "</font></h1></center>");
                    printw.println("<br>");
                    printw.println("<h4 class=\"fs-4\">");

                    printw.println("</h4>");
                    printw.println("<h4 class=\"fs-4\">");
                    printw.println("Hora de Creacion: " + hourFormat.format(date) + " " + "Fecha de Creacion: " + dateFormat.format(date));
                    printw.println("</h4>");
                    printw.println("<div style=\"margin-bottom: 10px\">");

                    printw.println("<table class=\"table table-dark table-striped\">");
                    printw.println("<thead>");
                    printw.println("<tr>");
                    printw.println("<table class=\"table table-dark table-striped\">");
                    printw.println("<th scope=\"col\">CODIGO</th>");
                    printw.println("<th scope=\"col\">NOMBRE</th>");
                    printw.println("<th scope=\"col\"># ALUMNOS</th>");
                    printw.println("</tr>");
                    printw.println("</thead>");

                    for (int i = 0; i < ProcesoAsignarAlumnos.contador; i++) {
                        for (int j = 0; j < ProcesoCursos.contador; j++) {
                            if (ProcesoCursos.cursos[j].getId() == ProcesoAsignarAlumnos.asignarAlumnos[i].getIdCurso()) {
                                printw.println("<th>" + ProcesoCursos.cursos[j].getCodigo() + "</th>");
                                printw.println("<th>" + ProcesoCursos.cursos[j].getNombre() + "</th>");
                            }

                        }

                        int tamanio = ProcesoAsignarAlumnos.contador;
                        printw.println("<th>" + tamanio + "</th>");
                        printw.println("</tr>");


                    }

                    printw.println("</tbody>");
                    printw.println("</table>");
                    printw.println("</div>");

                    printw.println("</container>");

                    System.out.println("Generado exitosamente, en la ruta root.");
                } catch (Exception e) {
                    System.out.println("Error al generar.");
                }

            }
            if (type == 5) {
                try {
                    System.out.println("Ingrese el id del curso");
                    String s_curso = consola.nextLine();
                    int id_curso = Integer.parseInt(s_curso);
                    Cursos curso = null;
                    Profesores profe = null;
                    for (int j = 0; j < ProcesoCursos.contador; j++) {
                        if (ProcesoCursos.cursos[j].getId() == id_curso) {
                            curso = ProcesoCursos.cursos[j];
                        }
                    }
                    for (int i = 0; i < ProcesoAsignarProfesores.contador; i++) {
                        if (ProcesoAsignarProfesores.asignarProfesores[i].getIdCurso() == id_curso) {
                            for (int j = 0; j < ProcesoProfesores.contador; j++) {
                                if (ProcesoProfesores.profesores[j].getId() == ProcesoAsignarProfesores.asignarProfesores[i].getIdProfesor()) {
                                    profe = ProcesoProfesores.profesores[j];
                                }
                            }
                        }
                    }

                    printw.println("<container class=\"container\">");
                    printw.println("<center style=\"margin-bottom: 20px\"><h1><font>" + title + "</font></h1></center>");
                    printw.println("<br>");
                    printw.println("<h4 class=\"fs-4\">");

                    printw.println("</h4>");
                    printw.println("<h4 class=\"fs-4\">");
                    printw.println("Hora de Creacion: " + hourFormat.format(date) + " " + "Fecha de Creacion: " + dateFormat.format(date));
                    printw.println("</h4>");
                    printw.println("<div style=\"margin-bottom: 10px\">");

                    //ENCABEZADO
                    printw.println("<p class=\"h3\">Codigo Curso: " + curso.getCodigo() + "</p>");
                    printw.println("<p class=\"h3\">Nombre Curso: " + curso.getNombre() + "</p>");
                    printw.println("<p class=\"h3\">Codigo Personal: " + profe.getRegistroPersonal() + "</p>");
                    printw.println("<p class=\"h3\">Nombre Profesor: " + profe.getNombre() + "</p>");

                    printw.println("<table class=\"table table-dark table-striped\">");
                    printw.println("<thead>");
                    printw.println("<tr>");
                    printw.println("<table class=\"table table-dark table-striped\">");
                    printw.println("<th scope=\"col\">CARNE</th>");
                    printw.println("<th scope=\"col\">NOMBRE</th>");
                    printw.println("<th scope=\"col\">NOTA</th>");
                    printw.println("<th scope=\"col\">APROBADO</th>");
                    printw.println("</tr>");
                    printw.println("</thead>");

                    printw.println("<tbody>");
                    printw.println("<tr>");


                    for (int i = 0; i < ProcesoNotas.contador; i++) {
                        if (ProcesoNotas.asignarnotas[i].getIdCurso() == id_curso) {
                            for (int j = 0; j < ProcesoAsignarAlumnos.contador; j++) {
                                if (ProcesoAsignarAlumnos.asignarAlumnos[j].getIdCurso() == id_curso) {
                                    for (int k = 0; k < ProcesoAlumnos.contador; k++) {
                                        if (ProcesoAlumnos.alumnos[k].getId() == ProcesoAsignarAlumnos.asignarAlumnos[j].getIdAlumno()) {
                                            printw.println("<th>" + ProcesoAlumnos.alumnos[k].getCarnet() + "</th>");
                                            printw.println("<th>" + ProcesoAlumnos.alumnos[k].getNombre() + "</th>");
                                            printw.println("<th>" + ProcesoNotas.asignarnotas[j].getNota() + "</th>");

                                            Double nota = ProcesoNotas.asignarnotas[j].getNota();
                                            String respuesta = String.valueOf(nota);
                                            if (nota >= 61) {
                                                respuesta = "SI";
                                            } else {
                                                respuesta = "NO";
                                            }
                                            printw.println("<th>" + respuesta + "</th>");
                                        }
                                    }
                                }
                                printw.println("</tr>");
                            }

                        }
                    }


                    printw.println("</tbody>");
                    printw.println("</table>");
                    printw.println("</div>");

                    printw.println("</container>");

                    System.out.println("Generado exitosamente, en la ruta root.");

                } catch (Exception e) {
                    System.out.println("Error al generar");
                }

            }
            if (type == 6) {
                try {

                    System.out.println("Ingrese el id del curso");
                    String s_curso = consola.nextLine();
                    int id_curso = Integer.parseInt(s_curso);
                    Cursos curso = null;
                    Profesores profe = null;
                    for (int j = 0; j < ProcesoCursos.contador; j++) {
                        if (ProcesoCursos.cursos[j].getId() == id_curso) {
                            curso = ProcesoCursos.cursos[j];
                        }
                    }
                    for (int i = 0; i < ProcesoAsignarProfesores.contador; i++) {
                        if (ProcesoAsignarProfesores.asignarProfesores[i].getIdCurso() == id_curso) {
                            for (int j = 0; j < ProcesoProfesores.contador; j++) {
                                if (ProcesoProfesores.profesores[j].getId() == ProcesoAsignarProfesores.asignarProfesores[i].getIdProfesor()) {
                                    profe = ProcesoProfesores.profesores[j];
                                }
                            }
                        }
                    }

                    printw.println("<container class=\"container\">");
                    printw.println("<center style=\"margin-bottom: 20px\"><h1><font>" + title + "</font></h1></center>");
                    printw.println("<br>");
                    printw.println("<h4 class=\"fs-4\">");

                    printw.println("</h4>");
                    printw.println("<h4 class=\"fs-4\">");
                    printw.println("Hora de Creacion: " + hourFormat.format(date) + " " + "Fecha de Creacion: " + dateFormat.format(date));
                    printw.println("</h4>");
                    printw.println("<div style=\"margin-bottom: 10px\">");

                    //ENCABEZADO
                    printw.println("<p class=\"h3\">Codigo Curso: " + curso.getCodigo() + "</p>");
                    printw.println("<p class=\"h3\">Nombre Curso: " + curso.getNombre() + "</p>");
                    printw.println("<p class=\"h3\">Codigo Personal: " + profe.getRegistroPersonal() + "</p>");
                    printw.println("<p class=\"h3\">Nombre Profesor: " + profe.getNombre() + "</p>");

                    printw.println("<table class=\"table table-dark table-striped\">");
                    printw.println("<thead>");
                    printw.println("<tr>");
                    printw.println("<table class=\"table table-dark table-striped\">");
                    printw.println("<th scope=\"col\">NOMBRE</th>");
                    printw.println("<th scope=\"col\">NOTA</th>");
                    printw.println("</tr>");
                    printw.println("</thead>");

                    printw.println("<tbody>");
                    printw.println("<tr>");


                    printw.println("<th></th>");
                    printw.println("<th></th>");
                    printw.println("</tr>");


                    printw.println("</tbody>");
                    printw.println("</table>");
                    printw.println("</div>");

                    printw.println("</container>");

                    System.out.println("Generado exitosamente, en la ruta root.");

                } catch (Exception e) {
                    System.out.println("Error al generar");
                }


            }
            printw.println("</body>");
            printw.println("</html>");

            printw.close();


        } catch (IOException e) {
            System.out.println("Error al generar.");
        }
    }


//---------------------------------------------------------------------------------

}
