package com.grupo_7;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Date;
import java.util.Scanner;

public class ProcesoProfesores {
    public static Profesores[] profesores;
    public static int contador;
    Scanner sc = new Scanner(System.in);// "C:\Users\kevin\Downloads\mat2\datos.csv";

    public ProcesoProfesores() {
        profesores = new Profesores[20];
        contador = 0;
    }
    public void leerTextoPlano() throws IOException {

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

                for (int z = 0; z < contador; z++) {
                    // only changes num, not the array element
                    if (profesores[z].getId() == Integer.parseInt(parametros[0])) {
                        flag = true;
                        break;
                    }
                }
                if (flag) {
                    Log.addToEndFile("Logs.txt", " " + new Date().toString() + " El profesor con el id: " + Integer.parseInt(parametros[0]) +" Ya fue agregado"+ "\n");
                    linea = br.readLine();      //Leemos una nueva línea del buffer;
                    continue;
                }

                profesores[contador] = new Profesores(Integer.parseInt(parametros[0].trim()), Integer.parseInt(parametros[1].trim()),
                        parametros[2].trim(),parametros[3].trim(), parametros[4].trim(), (parametros[5].trim()).charAt(0));

                contador++;
                linea = br.readLine();      //Leemos una nueva línea del buffer;
            }
            System.out.println("Los profesores que no se han podido guardar estan en el log.");
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
        System.out.println("ID\t\tRegistro de personal\t\t\tNombre\t\tFecha de nacimiento\t\t\tFecha de contratación\t\t\tGénero");
        for (int i = 0; i < this.contador; i++) {
            System.out.println(this.profesores[i].getId() + "\t\t" + this.profesores[i].getRegistroPersonal()
                    + "\t\t" + this.profesores[i].getNombre() + "\t\t" + this.profesores[i].getFechaNacimiento()
                    + "\t\t" + this.profesores[i].getFechaContratado() + "\t\t" + this.profesores [i].getGenero()
                    + "\t\t" );
        }
    }
}
