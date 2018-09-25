package com.studio.oscar.agendaescolar.Objetos;

import java.io.Serializable;

public class homework implements Serializable {

    String asignatura;
    String tarea;
    String fechaEntrega;

    public homework(String asignatura, String tarea, String fechaEntrega){
        this.asignatura = asignatura;
        this.tarea = tarea;
        this.fechaEntrega = fechaEntrega;
    }

    public homework(String asignatura, String tarea){
        this.asignatura = asignatura;
        this.tarea = tarea;
    }

    public homework(String asignatura){
        this.asignatura = asignatura;
    }

    void setAsignatura(String asignatura){
        this.asignatura = asignatura;
    }

    void setTarea(String tarea){
        this.tarea = tarea;
    }

    void setFechaEntrega(String fechaEntrega){
        this.fechaEntrega = fechaEntrega;
    }

    public String getAsignatura(){
        return asignatura;
    }

    public String getTarea(){
        return tarea;
    }

    public String getFechaEntrega(){
        return fechaEntrega;
    }

}
