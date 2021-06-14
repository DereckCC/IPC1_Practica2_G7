package com.grupo_7;

public class AsignarProfesor {
    int idProfesor;
    int idCurso;

    public AsignarProfesor(int idProfesor, int idCurso) {
        this.idProfesor = idProfesor;
        this.idCurso = idCurso;
    }
    public int getIdProfesor() {
        return idProfesor;
    }

    public void setIdProfesor(int idProfesor) {
        this.idProfesor = idProfesor;
    }

    public int getIdCurso() {
        return idCurso;
    }

    public void setIdCurso(int idCurso) {
        this.idCurso = idCurso;
    }
}
