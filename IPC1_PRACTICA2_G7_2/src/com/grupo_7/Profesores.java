package com.grupo_7;

public class Profesores {
    int id;
    int registroPersonal;
    String nombre;
    String fechaNacimiento;
    String fechaContratado;
    char genero;


    public Profesores(int id, int registroPersonal, String nombre, String fechaNacimiento, String fechaContratado, char genero) {
        this.id = id;
        this.registroPersonal = registroPersonal;
        this.nombre = nombre;
        this.fechaNacimiento = fechaNacimiento;
        this.fechaContratado = fechaContratado;
        this.genero = genero;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRegistroPersonal() {
        return registroPersonal;
    }

    public void setRegistroPersonal(int registroPersonal) {
        this.registroPersonal = registroPersonal;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(String fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getFechaContratado() {
        return fechaContratado;
    }

    public void setFechaContratado(String fechaContratado) {
        this.fechaContratado = fechaContratado;
    }

    public char getGenero() {
        return genero;
    }

    public void setGenero(char genero) {
        this.genero = genero;
    }

}
