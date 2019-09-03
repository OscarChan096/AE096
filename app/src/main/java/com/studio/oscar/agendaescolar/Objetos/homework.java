package com.studio.oscar.agendaescolar.Objetos;

import java.io.Serializable;

public class homework implements Serializable {

    String asignatura;
    String tarea;
    String fechaEntrega;
    String activity_name;
    int diaModificacion, mesModificacion,anioModificacion;
    int diaEntrega, mesEntrega, anioEntrega;

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

    public int getDiaModificacion() {
        return diaModificacion;
    }

    public void setDiaModificacion(int diaModificacion) {
        this.diaModificacion = diaModificacion;
    }

    public int getMesModificacion() {
        return mesModificacion;
    }

    public void setMesModificacion(int mesModificacion) {
        this.mesModificacion = mesModificacion;
    }

    public int getAnioModificacion() {
        return anioModificacion;
    }

    public void setAnioModificacion(int anioModificacion) {
        this.anioModificacion = anioModificacion;
    }

    public int getDiaEntrega() {
        return diaEntrega;
    }

    public void setDiaEntrega(int diaEntrega) {
        this.diaEntrega = diaEntrega;
    }

    public int getMesEntrega() {
        return mesEntrega;
    }

    public void setMesEntrega(int mesEntrega) {
        this.mesEntrega = mesEntrega;
    }

    public int getAnioEntrega() {
        return anioEntrega;
    }

    public void setAnioEntrega(int anioEntrega) {
        this.anioEntrega = anioEntrega;
    }

    public String getActivity_name() {
        return activity_name;
    }

    public void setActivity_name(String activity_name) {
        this.activity_name = activity_name;
    }
}
