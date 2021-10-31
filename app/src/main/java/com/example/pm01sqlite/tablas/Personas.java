package com.example.pm01sqlite.tablas;

public class Personas {
    private Integer id, edad;
    private String nombres, apellidos, correo;


    // Primer Contructor de clase
    public Personas(Integer id, Integer edad, String nombres, String apellidos, String correo) {
        this.id = id;
        this.edad = edad;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.correo = correo;
    }

    // Segundo contructor de clase
    public Personas(){ }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getEdad() {
        return edad;
    }

    public void setEdad(Integer edad) {
        this.edad = edad;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }
}
