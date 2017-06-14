package com.vett.cryst.vetapp;

/**
 * Created by cryst on 28/05/2017.
 */

public class RecordatorioCorto {
    String fechaJunta, mensaje;

    public RecordatorioCorto(String fechaJunta, String mensaje) {
        this.fechaJunta = fechaJunta;
        this.mensaje = mensaje;
    }

    public String getFechaJunta() {
        return fechaJunta;
    }

    public void setFechaJunta(String fechaJunta) {
        this.fechaJunta = fechaJunta;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
}
