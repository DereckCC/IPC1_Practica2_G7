package com.grupo_7;

public class Usuario {
    private String userName;
    private String password;

    public Usuario() {
        this.userName = "";
        this.password = "";
    }

    public Usuario(String userNameA, String passwordA) {
        this.userName = userNameA;
        this.password = passwordA;
    }

    public void MostrarDatos() {

        System.out.println(userName + ", " + password);
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
