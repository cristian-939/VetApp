package com.vett.cryst.vetapp;

/**
 * Created by cryst on 26/05/2017.
 */

public class Servicio {
    String titulo;
    int imagen;

    public Servicio() {
    }

    public Servicio(String titulo, int imagen) {
        this.titulo = titulo;
        this.imagen = imagen;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }


    public int getImagen() {
        return imagen;
    }

    public void setImagen(int imagen) {
        this.imagen = imagen;
    }
}
