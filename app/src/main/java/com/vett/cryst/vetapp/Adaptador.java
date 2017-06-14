
package com.vett.cryst.vetapp;


import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;


public class Adaptador extends RecyclerView.Adapter<Adaptador.MensajeViewHolder>   {
    private static List<ConsejoImagenStr> listaconsejos;


    public Adaptador(List<ConsejoImagenStr> listaconsejos) {
        this.listaconsejos = listaconsejos;
    }




    @Override
//para crearlo al principio
    public MensajeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.fila_consejo, parent, false);
        MensajeViewHolder holder = new MensajeViewHolder(v);
        return holder;
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onBindViewHolder(MensajeViewHolder holder, int position) {

        ConsejoImagenStr c = listaconsejos.get(position);
        holder.txtTitulo.setText(c.getTitulo());
        holder.txtMensaje.setText(c.getDescripcion());
        holder.img.setImageResource(c.getImg());



         //holder.img.setBackground(c.getImg());

       // AppCompatActivity ap = new AppCompatActivity();

       // ap.getResources().getResourceName(nombreImagen);
        //int imaa = ap.getResources().getIdentifier(nombreImagen, "drawable", ap.getPackageName());


        //holder.img.setImageResource(Integer.parseInt(c.getImg()));


       // int imagerResource = ap.getResources().getIdentifier(nombreImagen, null, ap.getPackageName());
       // Drawable imagenFinal = ap.getResources().getDrawable(imagerResource);


      //  holder.img.setImageDrawable(imagenFinal);






       // Log.v("asis",String.valueOf(position));
        //String url = listaconsejos.get(position).getUrl();
        /*holder.img.setImageResource(listaconsejos.get(position).getUrl());
        holder.txtTitulo.setText(listaconsejos.get(position).getTitulo());
        holder.txtMensaje.setText(listaconsejos.get(position).getDescripcion());*/
    }

    @Override
    public int getItemCount() {
        return listaconsejos.size();
    }



    public static class MensajeViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        //ImageView imgMensaje;
        TextView txtTitulo;
        TextView txtMensaje;
        private CardView cv;
        private ImageView img;


        public MensajeViewHolder(View itemView) {

            super(itemView);
            itemView.setOnClickListener(this);
            // imgMensaje = (ImageView) itemView.findViewById(R.id.imgMensaje);
            txtMensaje = (TextView) itemView.findViewById(R.id.viewdescripcionconsejo);
            txtMensaje.setMaxLines(2);
            txtTitulo = (TextView) itemView.findViewById(R.id.viewtituloconsejo);
            img = (ImageView) itemView.findViewById(R.id.imgrecycler);

            cv = (CardView) itemView.findViewById(R.id.cv);

        }

        public void onClick(View view) {

            //listener.onItemClick(v, getAdapterPosition());
           // Log.v("click","asis");

            Context context = itemView.getContext();
            int pos = getAdapterPosition();
            ConsejoImagenStr c = listaconsejos.get(pos);
            Log.v("posss",String.valueOf(pos));
            //el de la fila consejos
            String txttit = c.getTitulo();
            String txtmen = c.getDescripcion();
            int img = c.getImg();

         //   Consejo m = new Consejo(txttit, txtmen, listaconsejos.get(pos).getImg());

            ConsejoImagenStr cistr = new ConsejoImagenStr(txttit, txtmen, img);

            Intent intent = new Intent(context, CargarConsejos.class);
            intent.putExtra("obj", cistr);
            context.startActivity(intent);
        }


    }

}
