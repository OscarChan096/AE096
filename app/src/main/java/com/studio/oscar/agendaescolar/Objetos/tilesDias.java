package com.studio.oscar.agendaescolar.Objetos;

public class tilesDias {

    private String day;
    private int imagen;

    public tilesDias(){
        super();
    }

    public tilesDias(String day, int imagen){
        super();
        this.day = day;
        this.imagen = imagen;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public int getImage(){
        return imagen;
    }

    public void setImage(int imagen){
        this.imagen = imagen;
    }

}
