package com.studio.oscar.agendaescolar.Objetos;

import java.io.Serializable;

public class Evt implements Serializable {

    String nameEvt;
    String descripcion;
    String fecha;
    String hora;

    public Evt(String nameEvt, String descripcion, String fecha, String hora){
        this.nameEvt = nameEvt;
        this.descripcion = descripcion;
        this.fecha = fecha;
        this.hora = hora;
    }

    public Evt(String nameEvt, String fecha, String hora){
        this.nameEvt = nameEvt;
        this.fecha = fecha;
        this.hora = hora;
    }

    void setNameEvt(String nameEvt){
        this.nameEvt = nameEvt;
    }

    void setDescripcion(String descripcion){
        this.descripcion = descripcion;
    }

    void setFecha(String fecha){
        this.fecha = fecha;
    }

    void setHora(String hora){
        this.hora = hora;
    }

    public String getNameEvt(){
        return nameEvt;
    }

    public String getDescripcion(){
        return descripcion;
    }

    public String getFecha(){
        return fecha;
    }

    public String getHora(){
        return hora;
    }

}
