package com.vett.cryst.vetapp;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.io.Serializable;

/**
 * Created by daniel on 06/05/2017.
 */

public class Consejo  {

    private String titulo, descripcion;
    int img;

    public Consejo(String titulo, String descripcion, int img) {
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

    public Consejo() {
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
