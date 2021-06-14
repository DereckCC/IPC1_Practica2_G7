package com.grupo_7;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Date;
import java.util.Scanner;

public class ProcesoAsignarProfesores {
    public static AsignarProfesor[] asignarProfesores;
    public static int contador;
    Scanner sc = new Scanner(System.in);// "C:\Users\kevin\Downloads\mat2\alimentos.csv";

    public ProcesoAsignarProfesores() {
        asignarProfesores = new AsignarProfesor[30];
        contador = 0;
    }
    public void leerTextoPlano(Profesores[] profesores, int contadorProfesores, Cursos[] cursos, int contadorCursos) throws IOException {

        //Declaramos el buffer donde se almacenará el contenido del archivo
        BufferedReader br = null;
        //Ruta del archivo
        String lectura;
        String rutaArchivo;
        lectura = sc.nextLine();
        rutaArchivo = lectura;

        try {

            //Inicializamos el buffer colocando la ruta del archivo
            br = new BufferedReader(new FileReader(rutaArchivo));
            //Leemos el archivo línea por línea
            String linea = br.readLine();
            linea = br.readLine();

            //Mientras queden líneas en el buffer seguiremos leyendolas.
            while (linea != null) {
                String[] parametros = linea.split(",");
                boolean flag = false;

                for (int z = 0; z < contadorProfesores; z++) {
                    // only changes num, not the array element
                    if (profesores[z].getId() == Integer.parseInt(parametros[0])) {
                        flag = true;
                        break;
                    }
                }
                if (!flag) {
                    Log.addToEndFile("Logs.txt", " " + new Date().toString() + "No hemos encontrado profesor al  con el ID " + Integer.parseInt(parametros[0]) +"\n");
                    linea = br.readLine();      //Leemos una nueva línea del buffer;
                    continue;
                }
                flag = false;
                for (int z = 0; z < contadorCursos; z++) {
                    // only changes num, not the array element
                    if (cursos[z].getId() == Integer.parseInt(parametros[1])) {
                        flag = true;
                        break;
                    }
                }
                if (!flag) {
                    Log.addToEndFile("Logs.txt", " " + new Date().toString() + " No hemos encontrado al curso con el ID " + Integer.parseInt(parametros[1]) +"\n");
                    linea = br.readLine();      //Leemos una nueva línea del buffer;
                    continue;
                }
                asignarProfesores[contador] = new AsignarProfesor(Integer.parseInt(parametros[0].trim()), Integer.parseInt(parametros[1].trim()));
                contador++;

                linea = br.readLine();      //Leemos una nueva línea del buffer;
            }
            System.out.println("Los cursos y profesores que no se han podido asignar se encuentran en el log.");
        } catch (Exception e) {
            //En caso de error mostraremos este mensaje
            System.out.println("Ocurrió un error al momento de leer el archivo");

        } finally {
            // Si el buffer es diferente de nulo quiere decir que conseguimos abrir el archivo
            if (br != null) {
                //Por ende una vez dejemos de utilizarlo, debemos cerrarlo.
                br.close();
            }
        }
    }

    public void mostrarArreglo() {
        System.out.println("ID PROFESOR\t\tID CURSO\t\t");
        for (int i = 0; i < this.contador; i++) {
            System.out.println(this.asignarProfesores[i].getIdProfesor() + "\t\t" + this.asignarProfesores[i].getIdCurso());
        }
    }
}
