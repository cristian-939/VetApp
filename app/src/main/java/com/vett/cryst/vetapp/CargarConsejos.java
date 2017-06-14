package com.vett.cryst.vetapp;

import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.FragmentActivity;
import android.widget.ImageView;
import android.widget.TextView;


/**
 * Created by daniel on 07/05/2017.
 */

public class CargarConsejos extends FragmentActivity {

    TextView titulo;
    TextView mensaje;
    ImageView imagen;
    ConsejoImagenStr obj;
    //______________
    TextView descripcion;
    CollapsingToolbarLayout toolbar_layout;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.consejo);
        cargarObjetos();
        recogerMensaje();
        mostrarMensaje();

    }
    private void mostrarMensaje() {
        /*titulo.setText(obj.getTitulo());
        mensaje.setText(obj.getDescripcion());
        imagen.setImageResource(obj.getImg());*/
        //_______________AQUI VA EL LOCO___________________
        descripcion.setText(obj.getDescripcion());
        toolbar_layout.setBackgroundResource(obj.getImg());
        toolbar_layout.setTitle(obj.getTitulo());

    }

    private void recogerMensaje() {
        Bundle b = getIntent().getExtras();
        obj = (ConsejoImagenStr) b.get("obj");
    }

    private void cargarObjetos() {
        /*titulo = (TextView) findViewById(R.id.viewtituloconsejo);
        mensaje = (TextView) findViewById(R.id.viewdescripcionconsejo);
        imagen = (ImageView) findViewById(R.id.imgconsejocargado);*/
        //_____________________________________
        descripcion = (TextView) findViewById(R.id.textoDescripcion);
        toolbar_layout = (CollapsingToolbarLayout) findViewById(R.id.toolbar_layout);

    }
}
