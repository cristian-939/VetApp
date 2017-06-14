package com.vett.cryst.vetapp;

/**
 * Created by daniel on 02/05/2017.
 */

public class Mascota {
    String id, nombre, especie, correoDueno, raza,fechaNacimiento,sexo;

    public Mascota() {
    }

    public Mascota(String id, String nombre, String especie, String correoDueno, String raza, String fechaNacimiento, String sexo) {
        this.id = id;
        this.nombre = nombre;
        this.especie = especie;
        this.correoDueno = correoDueno;
        this.raza = raza;
        this.fechaNacimiento = fechaNacimiento;
        this.sexo = sexo;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEspecie() {
        return especie;
    }

    public void setEspecie(String especie) {
        this.especie = especie;
    }

    public String getCorreoDueno() {
        return correoDueno;
    }

    public void setCorreoDueno(String correoDueno) {
        this.correoDueno = correoDueno;
    }

    public String getRaza() {
        return raza;
    }

    public void setRaza(String raza) {
        this.raza = raza;
    }

    public String getEdad() {
        return fechaNacimiento;
    }

    public void setEdad(String fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }
}
