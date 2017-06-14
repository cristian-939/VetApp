package com.vett.cryst.vetapp;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by cryst on 29/05/2017.
 */

public class AdaptadorServicios extends RecyclerView.Adapter<AdaptadorServicios.MensajeViewHolder>   {
    private static List<Servicio> listaservicios;


    public AdaptadorServicios(List<Servicio> listaservicios) {
        this.listaservicios = listaservicios;
    }




    @Override
//para crearlo al principio
    public AdaptadorServicios.MensajeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.fila_servicio, parent, false);
        AdaptadorServicios.MensajeViewHolder holder = new AdaptadorServicios.MensajeViewHolder(v);
        return holder;
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onBindViewHolder(AdaptadorServicios.MensajeViewHolder holder, int position) {

        Servicio c = listaservicios.get(position);
        holder.txtTitulo.setText(c.getTitulo());
        holder.img.setImageResource(c.getImagen());




    }

    @Override
    public int getItemCount() {
        return listaservicios.size();
    }



    public static class MensajeViewHolder extends RecyclerView.ViewHolder {
        //ImageView imgMensaje;
        TextView txtTitulo;
        private CardView cv;
        private ImageView img;


        public MensajeViewHolder(View itemView) {

            super(itemView);
            // imgMensaje = (ImageView) itemView.findViewById(R.id.imgMensaje);

            txtTitulo = (TextView) itemView.findViewById(R.id.viewtituloconsejoservicio);
            img = (ImageView) itemView.findViewById(R.id.imgrecyclerservicio);

            cv = (CardView) itemView.findViewById(R.id.cv2);

        }


    }

}
