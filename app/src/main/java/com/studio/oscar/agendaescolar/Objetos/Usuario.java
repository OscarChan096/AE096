package com.studio.oscar.agendaescolar.Objetos;

import java.io.Serializable;

public class Usuario implements Serializable {

    String nombre;
    String escuela;
    String especialidad;
    String numTel;
    String nControl;

    public Usuario(String nombre,String escuela, String especialidad, String numTel, String nControl){
        this.nombre = nombre;
        this.escuela = escuela;
        this.especialidad = especialidad;
        this.numTel = numTel;
        this.nControl = nControl;
    }

    public String getNombre(){
        return nombre;
    }

    public String getEscuela(){
        return escuela;
    }

    public String getEspecialidad(){
        return especialidad;
    }

    public String getNumTel(){
        return numTel;
    }

    public String getnControl(){
        return nControl;
    }

}
