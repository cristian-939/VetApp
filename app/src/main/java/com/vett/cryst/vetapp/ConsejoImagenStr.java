package com.vett.cryst.vetapp;

import android.graphics.drawable.Drawable;

import java.io.Serializable;

/**
 * Created by cryst on 25/05/2017.
 */

public class ConsejoImagenStr implements Serializable {

    private static final long serialVersionUID = 3185099950959560099L;

    private String titulo, descripcion ;
    private int img;

    public ConsejoImagenStr(String titulo, String descripcion, int img) {
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.img = img;
    }

    @Override
    public String toString() {
        return "Consejo{" +
                "titulo='" + titulo + '\'' +
                ", descripcion='" + descripcion + '\'' +
                '}';
    }

    public ConsejoImagenStr() {
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }


}
