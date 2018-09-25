package com.studio.oscar.agendaescolar.Objetos;

import java.io.Serializable;

public class HClases implements Serializable, Comparable<HClases> {

    private int id;
    private String asignatura, profesor, aula, hora, tohora, nombreArchivo;

    public HClases(int id, String asignatura, String profesor, String aula, String hora, String tohora){
        this.id = id;
        this.asignatura = asignatura;
        this.profesor = profesor;
        this.aula = aula;
        this.hora = hora;
        this.tohora = tohora;
    }

    public HClases(String asignatura, String profesor, String aula, String hora, String tohora){
        this.asignatura = asignatura;
        this.profesor = profesor;
        this.aula = aula;
        this.hora = hora;
        this.tohora = tohora;
    }

    public HClases(String asignatura, String profesor, String aula){
        this.asignatura = asignatura;
        this.profesor = profesor;
        this.aula = aula;
    }

    public String getNombreArchivo(){
        return nombreArchivo;
    }

    public void setNombreArchivo(String nombreArchivo){
        this.nombreArchivo = nombreArchivo;
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

    @Override
    public int compareTo(HClases b) {
        if(id>b.id){
            return 1;
        }else if(id<b.id){
            return -1;
        }else{
            return 0;
        }
    }

}
