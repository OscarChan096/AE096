package com.studio.oscar.agendaescolar.Objetos;

import java.io.Serializable;

public class Agenda implements Serializable{ //Comparable<Agenda> {

    private int id;
    private String asignatura, profesor, aula, hora, tohora;
    private String nombreArchivo;
    private int diaModificacion, mesModificacion, anioModificacion;
    private String dia;
    private String tarea;
    private String fechaEntrega;
    private String activity_name;
    private int diaEntrega, mesEntrega, anioEntrega;

    public Agenda(int id, String asignatura, String profesor, String aula, String hora, String tohora, String dia, String activity_name) {
        this.id = id;
        this.asignatura = asignatura;
        this.profesor = profesor;
        this.aula = aula;
        this.hora = hora;
        this.tohora = tohora;
        this.dia = dia;
        this.fechaEntrega = dia;
        this.activity_name = activity_name;
    }

    public Agenda(String asignatura, String tarea, String fechaEntrega, String activity_name) {
        this.asignatura = asignatura;
        this.tarea = tarea;
        this.fechaEntrega = fechaEntrega;
        this.activity_name = activity_name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAsignatura() {
        return asignatura;
    }

    public void setAsignatura(String asignatura) {
        this.asignatura = asignatura;
    }

    public String getProfesor() {
        return profesor;
    }

    public void setProfesor(String profesor) {
        this.profesor = profesor;
    }

    public String getAula() {
        return aula;
    }

    public void setAula(String aula) {
        this.aula = aula;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getTohora() {
        return tohora;
    }

    public void setTohora(String tohora) {
        this.tohora = tohora;
    }

    public String getNombreArchivo() {
        return nombreArchivo;
    }

    public void setNombreArchivo(String nombreArchivo) {
        this.nombreArchivo = nombreArchivo;
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

    public String getDia() {
        return dia;
    }

    public void setDia(String dia) {
        this.dia = dia;
    }

    public String getTarea() {
        return tarea;
    }

    public void setTarea(String tarea) {
        this.tarea = tarea;
    }

    public String getFechaEntrega() {
        return fechaEntrega;
    }

    public void setFechaEntrega(String fechaEntrega) {
        this.fechaEntrega = fechaEntrega;
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

    /*@Override
    public int compareTo(Agenda b) {
        if (diaEntrega > b.diaEntrega && mesEntrega > b.mesEntrega && anioEntrega > b.anioEntrega) {
            return 1;
        } else if (diaEntrega < b.diaEntrega && mesEntrega < b.mesEntrega && anioEntrega < b.anioEntrega) {
            return -1;
        } else {
            return 0;
        }
    }*/

}
