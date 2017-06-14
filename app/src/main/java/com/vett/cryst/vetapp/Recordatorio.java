package com.vett.cryst.vetapp;

/**
 * Created by cryst on 28/05/2017.
 */

public class Recordatorio {

    String correo, fecha, hora, mensaje;

    public Recordatorio(String correo, String fecha, String hora, String mensaje) {
        this.correo = correo;
        this.fecha = fecha;
        this.hora = hora;
        this.mensaje = mensaje;
    }


    public Recordatorio() {
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    @Override
    public String toString() {
        return "Recordatorio{" +
                "correo='" + correo + '\'' +
                ", fecha='" + fecha + '\'' +
                ", hora='" + hora + '\'' +
                ", mensaje='" + mensaje + '\'' +
                '}';
    }
}
