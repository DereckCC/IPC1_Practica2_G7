package com.grupo_7;

public class AsignarNotas {
    int idAlumno;
    int idCurso;
    double nota;

    public AsignarNotas(int idAlumno, int idCurso, double nota) {
        this.idAlumno = idAlumno;
        this.idCurso = idCurso;
        this.nota = nota;
    }

    public int getIdAlumno() {
        return idAlumno;
    }

    public void setIdAlumno(int idAlumno) {
        this.idAlumno = idAlumno;
    }

    public int getIdCurso() {
        return idCurso;
    }

    public void setIdCurso(int idCurso) {
        this.idCurso = idCurso;
    }

    public double getNota() {
        return nota;
    }

    public void setNota(double nota) {
        this.nota = nota;
    }
}
